package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.HistoricWayNodeRepository;
import pl.pwr.bdmap.model.HistoricWayNode;
import pl.pwr.bdmap.model.WayNode;

@Service
public class HistoricWayNodeService {
    private final ChangesetService changesetService;
    private final HistoricWayNodeRepository historicWayNodeRepository;
    private final HistoricWayDataService historicWayDataService;
    private final HistoricNodeDataService historicNodeDataService;

    public HistoricWayNodeService(ChangesetService changesetService, HistoricWayNodeRepository historicWayNodeRepository, HistoricWayDataService historicWayDataService, HistoricNodeDataService historicNodeDataService) {
        this.changesetService = changesetService;
        this.historicWayNodeRepository = historicWayNodeRepository;
        this.historicWayDataService = historicWayDataService;
        this.historicNodeDataService = historicNodeDataService;
    }


    public HistoricWayNode saveInitialVersion(WayNode wayNode) {
        HistoricWayNode historicWayNode = new HistoricWayNode();
        historicWayNode.setWayNode(wayNode);
        historicWayNode.setHistoricWayData(historicWayDataService.findInitialVersionByWayId(wayNode.getWay().getId()));
        historicWayNode.setNode1(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode1().getId()));
        historicWayNode.setNode2(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode2().getId()));
        historicWayNode.setChangeset(changesetService.getSystemChangeset());
        return historicWayNodeRepository.save(historicWayNode);
    }
}

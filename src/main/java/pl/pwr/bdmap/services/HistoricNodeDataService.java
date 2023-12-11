package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.HistoricNodeDataRepository;
import pl.pwr.bdmap.dto.HistoricNodeDataDTO;
import pl.pwr.bdmap.dto.HistoricNodeDataDTOMapper;
import pl.pwr.bdmap.model.HistoricNodeData;
import pl.pwr.bdmap.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricNodeDataService {
    private final HistoricNodeDataRepository historicNodeDataRepository;
    private final HistoricNodeDataDTOMapper historicNodeDataDTOMapper;

    private final ChangesetService changesetService;

    @Autowired
    public HistoricNodeDataService(HistoricNodeDataRepository historicNodeDataRepository, HistoricNodeDataDTOMapper historicNodeDataDTOMapper, ChangesetService changesetService) {
        this.historicNodeDataRepository = historicNodeDataRepository;
        this.historicNodeDataDTOMapper = historicNodeDataDTOMapper;
        this.changesetService = changesetService;
    }

    public List<HistoricNodeDataDTO> list() {
        List<HistoricNodeData> list = new ArrayList<>();
        historicNodeDataRepository.findAll().forEach(list::add);
        return list.stream().map(historicNodeDataDTOMapper).collect(Collectors.toList());
    }

    public HistoricNodeData saveInitialVersion(Node node) {
        HistoricNodeData historicNodeData = new HistoricNodeData();
        historicNodeData.setNode(node);
        historicNodeData.setPosX(node.getPosX());
        historicNodeData.setPosY(node.getPosY());
        historicNodeData.setChangeset(changesetService.getSystemChangeset());
        // Save
        return historicNodeDataRepository.save(historicNodeData);
    }
}

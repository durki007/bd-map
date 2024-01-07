package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.HistoricWayDataRepository;
import pl.pwr.bdmap.model.HistoricWayData;
import pl.pwr.bdmap.model.Way;

@Service
public class HistoricWayDataService {

    private final ChangesetService changesetService;
    private final HistoricWayDataRepository historicWayDataRepository;

    public HistoricWayDataService(ChangesetService changesetService, HistoricWayDataRepository historicWayDataRepository) {
        this.changesetService = changesetService;
        this.historicWayDataRepository = historicWayDataRepository;
    }

    public HistoricWayData saveInitialVersion(Way way) {
        HistoricWayData historicWayData = new HistoricWayData();
        historicWayData.setWay(way);
        historicWayData.setName(way.getName());
        historicWayData.setChangeset(changesetService.getSystemChangeset());
        return historicWayDataRepository.save(historicWayData);
    }
}

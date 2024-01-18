package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.HistoricWayData;

public interface HistoricWayDataRepository extends CrudRepository<HistoricWayData, Integer> {
    public HistoricWayData findByChangeset_IdAndWay_Id(int changesetId, int wayId);
}

package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.HistoricNodeData;

public interface HistoricNodeDataRepository extends CrudRepository<HistoricNodeData, Integer> {
}

package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.HistoricWayNode;

public interface HistoricWayNodeRepository extends CrudRepository<HistoricWayNode, Integer> {
}

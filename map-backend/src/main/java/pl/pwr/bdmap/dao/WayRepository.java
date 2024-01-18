package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Way;

public interface WayRepository extends CrudRepository<Way, Integer> {
}

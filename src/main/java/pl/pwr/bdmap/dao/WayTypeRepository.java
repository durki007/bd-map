package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.WayType;

public interface WayTypeRepository extends CrudRepository<WayType, Integer> {
    WayType findByType(String type);
}

package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyWayType;
import pl.pwr.bdmap.model.WayType;

import java.util.List;

public interface KeyWayTypeRepository extends CrudRepository<KeyWayType, Integer> {
    List<KeyWayType> findAllByWayTypeId(int id);

    KeyWayType findByKeyAndWayType(Key key, WayType wayType);
}

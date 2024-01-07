package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.WayTag;

import java.util.List;

public interface WayTagRepository extends CrudRepository <WayTag, Integer> {
    List<WayTag> findAllByWayId(int id);
}

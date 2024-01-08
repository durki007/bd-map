package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.model.WayTag;

import java.util.List;

public interface WayTagRepository extends CrudRepository <WayTag, Integer> {
    List<WayTag> findAllByWayId(int id);

    WayTag findByWayAndKey(Way way, Key key);
}

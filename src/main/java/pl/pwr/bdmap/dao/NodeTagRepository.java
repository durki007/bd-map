package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.NodeTag;

import java.util.List;

public interface NodeTagRepository extends CrudRepository<NodeTag, Integer> {
    List<NodeTag> findAllByNodeId(int id); //TODO: how to implement
}

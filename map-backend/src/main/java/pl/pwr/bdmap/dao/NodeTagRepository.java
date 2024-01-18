package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeTag;

import java.util.List;

public interface NodeTagRepository extends CrudRepository<NodeTag, Integer> {
    List<NodeTag> findAllByNodeId(int id);

    NodeTag findByNodeAndKey(Node node, Key key);
}

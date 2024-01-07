package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyNodeType;
import pl.pwr.bdmap.model.NodeType;

import java.util.List;

public interface KeyNodeTypeRepository extends CrudRepository<KeyNodeType, Integer> {
    List<KeyNodeType> findAllByNodeTypeId(int id);

    boolean findByNodeValue(String keyName);

    List<KeyNodeType> findAllByKeyIdAndNodeTypeId(int id, int id1);

    KeyNodeType findByKeyAndNodeType(Key key, NodeType nodeType);
}

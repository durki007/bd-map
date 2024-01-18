package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.NodeType;

public interface NodeTypeRepository extends CrudRepository<NodeType, Integer> {
    NodeType findByType(String type);

}

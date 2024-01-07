package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeType;

public interface NodeRepository extends CrudRepository<Node, Integer> {



}

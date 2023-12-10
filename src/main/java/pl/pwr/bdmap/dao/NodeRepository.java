package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Node;

public interface NodeRepository extends CrudRepository<Node, Integer> {
}

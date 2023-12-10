package pl.pwr.bdmap.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Node;

public interface NodeRepository extends CrudRepository<Node, Integer> {
}

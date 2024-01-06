package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.KeyNodeType;

import java.util.List;

public interface KeyNodeTypeRepository extends CrudRepository<KeyNodeType, Integer> {
    List<KeyNodeType> findAllByNodeTypeId(int id);
}

package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Key;

public interface KeyRepository extends CrudRepository<Key, Integer> {


    boolean findByNodeValue(String keyName);

    Key findByValue(String keyNameStr);
}

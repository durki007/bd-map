package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

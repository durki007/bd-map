package pl.pwr.bdmap.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

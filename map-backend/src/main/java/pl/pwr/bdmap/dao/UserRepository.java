package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByRole(String role);
}

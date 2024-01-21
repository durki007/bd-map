package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.User;

import java.util.List;

public interface ChangesetRepository extends CrudRepository<Changeset, Integer> {
    public List<Changeset> findByUser(User user);
}

package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Changeset;

public interface ChangesetRepository extends CrudRepository<Changeset, Long> {
}

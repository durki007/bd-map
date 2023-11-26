package pl.pwr.bdmap.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.Changeset;

public interface ChangesetRepository extends CrudRepository<Changeset, Long> {
}

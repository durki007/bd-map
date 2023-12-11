package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.ChangesetRepository;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.User;

@Service
public class ChangesetService {
    private final ChangesetRepository changesetRepository;
    private final UserService userService;

    @Autowired
    public ChangesetService(ChangesetRepository changesetRepository, UserService userService) {
        this.changesetRepository = changesetRepository;
        this.userService = userService;
    }

    public Changeset getSystemChangeset() {
        User systemUser = userService.getSystemUser();
        return changesetRepository.findByUser(systemUser).get(0);
    }
}

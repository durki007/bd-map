package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.ChangesetRepository;
import pl.pwr.bdmap.dto.ChangesetDTO;
import pl.pwr.bdmap.dto.ChangesetDTOMapper;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.User;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChangesetService {
    private final ChangesetRepository changesetRepository;
    private final ChangesetDTOMapper changesetDTOMapper;
    private final UserService userService;

    @Autowired
    public ChangesetService(ChangesetRepository changesetRepository, ChangesetDTOMapper changesetDTOMapper, UserService userService) {
        this.changesetRepository = changesetRepository;
        this.changesetDTOMapper = changesetDTOMapper;
        this.userService = userService;
    }

    public Changeset getSystemChangeset() {
        User systemUser = userService.getSystemUser();
        return changesetRepository.findByUser(systemUser).get(0);
    }

    public Changeset getChangeSetById(long id) throws NoSuchElementException {
        return changesetRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public ChangesetDTO create(long userId) throws NoSuchElementException {
        Changeset changesetModel = new Changeset();
        User user = userService.getUserById(userId); // Throws NoSuchElementException
        changesetModel.setUser(user);
        return changesetDTOMapper.apply(changesetRepository.save(changesetModel));
    }

    public List<ChangesetDTO> listByUserId(long userId) throws NoSuchElementException {
        User user = userService.getUserById(userId); // Throws NoSuchElementException
        return changesetRepository.findByUser(user).stream().map(changesetDTOMapper).toList();
    }
}

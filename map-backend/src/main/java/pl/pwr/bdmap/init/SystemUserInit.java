package pl.pwr.bdmap.init;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.pwr.bdmap.dao.ChangesetRepository;
import pl.pwr.bdmap.dao.UserRepository;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.User;

import java.util.List;

@Component
public class SystemUserInit implements InitializingBean {

    private final UserRepository userRepository;
    private final ChangesetRepository changesetRepository;
    private static final String SYSTEM_ROLE = "SYSTEM";

    @Autowired
    public SystemUserInit(UserRepository userRepository, ChangesetRepository changesetRepository) {
        this.userRepository = userRepository;
        this.changesetRepository = changesetRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initSystemUser();
        initSystemChangeset();
    }

    private void initSystemUser() {
        List<User> list = userRepository.findByRole(SYSTEM_ROLE);
        if (list.isEmpty()) {
            User user = new User();
            user.setRole(SYSTEM_ROLE);
            user.setUsername(SYSTEM_ROLE);
            user.setEmail(SYSTEM_ROLE);
            user.setPassword(SYSTEM_ROLE);
            userRepository.save(user);
            return;
        }
        if (list.size() > 1)
            throw new IllegalStateException("There is more than one SYSTEM user in the database");
    }

    private void initSystemChangeset() {
        List<Changeset> list = changesetRepository.findByUser(userRepository.findByRole(SYSTEM_ROLE).getFirst());
        if (list.isEmpty()) {
            Changeset changeset = new Changeset();
            changeset.setUser(userRepository.findByRole(SYSTEM_ROLE).getFirst());
            changesetRepository.save(changeset);
            return;
        }
        if (list.size() > 1)
            throw new IllegalStateException("There is more than one SYSTEM changeset in the database");
    }
}

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
        List<User> list = userRepository.findByRole("SYSTEM");
        if (list.isEmpty()) {
            User user = new User();
            user.setRole("SYSTEM");
            user.setUsername("SYSTEM");
            user.setEmail("SYSTEM");
            user.setPassword("SYSTEM");
            userRepository.save(user);
            return;
        }
        if (list.size() > 1)
            throw new IllegalStateException("There is more than one SYSTEM user in the database");
    }

    private void initSystemChangeset() {
        List<Changeset> list = changesetRepository.findByUser(userRepository.findByRole("SYSTEM").get(0));
        if (list.isEmpty()) {
            Changeset changeset = new Changeset();
            changeset.setUser(userRepository.findByRole("SYSTEM").get(0));
            changesetRepository.save(changeset);
            return;
        }
        if (list.size() > 1)
            throw new IllegalStateException("There is more than one SYSTEM changeset in the database");
    }
}

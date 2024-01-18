package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.UserRepository;
import pl.pwr.bdmap.model.User;

import java.util.NoSuchElementException;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getSystemUser() {
        return userRepository.findByRole("SYSTEM").get(0);
    }

    public User getUserById(Long id) throws NoSuchElementException {
        return userRepository.findById(id).orElseThrow();
    }
}

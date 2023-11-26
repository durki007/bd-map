package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.model.User;
import pl.pwr.bdmap.repositories.UserRepository;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

   @GetMapping("/users")
    List<User> all() {
        return (List<User>) userRepository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

}

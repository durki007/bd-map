package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.*;
import pl.pwr.bdmap.model.User;
import pl.pwr.bdmap.dao.UserRepository;

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

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}

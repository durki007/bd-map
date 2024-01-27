package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.*;
import pl.pwr.bdmap.dto.UserInfoDTO;
import pl.pwr.bdmap.dto.mappers.UserInfoDTOMapper;
import pl.pwr.bdmap.model.User;
import pl.pwr.bdmap.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;
    private final UserInfoDTOMapper userInfoDTOMapper;

    public UserController(UserRepository userRepository, UserInfoDTOMapper userInfoDTOMapper) {
        this.userRepository = userRepository;
        this.userInfoDTOMapper = userInfoDTOMapper;
    }

    @GetMapping("/users")
    List<UserInfoDTO> all() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users.stream().map(userInfoDTOMapper).toList();
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

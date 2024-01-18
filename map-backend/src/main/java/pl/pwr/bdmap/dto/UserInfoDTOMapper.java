package pl.pwr.bdmap.dto;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.User;

import java.util.function.Function;

@Service
public class UserInfoDTOMapper implements Function<User, UserInfoDTO> {
   @Override
    public UserInfoDTO apply(User user) {
        return new UserInfoDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole()
        );
    }
}

package pl.pwr.bdmap.dto;


import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.Changeset;
import java.util.function.Function;

@Service
public class ChangesetDTOMapper implements Function<Changeset, ChangesetDTO> {
    @Override
    public ChangesetDTO apply(Changeset changeset) {
        UserInfoDTOMapper userInfoDTOMapper = new UserInfoDTOMapper();
        return new ChangesetDTO(
                changeset.getId(),
                changeset.getCreationDate(),
                userInfoDTOMapper.apply(changeset.getUser())
        );
    }
}

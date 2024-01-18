package pl.pwr.bdmap.dto;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.Way;

import java.util.function.Function;


@Service
public class WayDTOMapper implements  Function<Way, WayDTO>{
    @Override
    public WayDTO apply(Way way) {
        return new WayDTO(
                way.getId(),
                way.getName(),
                way.getIsBlocked(),
                way.getTimestamp(),
                way.getWayType().getType()
        );
    }
}

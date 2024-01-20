package pl.pwr.bdmap.dto;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.WayNode;

import java.util.function.Function;

@Service
public class WayNodeDTOMapper implements Function<WayNode, WayNodeDTO> {
    @Override
    public WayNodeDTO apply(WayNode wayNode) {
        int blockedById = 0;
        if (wayNode.getBlockedBy() != null) {
            blockedById = wayNode.getBlockedBy().getId();
        }

        return new WayNodeDTO(
                wayNode.getId(),
                wayNode.getWay().getId(),
                wayNode.getNode1().getId(),
                wayNode.getNode2().getId(),
                blockedById
        );
    }
}

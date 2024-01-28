package pl.pwr.bdmap.dto;

import java.sql.Timestamp;
import java.util.List;

public record NodeDTO(
        int id,
        double posX,
        double posY,
        int blockedBy,
        Timestamp timestamp,
        String nodeType,
        List<KeyDTO> keys
) {
}

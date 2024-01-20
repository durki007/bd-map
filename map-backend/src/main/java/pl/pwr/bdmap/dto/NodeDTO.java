package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record NodeDTO(
        int id,
        int posX,
        int posY,
        int blockedBy,
        Timestamp timestamp,
        String nodeType
) {}

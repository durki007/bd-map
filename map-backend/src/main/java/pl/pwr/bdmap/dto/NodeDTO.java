package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record NodeDTO(
        int id,
        double posX,
        double posY,
        boolean isBlocked,
        Timestamp timestamp,
        String nodeType
) {}

package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record WayDTO(
        int id,
        String name,
        int blockedBy,
        Timestamp timestamp,
        String wayType

) {}

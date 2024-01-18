package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record WayDTO(
        int id,
        String name,
        boolean isBlocked,
        Timestamp timestamp,
        String wayType

) {}

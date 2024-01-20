package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record HistoricNodeDataDTO(
        int id,
        double posX,
        double posY,
        Timestamp timestamp,
        int nodeId
) {}

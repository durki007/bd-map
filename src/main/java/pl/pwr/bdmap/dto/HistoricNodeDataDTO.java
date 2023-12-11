package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record HistoricNodeDataDTO(
        int id,
        int posX,
        int posY,
        Timestamp timestamp,
        int nodeId
) {}

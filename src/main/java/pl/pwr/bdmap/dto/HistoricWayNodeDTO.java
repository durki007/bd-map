package pl.pwr.bdmap.dto;

import java.sql.Timestamp;

public record HistoricWayNodeDTO(
        int id,
        Timestamp timestamp,
        int wayId,
        int node1Id,
        int node2Id
) {
}

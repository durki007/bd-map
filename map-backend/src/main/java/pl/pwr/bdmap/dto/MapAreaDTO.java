package pl.pwr.bdmap.dto;

import java.sql.Timestamp;
import java.util.List;

public record MapAreaDTO(
        double maxX,
        double minX,
        double maxY,
        double minY,
        List<NodeDTO> singularNodes,
        List<MapAreaWayDTO> ways
) {
    public record MapAreaWayDTO(
            int id,
            String name,
            int blockedBy,
            String wayType,
            Timestamp timestamp,
            List<MapAreaWayNodeDTO> wayNodes
    ) {
    }

    public record MapAreaWayNodeDTO(
            int id,
            NodeDTO node1,
            NodeDTO node2
    ) {
    }
}

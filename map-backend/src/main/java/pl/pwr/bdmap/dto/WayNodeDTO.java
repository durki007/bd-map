package pl.pwr.bdmap.dto;

public record WayNodeDTO(
        int id,
        int wayId,
        int node1Id,
        int node2Id,
        int blockedBy
) {
}

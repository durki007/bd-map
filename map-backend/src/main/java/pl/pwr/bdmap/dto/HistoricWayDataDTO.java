package pl.pwr.bdmap.dto;

public record HistoricWayDataDTO(
        int id,
        String name,
        java.sql.Timestamp timestamp,
        int wayId
) {
}

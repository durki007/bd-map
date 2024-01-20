package pl.pwr.bdmap.dto;

public record ChangesetDTO(
        int id,
        java.sql.Timestamp creationDate,
        java.sql.Timestamp closeDate,
        UserInfoDTO userInfo
) {}

package pl.pwr.bdmap.dto;

public record UserInfoDTO(
        int id,
        String username,
        String email,
        String role
) {
}

package pl.pwr.bdmap.dto;

import java.util.List;
import java.util.Map;

public record UMPNodeDTO(
        double lon,
        double lat,
        String nodeType,
        List<Map<String, String>> kv
) {}

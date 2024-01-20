package pl.pwr.bdmap.dto;

import java.util.List;

public record MapAreaDTO(
        double topLeft,
        double topRight,
        double bottomLeft,
        double bottomRight,
        List<NodeDTO> nodeDTOList,
        List<WayNodeDTO> wayNodeDTOList,
        List<WayDTO> wayDTOList
) {}

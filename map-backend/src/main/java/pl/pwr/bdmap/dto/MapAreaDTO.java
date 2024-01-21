package pl.pwr.bdmap.dto;

import java.util.List;

public record MapAreaDTO(
        double maxX,
        double minX,
        double maxY,
        double minY,
        List<NodeDTO> nodeDTOList,
        List<WayNodeDTO> wayNodeDTOList,
        List<WayDTO> wayDTOList
) {}

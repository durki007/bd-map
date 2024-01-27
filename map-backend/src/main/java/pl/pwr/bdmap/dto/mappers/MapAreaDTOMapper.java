package pl.pwr.bdmap.dto.mappers;


import pl.pwr.bdmap.dto.MapAreaDTO;
import pl.pwr.bdmap.model.MapArea;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class MapAreaDTOMapper implements Function<MapArea, MapAreaDTO> {

    private final NodeDTOMapper nodeDTOMapper = new NodeDTOMapper();

    @Override
    public MapAreaDTO apply(MapArea mapArea) {
        var singularNodes = mapArea.getNodes().stream().map(nodeDTOMapper).toList();
        List<MapAreaDTO.MapAreaWayDTO> ways = mapArea.getWays()
                .stream()
                .map(way -> new MapAreaDTO.MapAreaWayDTO(
                        way.getId(),
                        way.getName(),
                        way.getBlockedBy() == null ? 0 : way.getBlockedBy().getId(),
                        way.getWayType().getType(),
                        way.getTimestamp(),
                        way.getWayNode().stream()
                                .map(wayNode -> new MapAreaDTO.MapAreaWayNodeDTO(
                                        wayNode.getId(),
                                        nodeDTOMapper.apply(wayNode.getNode1()),
                                        nodeDTOMapper.apply(wayNode.getNode2())
                                ))
                                .toList()
                )).toList();
        return new MapAreaDTO(
                mapArea.getMaxX(),
                mapArea.getMinX(),
                mapArea.getMaxY(),
                mapArea.getMinY(),
                singularNodes,
                ways
        );
    }
}

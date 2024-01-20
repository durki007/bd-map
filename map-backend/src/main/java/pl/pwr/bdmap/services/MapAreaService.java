package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.MapAreaDTO;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayNodeDTO;

import java.util.List;

@Service
public class MapAreaService {



    private final NodeService nodeService;
    private final WayNodeService wayNodeService;
    private final WayService wayService;

    @Autowired
    public MapAreaService(NodeService nodeService, WayNodeService wayNodeService, WayService wayService) {
        this.nodeService = nodeService;
        this.wayNodeService = wayNodeService;
        this.wayService = wayService;
    }

    public MapAreaDTO getMapAreaData(double maxX, double minX, double maxY, double minY) {

        List<NodeDTO> nodeDTOList = nodeService.getNodesOnScreen(minX, maxX, minY, maxY);
        System.out.println("NODE DTO LIST" + nodeDTOList);
        List<WayNodeDTO> wayNodeDTOList = wayNodeService.getWayNodesOnScreen(minX, maxX, minY, maxY);

        List<WayDTO> wayDTOList = wayService.getWaysOnTheScreen(minX, maxX, minY, maxY);
        return new MapAreaDTO(maxX, minX, maxY,minY, nodeDTOList, wayNodeDTOList, wayDTOList);
    }

}

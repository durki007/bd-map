package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.MapAreaDTO;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayNodeDTO;

import javax.naming.directory.InvalidAttributesException;
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

    public MapAreaDTO getMapAreaData(double maxX, double minX, double maxY, double minY) throws InvalidAttributesException {

        if(maxX < minX || maxY < minY) {
            throw new InvalidAttributesException("Invalid square limits");
        }

        List<NodeDTO> nodeDTOList = nodeService.getNodesOnScreen(maxX, minX, maxY, minY);

        List<WayNodeDTO> wayNodeDTOList = wayNodeService.getWayNodesOnScreen(maxX, minX, maxY, minY);

        List<WayDTO> wayDTOList = wayService.getWaysOnTheScreen(maxX, minX, maxY, minY);

        return new MapAreaDTO(maxX, minX, maxY,minY, nodeDTOList, wayNodeDTOList, wayDTOList);
    }

}

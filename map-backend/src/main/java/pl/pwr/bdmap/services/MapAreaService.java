package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.MapAreaDTO;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.model.MapArea;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.Way;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
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

    public MapArea getMapAreaData(double maxX, double minX, double maxY, double minY) throws InvalidAttributesException {
        if (maxX < minX || maxY < minY) {
            throw new InvalidAttributesException("Invalid square limits");
        }
        List<Node> allNodes = nodeService.getNodesOnScreen(maxX, minX, maxY, minY);
        List<Way> ways = wayService.getWaysOnTheScreen(maxX, minX, maxY, minY);
        List<Node> singularNodes = new ArrayList<>();
        for (Node node : allNodes) {
            if (node.getWayNode().isEmpty() && node.getWayNode1().isEmpty()) {
                singularNodes.add(node);
            }
        }
        return new MapArea(maxX, minX, maxY, minY, singularNodes, ways);
    }

}

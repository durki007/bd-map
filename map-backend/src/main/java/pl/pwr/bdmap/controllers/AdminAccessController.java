package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.exceptions.NotFoundException;
import pl.pwr.bdmap.services.NodeService;
import pl.pwr.bdmap.services.WayNodeService;
import pl.pwr.bdmap.services.WayService;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/*
 * This class is responsible for handling requests from the admin panel.
 */
@RestController
public class AdminAccessController {

    private final NodeService nodeService;
    private final WayService wayService;

    private final WayNodeService wayNodeService;

    public AdminAccessController(NodeService nodeService, WayService wayService, WayNodeService wayNodeService) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.wayNodeService = wayNodeService;
    }

    @PostMapping("/admin/node")
    NodeDTO newNode(@RequestBody NodeDTO newNode) {
        return nodeService.save(newNode);
    }

    @PostMapping("/admin/nodes")
    List<NodeDTO> newNodes(@RequestBody List<NodeDTO> newNodes) {
        return nodeService.save(newNodes);
    }

    @DeleteMapping("/admin/node/{id}")
    NodeDTO deleteNode(@PathVariable int id) {
        try {
            return nodeService.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Node not found", e);
        }
    }

    @PostMapping("/admin/way")
    WayDTO newWay(@RequestBody WayDTO newWay) {
        return wayService.save(newWay);
    }

    @PostMapping("/admin/ways")
    List<WayDTO> newWays(@RequestBody List<WayDTO> newWays) {
        return wayService.save(newWays);
    }

    @DeleteMapping("/admin/way/{id}")
    WayDTO deleteWay(@PathVariable int id) {
        try {
            return wayService.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    @PostMapping("/admin/wayNode")
    WayNodeDTO newWayNode(@RequestBody WayNodeDTO newWay) {

        try {
            return wayNodeService.save(newWay);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (InvalidAttributesException e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @PostMapping("/admin/wayNodes")
    List<WayNodeDTO> newWayNodes(@RequestBody List<WayNodeDTO> newWays) {
        List<WayNodeDTO> addedNodes = new ArrayList<>();
        try{
            addedNodes = wayNodeService.save(newWays);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
        } catch (InvalidAttributesException e) {
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }

        return addedNodes;
    }

    @DeleteMapping("/admin/wayNode/{id}")
    WayNodeDTO deleteWayNode(@PathVariable int id) {
        try {
            return wayNodeService.removeById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WayNode not found", e);
        }
    }


}

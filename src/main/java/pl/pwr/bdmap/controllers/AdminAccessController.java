package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.services.NodeService;
import pl.pwr.bdmap.services.WayService;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * This class is responsible for handling requests from the admin panel.
 */
@RestController
public class AdminAccessController {

    private final NodeService nodeService;
    private final WayService wayService;

    public AdminAccessController(NodeService nodeService, WayService wayService) {
        this.nodeService = nodeService;
        this.wayService = wayService;
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

    @DeleteMapping("/admin/way/{id}")
    WayDTO deleteWay(@PathVariable int id) {
        try {
            return  wayService.delete(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    // TODO: edit  (check if object is blocked)


}

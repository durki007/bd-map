package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.services.NodeService;

import java.util.List;
import java.util.NoSuchElementException;

/*
 * This class is responsible for handling requests from the admin panel.
 */
@RestController
public class AdminAccessController {

    private final NodeService nodeService;

    public AdminAccessController(NodeService nodeService) {
        this.nodeService = nodeService;
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
}

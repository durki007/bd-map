package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.services.NodeService;

import java.util.List;

@RestController
public class NodeController {

    private final NodeService nodeService;

    public NodeController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping("/nodes")
    List<NodeDTO> all() {
        return nodeService.list();
    }

    @GetMapping("/nodes/range")
    List<NodeDTO> inRange(@RequestParam int x, @RequestParam int y, @RequestParam int range) {
        // TODO: Implement filtering by range
        return nodeService.list();
    }
}

package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.repositories.NodeRepository;

import java.util.List;

@RestController
public class NodeController {
    private final NodeRepository nodeRepository;

    public NodeController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }
    @GetMapping("/nodes")
    List<Node> all() {
        return (List<Node>) nodeRepository.findAll();
    }

    @GetMapping("/nodes/range")
    List<Node> inRange(@RequestParam int x, @RequestParam int y, @RequestParam int range) {
        // TODO: Implement filtering by range
        return (List<Node>) nodeRepository.findAll();
    }
}

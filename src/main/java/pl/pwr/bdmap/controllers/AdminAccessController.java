package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.repositories.NodeRepository;

import java.util.List;

/*
    * This class is responsible for handling requests from the admin panel.
*/
@RestController
public class AdminAccessController {
    private final NodeRepository nodeRepository;

    public AdminAccessController(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @PostMapping("/admin/node")
    Node newNode(@RequestBody Node newNode) {
        return nodeRepository.save(newNode);
    }
    @PostMapping("/admin/nodes")
    List<Node> newNodes(@RequestBody List<Node> newNodes) {
        return (List<Node>) nodeRepository.saveAll(newNodes);
    }
}

package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeType;
import pl.pwr.bdmap.repositories.NodeRepository;
import pl.pwr.bdmap.repositories.NodeTypeRepository;

import java.util.List;

/*
 * This class is responsible for handling requests from the admin panel.
 */
@RestController
public class AdminAccessController {
    private final NodeRepository nodeRepository;
    private final NodeTypeRepository nodeTypeRepository;

    public AdminAccessController(NodeRepository nodeRepository, NodeTypeRepository nodeTypeRepository) {
        this.nodeRepository = nodeRepository;
        this.nodeTypeRepository = nodeTypeRepository;
    }

    @PostMapping("/admin/node")
    Node newNode(@RequestBody Node newNode) {
        NodeType nodeType = newNode.getNodeType();
        if (nodeType == null) {
            nodeType = new NodeType();
            nodeType.setType("default");
        }
        nodeType = nodeTypeRepository.save(nodeType);
        newNode.setNodeType(nodeType);
        return nodeRepository.save(newNode);
    }

    @PostMapping("/admin/nodes")
    List<Node> newNodes(@RequestBody List<Node> newNodes) {
        return (List<Node>) nodeRepository.saveAll(newNodes);
    }
}

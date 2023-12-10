package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.NodeDTOMapper;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.dao.NodeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NodeController {
    private final NodeRepository nodeRepository;
    private final NodeDTOMapper nodeDTOMapper;

    public NodeController(NodeRepository nodeRepository, NodeDTOMapper nodeDTOMapper) {
        this.nodeRepository = nodeRepository;
        this.nodeDTOMapper = nodeDTOMapper;
    }

    @GetMapping("/nodes")
    List<NodeDTO> all() {
        List<Node> list = new ArrayList<Node>();
        nodeRepository.findAll().forEach(list::add);
        return list.stream().map(nodeDTOMapper).collect(Collectors.toList());
    }

    @GetMapping("/nodes/range")
    List<Node> inRange(@RequestParam int x, @RequestParam int y, @RequestParam int range) {
        // TODO: Implement filtering by range
        return (List<Node>) nodeRepository.findAll();
    }
}

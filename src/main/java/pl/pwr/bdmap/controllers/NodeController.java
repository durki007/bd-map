package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dao.KeyRepository;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dao.NodeTagRepository;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeTag;
import pl.pwr.bdmap.services.NodeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class NodeController {

    private final NodeService nodeService;
    private final NodeTagRepository nodeTagRepository;
    private final KeyRepository keyRepository;
    private final NodeRepository nodeRepository;

    public NodeController(NodeService nodeService, NodeTagRepository nodeTagRepository, KeyRepository keyRepository, NodeRepository nodeRepository) {
        this.nodeService = nodeService;
        this.nodeTagRepository = nodeTagRepository;
        this.keyRepository = keyRepository;
        this.nodeRepository = nodeRepository;
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

    @PutMapping("/nodes/key")
    String addKeyToNode(@RequestParam("nodeId") Integer nodeId,
                        @RequestParam("keyName") String keyName ) {

        Node node;

        try{
            node = nodeRepository.findById(nodeId).orElseThrow();
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Node not found", e);
        }

        Key key;
        try{
            key = keyRepository.findByValue(keyName);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Key not found", e);
        }

        NodeTag existingNodeTag = nodeTagRepository.findByNodeAndKey(node,key);

        if(existingNodeTag == null) {
            NodeTag newNodeTag = new NodeTag();
            newNodeTag.setKey(key);
            newNodeTag.setNode(node);

            newNodeTag = nodeTagRepository.save(newNodeTag);
            //TODO: set version as timestamp?

            return newNodeTag.toString();
        }
        else {
            return existingNodeTag.toString();
        }
    }

}

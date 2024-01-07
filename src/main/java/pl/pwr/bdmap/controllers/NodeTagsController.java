package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dao.KeyNodeTypeRepository;
import pl.pwr.bdmap.dao.KeyRepository;
import pl.pwr.bdmap.dao.NodeTypeRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyNodeType;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeType;
import pl.pwr.bdmap.services.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class NodeTagsController {


    private final NodeService nodeService;
    private final NodeTypeService nodeTypeService;
    private final NodeTagService nodeTagService;
    private final KeyService keyService;
    private final KeyNodeTypeService keyNodeTypeService;
    private final NodeTypeRepository nodeTypeRepository;
    private final KeyRepository keyRepository;
    private final KeyNodeTypeRepository keyNodeTypeRepository;

    public NodeTagsController(NodeService nodeService, NodeTagService nodeTagService, NodeTypeService nodeTypeService, KeyService keyService, KeyNodeTypeService keyNodeTypeService, NodeTypeRepository nodeTypeRepostiory, NodeTypeRepository nodeTypeRepository, KeyRepository keyRepository, KeyNodeTypeRepository keyNodeTypeRepository) {
        this.nodeService = nodeService;
        this.nodeTagService = nodeTagService;
        this.nodeTypeService = nodeTypeService;
        this.keyService = keyService;
        this.keyNodeTypeService = keyNodeTypeService;
        this.nodeTypeRepository = nodeTypeRepository;
        this.keyRepository = keyRepository;
        this.keyNodeTypeRepository = keyNodeTypeRepository;
    }

    // get tags for {id} node
    @GetMapping("nodes/{id}/getTags")
    List<String> nodeTags(@PathVariable int id) {
        try{
            return nodeTagService.getTags(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    // get type for {id} node
    @GetMapping("nodes/{id}/getType")
    String nodeTypes(@PathVariable int id) {
        try{
            return nodeService.getNodeType(id).getType();
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    // get available key for {id} node
    @GetMapping("nodes/{id}/getAvailableKeys")
    List<String> availableKeys(@PathVariable int id) {

        // getting the node type of {id} node
        NodeType nodeType = nodeService.getNodeType(id);

        try{
            return keyNodeTypeService.getAvailableKeys(nodeType);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }


    // add possible key for specific type of nodes

    @PutMapping("nodes/{nodeType}/key/{keyName}")
    String keysForType(@PathVariable("nodeType") String nodeTypeStr,
                             @PathVariable("keyName") String keyNameStr) {

        NodeType nodeType = nodeTypeRepository.findByType(nodeTypeStr);

        if (nodeType == null) {
            NodeType newNodeType = new NodeType();
            newNodeType.setType(nodeTypeStr);
            nodeType = nodeTypeRepository.save(newNodeType);
        }

        Key key = keyRepository.findByValue(keyNameStr);

        if (key == null) {
            Key newKey = new Key();
            newKey.setValue(keyNameStr);
            key = keyRepository.save(newKey);
        }

        KeyNodeType existingKeyNodeType = keyNodeTypeRepository.findByKeyAndNodeType(key, nodeType);

        if (existingKeyNodeType == null) {

            KeyNodeType newKeyNodeType = new KeyNodeType();
            newKeyNodeType.setKey(key);
            newKeyNodeType.setNodeType(nodeType);
            keyNodeTypeRepository.save(newKeyNodeType);
            return keyNodeTypeService.save(newKeyNodeType);
        }



        return keyNodeTypeService.save(existingKeyNodeType);
    }




}

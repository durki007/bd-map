package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.services.*;

import java.util.List;
import java.util.NoSuchElementException;

public class NodeTagsController {


    private final NodeService nodeService;
    private final NodeTypeService nodeTypeService;
    private final NodeTagService nodeTagService;
    private final KeyService keyService;

    public NodeTagsController(NodeService nodeService, NodeTagService nodeTagService, NodeTypeService nodeTypeService, KeyService keyService) {
        this.nodeService = nodeService;
        this.nodeTagService = nodeTagService;
        this.nodeTypeService = nodeTypeService;
        this.keyService = keyService;

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
            return nodeTypeService.getType(id);                             // TODO: implement
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

/*
    // get available key for {id} node
    @GetMapping("nodes/{id}/getAvailableKeys")
    List<String> availableKeys(@PathVariable int id) {
        try{
            return KeyNodeTypeService.getAvailableKeys(id);              // TODO: implement
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }


    // add possible key for specific type of nodes

    @PutMapping("nodes/types/key/type=?key=?")                                  // TODO: check it
    List<String> keysForType(@RequestBody List<String> keysForType) {
        return KeyNodeTypeService.save(keysForType);
    }

*/

}

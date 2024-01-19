package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.*;
import pl.pwr.bdmap.exceptions.NotFoundException;
import pl.pwr.bdmap.services.ChangesetService;
import pl.pwr.bdmap.services.EditorService;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EditorController {
    private final EditorService editorService;
    private final ChangesetService changesetService;

    public EditorController(EditorService editorService, ChangesetService changesetService) {
        this.editorService = editorService;
        this.changesetService = changesetService;
    }


    @GetMapping("/editor/changeset")
    public List<ChangesetDTO> listChangesetsByUser(@RequestParam long userId) {
        try {
            return changesetService.listByUserId(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @PostMapping("/editor/changeset")
    public ChangesetDTO createChangeset(@RequestParam long userId) {
        try {
            return changesetService.create(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @PutMapping("/editor/node")
    public HistoricNodeDataDTO updateNodeData(@RequestParam int nodeId, @RequestParam int changesetId, @RequestBody NodeDTO nodeDTO) {
        try {
            return editorService.updateNode(nodeId, changesetId, nodeDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Node or changeset not found", e);
        }
    }

    @PutMapping("/editor/way")
    public HistoricWayDataDTO updateWayData(@RequestParam int wayId, @RequestParam int changesetId, @RequestBody WayDTO wayDTO) {
        try {
            return editorService.updateWay(wayId, changesetId, wayDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way or changeset not found", e);
        }
    }

    @PutMapping("/editor/wayNode")
    public HistoricWayNodeDTO updateWayNodeData(@RequestParam int wayNodeId, @RequestParam int changesetId, @RequestBody WayNodeDTO wayNodeDTO) {
        try {
            return editorService.updateWayNode(wayNodeId, changesetId, wayNodeDTO);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "WayNode or changeset not found", e);
        } catch (InvalidAttributesException | NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}

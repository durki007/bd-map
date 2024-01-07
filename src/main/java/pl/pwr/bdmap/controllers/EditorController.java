package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.ChangesetDTO;
import pl.pwr.bdmap.dto.HistoricNodeDataDTO;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.services.ChangesetService;
import pl.pwr.bdmap.services.EditorService;

import java.util.NoSuchElementException;

@RestController
public class EditorController {
    private final EditorService editorService;
    private final ChangesetService changesetService;

    public EditorController(EditorService editorService, ChangesetService changesetService) {
        this.editorService = editorService;
        this.changesetService = changesetService;
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
}

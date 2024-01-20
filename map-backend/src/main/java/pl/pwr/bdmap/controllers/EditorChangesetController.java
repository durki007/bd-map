package pl.pwr.bdmap.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.*;
import pl.pwr.bdmap.exceptions.ChangesetClosedException;
import pl.pwr.bdmap.services.ChangesetService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class EditorChangesetController {
    private final ChangesetService changesetService;
    private final NodeDTOMapper nodeDTOMapper;

    private final WayDTOMapper wayDTOMapper;
    private final WayNodeDTOMapper wayNodeDTOMapper;

    @Autowired
    public EditorChangesetController(ChangesetService changesetService, NodeDTOMapper nodeDTOMapper, WayDTOMapper wayDTOMapper, WayNodeDTOMapper wayNodeDTOMapper) {
        this.changesetService = changesetService;
        this.nodeDTOMapper = nodeDTOMapper;
        this.wayDTOMapper = wayDTOMapper;
        this.wayNodeDTOMapper = wayNodeDTOMapper;
    }

    @GetMapping("/editor/changeset")
    public List<ChangesetDTO> listChangesetsByUser(@RequestParam long userId) {
        try {
            return changesetService.listByUserId(userId);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @DeleteMapping("/editor/changeset/{id}")
    public ChangesetDTO closeChangeset(@PathVariable int id) {
        try {
            return changesetService.close(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        } catch (ChangesetClosedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Changeset is closed", e);
        }
    }

    @GetMapping("/editor/changeset/{id}/blocked/nodes")
    public List<NodeDTO> listBlockedNodes(@PathVariable int id) {
        try {
            return changesetService.getBlockedNodes(id).stream().map(nodeDTOMapper).toList();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        }
    }

    @PostMapping("/editor/changeset/{id}/blocked/nodes")
    public List<Integer> blockNodes(@PathVariable int id, @RequestBody List<Integer> nodeIds) {
        try {
            return changesetService.blockNodes(id, nodeIds);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        } catch (ChangesetClosedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Changeset is closed", e);
        }
    }

    @GetMapping("/editor/changeset/{id}/blocked/ways")
    public List<WayDTO> listBlockedWays(@PathVariable int id) {
        try {
            return changesetService.getBlockedWays(id).stream().map(wayDTOMapper).toList();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        }
    }

    @PostMapping("/editor/changeset/{id}/blocked/ways")
    public List<Integer> blockWays(@PathVariable int id, @RequestBody List<Integer> wayIds) {
        try {
            return changesetService.blockWays(id, wayIds);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        } catch (ChangesetClosedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Changeset is closed", e);
        }
    }

    @GetMapping("/editor/changeset/{id}/blocked/waynodes")
    public List<WayNodeDTO> listBlockedWayNodes(@PathVariable int id) {
        try {
            return changesetService.getBlockedWayNodes(id).stream().map(wayNodeDTOMapper).toList();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        }
    }

    @PostMapping("/editor/changeset/{id}/blocked/waynodes")
    public List<Integer> blockWayNodes(@PathVariable int id, @RequestBody List<Integer> wayNodeIds) {
        try {
            return changesetService.blockWayNodes(id, wayNodeIds);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Changeset not found", e);
        } catch (ChangesetClosedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Changeset is closed", e);
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
}

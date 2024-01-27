package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.ChangesetRepository;
import pl.pwr.bdmap.dto.ChangesetDTO;
import pl.pwr.bdmap.dto.mappers.ChangesetDTOMapper;
import pl.pwr.bdmap.exceptions.ChangesetClosedException;
import pl.pwr.bdmap.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChangesetService {
    private final NodeService nodeService;
    private final WayService wayService;
    private final WayNodeService wayNodeService;
    private final ChangesetRepository changesetRepository;
    private final ChangesetDTOMapper changesetDTOMapper;
    private final UserService userService;

    @Autowired
    public ChangesetService(NodeService nodeService, WayService wayService, WayNodeService wayNodeService, ChangesetRepository changesetRepository, ChangesetDTOMapper changesetDTOMapper, UserService userService) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.wayNodeService = wayNodeService;
        this.changesetRepository = changesetRepository;
        this.changesetDTOMapper = changesetDTOMapper;
        this.userService = userService;
    }

    public Changeset getSystemChangeset() {
        User systemUser = userService.getSystemUser();
        return changesetRepository.findByUser(systemUser).getFirst();
    }

    public Changeset getChangeSetById(int id) throws NoSuchElementException {
        System.out.println("change set idd: " + id);
        return changesetRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Changeset not found with id: " + id));
    }

    public ChangesetDTO create(long userId) throws NoSuchElementException {
        Changeset changesetModel = new Changeset();
        User user = userService.getUserById(userId); // Throws NoSuchElementException
        changesetModel.setUser(user);
        return changesetDTOMapper.apply(changesetRepository.save(changesetModel));
    }

    public List<ChangesetDTO> listByUserId(long userId) throws NoSuchElementException {
        User user = userService.getUserById(userId); // Throws NoSuchElementException
        return changesetRepository.findByUser(user).stream().map(changesetDTOMapper).toList();
    }

    public ChangesetDTO close(int id) throws NoSuchElementException, ChangesetClosedException {
        Changeset changeset = getChangeSetById(id);
        if (changeset.getCloseDate() != null) {
            throw new ChangesetClosedException("Changeset with id " + id + " is already closed");
        }
        return changesetDTOMapper.apply(closeChangeset(changeset));
    }

    public Changeset closeChangeset(Changeset changeset) {
        changeset.setCloseDate(new java.sql.Timestamp(System.currentTimeMillis()));
        // Unblock
        changeset.getBlockedWayNodes().forEach(wayNode -> wayNode.setBlockedBy(null));
        changeset.getBlockedNodes().forEach(node -> node.setBlockedBy(null));
        changeset.getBlockedWays().forEach(way -> way.setBlockedBy(null));
        return changesetRepository.save(changeset);
    }

    public List<Node> getBlockedNodes(int changesetId) throws NoSuchElementException {
        Changeset changeset = getChangeSetById(changesetId);
        return changeset.getBlockedNodes().stream().toList();

    }

    public List<Way> getBlockedWays(int id) throws NoSuchElementException {
        Changeset changeset = getChangeSetById(id);
        return changeset.getBlockedWays().stream().toList();
    }

    public List<WayNode> getBlockedWayNodes(int id) {
        Changeset changeset = getChangeSetById(id);
        return changeset.getBlockedWayNodes().stream().toList();
    }

    public List<Integer> blockNodes(int id, List<Integer> nodeIds) throws NoSuchElementException, ChangesetClosedException {
        Changeset changeset = getChangeSetById(id);
        if (changeset.getCloseDate() == null) {
            throw new ChangesetClosedException("Changeset with id " + id + " is closed");
        }
        List<Integer> successfullyBlockedNodes = new ArrayList<>();
        for (Integer nodeId : nodeIds) {
            try {
                nodeService.blockNode(nodeId, changeset);
                successfullyBlockedNodes.add(nodeId);
            } catch (NoSuchElementException e) {
                System.out.println("Failed to block node with id " + nodeId + ": " + e.getMessage());
            }
        }
        changesetRepository.save(changeset);
        return successfullyBlockedNodes;
    }

    public List<Integer> blockWays(int id, List<Integer> wayIds) throws NoSuchElementException, ChangesetClosedException {
        Changeset changeset = getChangeSetById(id);
        if (changeset.getCloseDate() == null) {
            throw new ChangesetClosedException("Changeset with id " + id + " is closed");
        }
        List<Integer> successfullyBlockedWays = new ArrayList<>();
        for (Integer wayId : wayIds) {
            try {
                wayService.blockWay(wayId, changeset);
                successfullyBlockedWays.add(wayId);
            } catch (NoSuchElementException e) {
                System.out.println("Failed to block way with id " + wayId + ": " + e.getMessage());
            }
        }
        changesetRepository.save(changeset);
        return successfullyBlockedWays;
    }

    public List<Integer> blockWayNodes(int id, List<Integer> wayNodeIds) throws NoSuchElementException, ChangesetClosedException {
        Changeset changeset = getChangeSetById(id);
        if (changeset.getCloseDate() == null) {
            throw new ChangesetClosedException("Changeset with id " + id + " is closed");
        }
        List<Integer> successfullyBlockedWayNodes = new ArrayList<>();
        for (Integer wayNodeId : wayNodeIds) {
            try {
                wayNodeService.blockWayNode(wayNodeId, changeset);
                successfullyBlockedWayNodes.add(wayNodeId);
            } catch (NoSuchElementException e) {
                System.out.println("Failed to block way node with id " + wayNodeId + ": " + e.getMessage());
            }
        }
        changesetRepository.save(changeset);
        return successfullyBlockedWayNodes;
    }


}

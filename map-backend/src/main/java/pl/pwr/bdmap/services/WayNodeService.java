package pl.pwr.bdmap.services;


import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dao.WayNodeRepository;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.dto.WayNodeDTOMapper;
import pl.pwr.bdmap.exceptions.NotFoundException;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.model.WayNode;

import javax.naming.directory.InvalidAttributesException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class WayNodeService {
    private final WayRepository wayRepository;
    private final NodeRepository nodeRepository;
    private final WayNodeRepository wayNodeRepository;

    private final HistoricWayNodeService historicWayNodeService;


    private final WayNodeDTOMapper mapper;

    public WayNodeService(WayRepository wayRepository, NodeRepository nodeRepository, WayNodeRepository wayNodeRepository, @Lazy HistoricWayNodeService historicWayNodeService, WayNodeDTOMapper mapper) {
        this.wayRepository = wayRepository;
        this.nodeRepository = nodeRepository;
        this.wayNodeRepository = wayNodeRepository;
        this.historicWayNodeService = historicWayNodeService;
        this.mapper = mapper;
    }

    public WayNode save(WayNode wayNode) {
        return wayNodeRepository.save(wayNode);
    }

    public WayNodeDTO save(WayNodeDTO wayNodeDTO) throws NotFoundException, InvalidAttributesException {
        // checking if enough data passed
        if (wayNodeDTO.wayId() == 0 || wayNodeDTO.node1Id() == 0 || wayNodeDTO.node2Id() == 0) {
            throw new InvalidAttributesException("Missing required fields in way_node " + wayNodeDTO);
        }

        if (wayNodeDTO.node1Id() == wayNodeDTO.node2Id()) {
            throw new InvalidAttributesException("Invalid (the same) nodeId fields in way_node " + wayNodeDTO);
        }

        if (!wayNodeRepository.findAllByNode1_IdAndNode2_IdAndWay_IdOrNode1_IdAndNode2_IdAndWay_Id(
                wayNodeDTO.node1Id(), wayNodeDTO.node2Id(), wayNodeDTO.wayId(),
                wayNodeDTO.node2Id(), wayNodeDTO.node1Id(), wayNodeDTO.wayId()
        ).isEmpty()) {
            throw new InvalidAttributesException("There is already such WayNode in the database " + wayNodeDTO);
        }


        WayNode wayNode = new WayNode();
        wayNode.setBlockedBy(null);
        // Check if nodes and way exist
        var way = wayRepository.findById(wayNodeDTO.wayId())
                .orElseThrow(() -> new NotFoundException("Way_node:" + wayNodeDTO + " has reference to not existing Way with id: " + wayNodeDTO.wayId()));
        var node1 = nodeRepository.findById(wayNodeDTO.node1Id())
                .orElseThrow(() -> new NotFoundException("Way_node: " + wayNodeDTO + " has reference to not existing Node1 with id: " + wayNodeDTO.node1Id()));
        var node2 = nodeRepository.findById(wayNodeDTO.node2Id())
                .orElseThrow(() -> new NotFoundException("Way_node: " + wayNodeDTO + " has reference to not existing Node2 with id: " + wayNodeDTO.node2Id()));

        wayNode.setWay(way);
        wayNode.setNode1(node1);
        wayNode.setNode2(node2);
        // Save wayNode
        wayNodeRepository.save(wayNode);
        historicWayNodeService.saveInitialVersion(wayNode);
        // Save initial version
        return mapper.apply(wayNode);
    }

    public List<WayNodeDTO> save(List<WayNodeDTO> wayNodeDTOs) throws NotFoundException, InvalidAttributesException {

        List<WayNodeDTO> savedWayNodes = new ArrayList<>();

        // adding node
        for (int i = 0; i < wayNodeDTOs.size(); i++) {
            WayNodeDTO wayNodeDTO = wayNodeDTOs.get(i);
            try {
                savedWayNodes.add(save(wayNodeDTO));
            } catch (NotFoundException e) {
                // Modify the message of the NotFoundException and rethrow
                throw new NotFoundException("WayNodeDTO at index " + i + ": " + e.getMessage());
            } catch (InvalidAttributesException e) {
                throw new InvalidAttributesException("WayNodeDTO at index " + i + ": " + e.getMessage());
            }
        }
        return savedWayNodes;
    }

    public WayNode update(WayNode wayNode, WayNodeDTO dtoNew) throws NotFoundException, InvalidAttributesException {
        // checking if there is some data in dtoNew and if it's correct
        if ((dtoNew.wayId() == 0 && dtoNew.node1Id() == 0 && dtoNew.node2Id() == 0) || ((dtoNew.node1Id() == dtoNew.node2Id()) && dtoNew.node1Id() != 0)) {
            throw new InvalidAttributesException("Invalid attributes for updating: " + dtoNew);
        }

        if (dtoNew.wayId() != 0) {
            Way newWay = wayRepository.findById(dtoNew.wayId()).orElseThrow(() -> new NotFoundException("Couldn't find way with id: " + dtoNew.wayId()));
            wayNode.setWay(newWay);
        }

        if (dtoNew.node1Id() != 0) {
            Node newNode1 = nodeRepository.findById(dtoNew.node1Id()).orElseThrow(() -> new NotFoundException("Couldn't find node with id: " + dtoNew.node1Id()));
            wayNode.setNode1(newNode1);
        }

        if (dtoNew.node2Id() != 0) {
            Node newNode2 = nodeRepository.findById(dtoNew.node2Id()).orElseThrow(() -> new NotFoundException("Couldn't find node with id: " + dtoNew.node2Id()));
            wayNode.setNode2(newNode2);
        }



        // Validate if the updated data already exists in the database
        if (wayNodeRepository.findAllByNode1_IdAndNode2_IdAndWay_IdOrNode1_IdAndNode2_IdAndWay_Id(
                wayNode.getNode1().getId(), wayNode.getNode2().getId(), wayNode.getWay().getId(),
                wayNode.getNode2().getId(), wayNode.getNode1().getId(), wayNode.getWay().getId()
        ).isEmpty()) {
            // No conflicting data, update the wayNode
            return wayNodeRepository.save(wayNode);
        } else {
            throw new InvalidAttributesException("Node with selected data already exists");
        }
    }

    public List<WayNode> getAll() {
        var a = wayNodeRepository.findAll();
        List<WayNode> wn = new ArrayList<>();
        a.forEach(wn::add);
        return wn;
    }

    public WayNodeDTO removeById(int id) throws NoSuchElementException {
        WayNode wayNode = wayNodeRepository.findById(id).orElseThrow();
        wayNodeRepository.deleteById(id);
        return mapper.apply(wayNode);
    }

    // Returns all Ways that contain given node
    public List<Way> getWaysByNodeId(int nodeId) {
        return wayNodeRepository.findAllByNode1_IdOrNode2_Id(nodeId, nodeId).stream().map(WayNode::getWay).toList();
    }

    public WayNode getWayNodeById(int id) throws NoSuchElementException {
        return wayNodeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("WayNode not found"));
    }

    public void blockWayNode(int id, Changeset changeset) throws NoSuchElementException {
        WayNode wayNode = getWayNodeById(id);
        wayNode.setBlockedBy(changeset);
        wayNodeRepository.save(wayNode);
    }

    public WayNode unlockWayNode(int id) throws NoSuchElementException {
        WayNode wayNode = getWayNodeById(id);
        wayNode.setBlockedBy(null);
        return wayNodeRepository.save(wayNode);
    }

    public WayNode unlockWayNode(WayNode wayNode) {
        wayNode.setBlockedBy(null);
        return wayNodeRepository.save(wayNode);
    }

    public List<WayNodeDTO> getWayNodesOnScreen(double maxX, double minX, double maxY, double minY) {
        List<WayNode> wayNodes = wayNodeRepository.findWayNodesWithNodeOnScreen(minX, maxX, minY, maxY);
        return wayNodes.stream().map(mapper).toList();
    }
}

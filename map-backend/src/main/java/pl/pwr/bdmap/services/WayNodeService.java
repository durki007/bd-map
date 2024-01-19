package pl.pwr.bdmap.services;


import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dao.WayNodeRepository;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.dto.WayNodeDTOMapper;
import pl.pwr.bdmap.exceptions.NotFoundException;
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

    public WayNodeService(WayRepository wayRepository, NodeRepository nodeRepository, WayNodeRepository wayNodeRepository, HistoricWayNodeService historicWayNodeService, WayNodeDTOMapper mapper) {
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
            throw new InvalidAttributesException("Missing required fields in way_node " + wayNodeDTO.toString());
        }

        if(wayNodeDTO.node1Id() == wayNodeDTO.node2Id()){
            throw new InvalidAttributesException("Invalid (the same) nodeId fields in way_node " + wayNodeDTO.toString());
        }

        if (!wayNodeRepository.findAllByNode1_IdAndNode2_IdAndWay_IdOrNode1_IdAndNode2_IdAndWay_Id(
                wayNodeDTO.node1Id(), wayNodeDTO.node2Id(), wayNodeDTO.wayId(),
                wayNodeDTO.node2Id(), wayNodeDTO.node1Id(), wayNodeDTO.wayId()
        ).isEmpty()) {
            throw new InvalidAttributesException("There is already such WayNode in the database " + wayNodeDTO.toString());
        }



        WayNode wayNode = new WayNode();
        wayNode.setBlocked(false);
        // Check if nodes and way exist
        var way = wayRepository.findById(wayNodeDTO.wayId())
                .orElseThrow(() -> new NotFoundException("Way_node:" + wayNodeDTO.toString() + " has reference to not existing Way with id: " + wayNodeDTO.wayId()));
        var node1 = nodeRepository.findById(wayNodeDTO.node1Id())
                .orElseThrow(() -> new NotFoundException("Way_node: " + wayNodeDTO.toString() + " has reference to not existing Node1 with id: " + wayNodeDTO.node1Id()));
        var node2 = nodeRepository.findById(wayNodeDTO.node2Id())
                .orElseThrow(() -> new NotFoundException("Way_node: " + wayNodeDTO.toString() + " has reference to not existing Node2 with id: " + wayNodeDTO.node2Id()));

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
                throw  new InvalidAttributesException("WayNodeDTO at index " + i + ": " + e.getMessage());
            }
        }
        return savedWayNodes;
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
        return wayNodeRepository.findById(id).orElseThrow();
    }
}

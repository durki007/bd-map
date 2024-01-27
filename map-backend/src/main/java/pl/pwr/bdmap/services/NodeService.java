package pl.pwr.bdmap.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.mappers.NodeDTOMapper;
import pl.pwr.bdmap.dto.UMPNodeDTO;
import pl.pwr.bdmap.exceptions.ListCreationException;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeType;

import javax.naming.directory.InvalidAttributesException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class NodeService {
    private final NodeRepository nodeRepository;
    private final HistoricNodeDataService historicNodeDataService;
    private final NodeTypeService nodeTypeService;
    private final NodeDTOMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public NodeService(NodeRepository repository, @Lazy HistoricNodeDataService historicNodeDataService, NodeTypeService nodeTypeService, NodeDTOMapper mapper, EntityManager entityManager) {
        this.nodeRepository = repository;
        this.historicNodeDataService = historicNodeDataService;
        this.nodeTypeService = nodeTypeService;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    public List<NodeDTO> list() {
        List<Node> list = new ArrayList<>();
        nodeRepository.findAll().forEach(list::add);
        return list.stream().map(mapper).toList();
    }

    public NodeDTO save(NodeDTO nodeDTO) throws RuntimeException {
        try {
            Node node = new Node();

            node.setPosX(nodeDTO.posX());
            node.setPosY(nodeDTO.posY());
            node.setBlockedBy(null);

            if (nodeDTO.nodeType() == null) {
                node.setNodeType(nodeTypeService.save("default"));
            } else {
                node.setNodeType(nodeTypeService.save(nodeDTO.nodeType()));
            }

            node = nodeRepository.save(node);

            historicNodeDataService.saveInitialVersion(node);
            return mapper.apply(node);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save node: " + e.getMessage(), e);
        }
    }

    public List<Node> saveAll(List<UMPNodeDTO> nodeDTOs) throws ListCreationException {
        List<Node> savedNodes = new ArrayList<>();
        for (int i = 0; i < nodeDTOs.size(); i++) {
            try {
                savedNodes.add(save(nodeDTOs.get(i)));
            } catch (Exception e) {
                throw new ListCreationException(i);
            }
        }
        return savedNodes;
    }

    public Node save(UMPNodeDTO nodeDTO) throws DataAccessException {
        Node node = new Node();
        node.setPosX(nodeDTO.lat());
        node.setPosY(nodeDTO.lon());
        if (nodeDTO.nodeType() == null) {
            node.setNodeType(nodeTypeService.save("default"));
        } else {
            node.setNodeType(nodeTypeService.save(nodeDTO.nodeType()));
        }
        node = nodeRepository.save(node);
        historicNodeDataService.saveInitialVersion(node);
        return node;
    }

    public List<NodeDTO> save(List<NodeDTO> nodeDTOs) {
        List<NodeDTO> savedNodes = new ArrayList<>();
        Map<NodeDTO, Exception> failedNodes = new HashMap<>();

        for (NodeDTO nodeDTO : nodeDTOs) {
            try {
                savedNodes.add(save(nodeDTO));
            } catch (Exception e) {
                failedNodes.put(nodeDTO, e);
            }
        }

        for (Map.Entry<NodeDTO, Exception> entry : failedNodes.entrySet()) {
            NodeDTO failedNode = entry.getKey();
            Exception exception = entry.getValue();
            System.out.println("Failed to save node: " + failedNode + ". Exception: " + exception.getMessage());
        }

        return savedNodes;
    }

    public NodeDTO delete(int id) throws NoSuchElementException {
        Node node = nodeRepository.findById(id).orElseThrow();
        nodeRepository.delete(node);
        return mapper.apply(node);
    }

    public Node getNodeById(int id) throws NoSuchElementException {
        return nodeRepository.findById(id).orElseThrow();
    }

    public void save(Node node) {
        nodeRepository.save(node);
    }

    public void update(Node node, NodeDTO dtoNew) throws InvalidAttributesException {

        if (dtoNew.nodeType() == null && dtoNew.posX() == 0 && dtoNew.posY() == 0) {
            throw new InvalidAttributesException("Invalid attributes for updating: " + dtoNew);
        }

        if (dtoNew.nodeType() != null) {
            node.setNodeType(nodeTypeService.save(dtoNew.nodeType()));
        }
        if (dtoNew.posY() != 0) {
            node.setPosY(dtoNew.posY());
        }
        if (dtoNew.posX() != 0) {
            node.setPosX(dtoNew.posX());
        }

        node.setTimestamp(new Timestamp(System.currentTimeMillis()));

        nodeRepository.save(node);
    }


    public NodeType getNodeType(int id) throws NoSuchElementException {
        Node node = nodeRepository.findById(id).orElseThrow();
        return node.getNodeType();
    }

    public void blockNode(Integer nodeId, Changeset changeset) throws NoSuchElementException {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new NoSuchElementException("Node with id " + nodeId + " not found"));
        node.setBlockedBy(changeset);
        nodeRepository.save(node);
    }

    public List<NodeDTO> getNodesOnScreen(double maxX, double minX, double maxY, double minY) {
        List<Node> nodes = nodeRepository.findNodesInsideSquare(minX, maxX, minY, maxY);
        return nodes.stream().map(mapper).toList();
    }
}

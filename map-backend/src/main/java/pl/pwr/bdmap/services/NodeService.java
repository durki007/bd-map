package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dao.NodeTypeRepository;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.NodeDTOMapper;
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
    private final NodeTypeRepository nodeTypeRepository;

    @Autowired
    public NodeService(NodeRepository repository, HistoricNodeDataService historicNodeDataService, NodeTypeService nodeTypeService, NodeDTOMapper mapper, NodeTypeRepository nodeTypeRepository) {
        this.nodeRepository = repository;
        this.historicNodeDataService = historicNodeDataService;
        this.nodeTypeService = nodeTypeService;
        this.mapper = mapper;
        this.nodeTypeRepository = nodeTypeRepository;
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

    public List<NodeDTO> save(List<NodeDTO> nodeDTOs) {
        List<NodeDTO> savedNodes = new ArrayList<>();
        Map<NodeDTO, Exception> failedNodes = new HashMap<>();

        for (NodeDTO nodeDTO : nodeDTOs) {
            try {
                savedNodes.add(save(nodeDTO));
            }
            catch (Exception e) {
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

        if(dtoNew.nodeType() == null && dtoNew.posX() == 0 && dtoNew.posY() == 0) {
            throw new InvalidAttributesException("Invalid attributes for updating: " + dtoNew);
        }

        if(dtoNew.nodeType() != null) {
            node.setNodeType(nodeTypeService.save(dtoNew.nodeType()));
        }
        if(dtoNew.posY() != 0) {
            node.setPosY(dtoNew.posY());
        }
        if(dtoNew.posX() != 0) {
            node.setPosX(dtoNew.posX());
        }

        node.setTimestamp(new Timestamp(System.currentTimeMillis()));

        nodeRepository.save(node);
    }


    public NodeType getNodeType(int id) throws NoSuchElementException {
        Node node = nodeRepository.findById(id).orElseThrow();

        return node.getNodeType();
    }
}

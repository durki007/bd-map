package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.NodeDTOMapper;
import pl.pwr.bdmap.model.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class NodeService {
    private final NodeRepository nodeRepository;
    private final HistoricNodeDataService historicNodeDataService;
    private final NodeTypeService nodeTypeService;
    private final NodeDTOMapper mapper;

    @Autowired
    public NodeService(NodeRepository repository, HistoricNodeDataService historicNodeDataService, NodeTypeService nodeTypeService, NodeDTOMapper mapper) {
        this.nodeRepository = repository;
        this.historicNodeDataService = historicNodeDataService;
        this.nodeTypeService = nodeTypeService;
        this.mapper = mapper;
    }

    public List<NodeDTO> list() {
        List<Node> list = new ArrayList<Node>();
        nodeRepository.findAll().forEach(list::add);
        return list.stream().map(mapper).collect(Collectors.toList());
    }
    public NodeDTO save(NodeDTO nodeDTO) {
        Node node = new Node();
        // Map known fields
        node.setPosX(nodeDTO.posX());
        node.setPosY(nodeDTO.posY());
        node.setIsBlocked(false);
        // Default node type
        if (nodeDTO.nodeType() == null) {
            node.setNodeType(nodeTypeService.save("default"));
        } else {
            node.setNodeType(nodeTypeService.save(nodeDTO.nodeType()));
        }
        // Save node
        node = nodeRepository.save(node);
        // Save initial version
        historicNodeDataService.saveInitialVersion(node);
        return mapper.apply(node);
    }

    public List<NodeDTO> save(List<NodeDTO> nodeDTOs) {
        List<NodeDTO> savedNodes = new ArrayList<>();
        for (NodeDTO nodeDTO : nodeDTOs) {
            savedNodes.add(save(nodeDTO));
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
}

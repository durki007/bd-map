package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeTypeRepository;
import pl.pwr.bdmap.model.NodeType;

import java.util.NoSuchElementException;

@Service
public class NodeTypeService {
    private final NodeTypeRepository nodeTypeRepository;

    @Autowired
    public NodeTypeService(NodeTypeRepository nodeTypeRepository) {
        this.nodeTypeRepository = nodeTypeRepository;
    }

    NodeType save(NodeType nodeType) {
        return nodeTypeRepository.save(nodeType);
    }


    NodeType save(String type) {
        if (nodeTypeRepository.findByType(type) == null) {
            NodeType nodeType = new NodeType();
            nodeType.setType(type);
            return nodeTypeRepository.save(nodeType);
        } else {
            return nodeTypeRepository.findByType(type);
        }
    }

    public String getType(int id) throws NoSuchElementException {
        System.out.println("TEEST");
        NodeType nodeType = nodeTypeRepository.findById(id).orElseThrow();
        return nodeType.getType();
    }

}

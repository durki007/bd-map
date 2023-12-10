package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeTypeRepository;
import pl.pwr.bdmap.model.NodeType;

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

}

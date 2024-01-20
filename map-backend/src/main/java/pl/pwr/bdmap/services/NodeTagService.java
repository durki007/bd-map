package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeTagRepository;
import pl.pwr.bdmap.model.NodeTag;

import java.util.List;

@Service
public class NodeTagService {

    private final NodeTagRepository nodeTagRepository;

    public NodeTagService(NodeTagRepository nodeTagRepository) {
        this.nodeTagRepository = nodeTagRepository;
    }

    public List<String> getTags(int id) {
        List<NodeTag> nodeTags = nodeTagRepository.findAllByNodeId(id);

        // extracting string name from key associated with tag
        return nodeTags.stream()
                .map(nodeTag -> nodeTag.getKey().getValue())
                .toList();
    }


}

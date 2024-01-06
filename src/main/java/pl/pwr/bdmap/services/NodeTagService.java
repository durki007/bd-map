package pl.pwr.bdmap.services;

import pl.pwr.bdmap.dao.NodeTagRepository;
import pl.pwr.bdmap.model.NodeTag;

import java.util.Collections;
import java.util.List;

public class NodeTagService {

    private final NodeTagRepository nodeTagRepository;

    public NodeTagService(NodeTagRepository nodeTagRepository) {
        this.nodeTagRepository = nodeTagRepository;
    }

    public List<String> getTags(int id) {

        List<NodeTag> nodeTags = nodeTagRepository.findAllByNodeId(id);             // TODO: there should be try catch cuz .orElseThrow() is not working

        // extracting string name from key associated with tag
        List<String> tags = nodeTags.stream()
                .map(nodeTag -> nodeTag.getKey().getValue())
                .toList();

        return tags;
    }




}

package pl.pwr.bdmap.dto.mappers;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Node;

import java.util.ArrayList;
import java.util.function.Function;

@Service
public class NodeDTOMapper implements Function<Node, NodeDTO> {
    private final KeyDTOMapper keyDTOMapper = new KeyDTOMapper();

    @Override
    public NodeDTO apply(Node node) {
        int blockedById = 0;
        if (node.getBlockedBy() != null) {
            blockedById = node.getBlockedBy().getId();
        }
        var keys = new ArrayList<Key>();
        node.getNodeTag().forEach(nodeTag -> keys.add(nodeTag.getKey()));
        return new NodeDTO(
                node.getId(),
                node.getPosX(),
                node.getPosY(),
                blockedById,
                node.getTimestamp(),
                node.getNodeType().getType(),
                keys.stream().map(keyDTOMapper).toList()
        );
    }
}

package pl.pwr.bdmap.dto;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.Node;

import java.util.function.Function;

@Service
public class NodeDTOMapper implements Function<Node, NodeDTO> {
    @Override
    public NodeDTO apply(Node node) {
        return new NodeDTO(
                node.getId(),
                node.getPosX(),
                node.getPosY(),
                node.getIsBlocked(),
                node.getTimestamp(),
                node.getNodeType().getType()
        );
    }
}

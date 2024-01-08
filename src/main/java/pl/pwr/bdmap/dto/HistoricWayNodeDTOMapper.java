package pl.pwr.bdmap.dto;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.HistoricWayNode;

@Service
public class HistoricWayNodeDTOMapper implements Function<HistoricWayNode, HistoricWayNodeDTO> {
    @Override
    public HistoricWayNodeDTO apply(HistoricWayNode historicWayNode) {
        return new HistoricWayNodeDTO(
                historicWayNode.getId(),
                historicWayNode.getTimestamp(),
                historicWayNode.getWay().getId(),
                historicWayNode.getNode1().getId(),
                historicWayNode.getNode2().getId()
        );
    }
}

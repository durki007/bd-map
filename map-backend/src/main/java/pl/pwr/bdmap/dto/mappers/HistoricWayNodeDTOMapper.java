package pl.pwr.bdmap.dto.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.HistoricWayNodeDTO;
import pl.pwr.bdmap.model.HistoricWayNode;

@Service
public class HistoricWayNodeDTOMapper implements Function<HistoricWayNode, HistoricWayNodeDTO> {
    @Override
    public HistoricWayNodeDTO apply(HistoricWayNode historicWayNode) {
        return new HistoricWayNodeDTO(
                historicWayNode.getId(),
                historicWayNode.getTimestamp(),
                historicWayNode.getHistoricWayData().getId(),
                historicWayNode.getNode1().getId(),
                historicWayNode.getNode2().getId()
        );
    }
}

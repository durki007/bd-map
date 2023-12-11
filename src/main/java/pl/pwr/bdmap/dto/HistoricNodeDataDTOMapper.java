package pl.pwr.bdmap.dto;

import pl.pwr.bdmap.model.HistoricNodeData;

import java.util.function.Function;

public class HistoricNodeDataDTOMapper implements Function<HistoricNodeData, HistoricNodeDataDTO> {
    @Override
    public HistoricNodeDataDTO apply(HistoricNodeData historicNodeData) {
        return new HistoricNodeDataDTO(
                historicNodeData.getId(),
                historicNodeData.getPosX(),
                historicNodeData.getPosY(),
                historicNodeData.getTimestamp(),
                historicNodeData.getNode().getId()
        );
    }
}

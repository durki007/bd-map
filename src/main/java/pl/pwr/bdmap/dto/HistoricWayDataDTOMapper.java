package pl.pwr.bdmap.dto;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.model.HistoricWayData;

import java.util.function.Function;

@Service
public class HistoricWayDataDTOMapper implements Function<HistoricWayData, HistoricWayDataDTO> {
    @Override
    public HistoricWayDataDTO apply(HistoricWayData historicWayData) {
        return new HistoricWayDataDTO(
                historicWayData.getId(),
                historicWayData.getName(),
                historicWayData.getTimestamp(),
                historicWayData.getWay().getId()
        );
    }
}

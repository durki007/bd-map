package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.*;
import pl.pwr.bdmap.model.*;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class EditorService {
    private final NodeService nodeService;
    private final WayService wayService;
    private final ChangesetService changesetService;
    private final HistoricNodeDataService historicNodeDataService;

    private final HistoricNodeDataDTOMapper historicNodeDataDTOMapper;
    private final HistoricWayDataDTOMapper historicWayDataDTOMapper;

    public EditorService(NodeService nodeService, WayService wayService, ChangesetService changesetService, HistoricNodeDataService historicNodeDataService, HistoricNodeDataDTOMapper historicNodeDataDTOMapper, HistoricWayDataDTOMapper historicWayDataDTOMapper) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.changesetService = changesetService;
        this.historicNodeDataService = historicNodeDataService;
        this.historicNodeDataDTOMapper = historicNodeDataDTOMapper;
        this.historicWayDataDTOMapper = historicWayDataDTOMapper;
    }

    public HistoricNodeDataDTO updateNode(int nodeId, int changesetId, NodeDTO newNode) throws NoSuchElementException {
        Node node = nodeService.getNodeById(nodeId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        Set<HistoricNodeData> historicNodeData = node.getHistoricNodeData();
        HistoricNodeData historicNodeDataEntry = new HistoricNodeData();
        // Apply changes to the node
        node.setPosX(newNode.posX());
        node.setPosY(newNode.posY());
        // TODO: Change NodeType
        // Create new historic node data entry
        historicNodeDataEntry.setNode(node);
        historicNodeDataEntry.setPosX(newNode.posX());
        historicNodeDataEntry.setPosY(newNode.posY());
        historicNodeDataEntry.setChangeset(changeset);
        historicNodeDataEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicNodeData.add(historicNodeDataEntry);
        nodeService.save(node);
        // Return Changeset DTO
        return historicNodeDataDTOMapper.apply(historicNodeDataEntry);
    }

    public HistoricWayDataDTO updateWay(int wayId, int changesetId, WayDTO newWay) throws NoSuchElementException {
        Way way = wayService.getWayById(wayId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        Set<HistoricWayData> historicWayData = way.getHistoricWayData();
        HistoricWayData historicWayDataEntry = new HistoricWayData();
        // Apply changes to the way
        way.setName(newWay.name());
        // TODO: Change WayType
        // Create new historic way data entry
        historicWayDataEntry.setWay(way);
        historicWayDataEntry.setName(newWay.name());
        historicWayDataEntry.setChangeset(changeset);
        historicWayDataEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayData.add(historicWayDataEntry);
        wayService.save(way);
        // Return HistoricWay DTO
        return historicWayDataDTOMapper.apply(historicWayDataEntry);
    }
}

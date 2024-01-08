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

    private final WayNodeService wayNodeService;
    private final HistoricNodeDataService historicNodeDataService;

    private final HistoricNodeDataDTOMapper historicNodeDataDTOMapper;
    private final HistoricWayDataDTOMapper historicWayDataDTOMapper;
    private final HistoricWayNodeDTOMapper historicWayNodeDTOMapper;

    public EditorService(NodeService nodeService, WayService wayService, ChangesetService changesetService, WayNodeService wayNodeService, HistoricNodeDataService historicNodeDataService, HistoricNodeDataDTOMapper historicNodeDataDTOMapper, HistoricWayDataDTOMapper historicWayDataDTOMapper, HistoricWayNodeDTOMapper historicWayNodeDTOMapper) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.changesetService = changesetService;
        this.wayNodeService = wayNodeService;
        this.historicNodeDataService = historicNodeDataService;
        this.historicNodeDataDTOMapper = historicNodeDataDTOMapper;
        this.historicWayDataDTOMapper = historicWayDataDTOMapper;
        this.historicWayNodeDTOMapper = historicWayNodeDTOMapper;
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

    public HistoricWayNodeDTO updateWayNode(int id, int changsetId, WayNodeDTO newWayNode ) throws NoSuchElementException {
        WayNode wayNode = wayNodeService.getWayNodeById(id); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changsetId); // Throws NoSuchElementException
        Set<HistoricWayNode> historicWayNode = wayNode.getHistoricWayNode();
        HistoricWayNode historicWayNodeEntry = new HistoricWayNode();
        // Apply changes to the way node
        wayNode.setBlocked(newWayNode.isBlocked());
        // Create new historic way node entry
        historicWayNodeEntry.setWayNode(wayNode);
        historicWayNodeEntry.setChangeset(changeset);
        historicWayNodeEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayNode.add(historicWayNodeEntry);
        wayNodeService.save(wayNode);
        // Return HistoricWayNode DT
        return historicWayNodeDTOMapper.apply(historicWayNodeEntry);
    }

}

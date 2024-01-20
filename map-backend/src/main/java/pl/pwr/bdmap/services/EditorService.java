package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.*;
import pl.pwr.bdmap.model.*;

import javax.naming.directory.InvalidAttributesException;
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
    private final HistoricWayDataService historicWayDataService;

    private final HistoricNodeDataDTOMapper historicNodeDataDTOMapper;
    private final HistoricWayDataDTOMapper historicWayDataDTOMapper;
    private final HistoricWayNodeDTOMapper historicWayNodeDTOMapper;

    public EditorService(NodeService nodeService, WayService wayService, ChangesetService changesetService, WayNodeService wayNodeService, HistoricNodeDataService historicNodeDataService, HistoricWayDataService historicWayDataService, HistoricNodeDataDTOMapper historicNodeDataDTOMapper, HistoricWayDataDTOMapper historicWayDataDTOMapper, HistoricWayNodeDTOMapper historicWayNodeDTOMapper) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.changesetService = changesetService;
        this.wayNodeService = wayNodeService;
        this.historicNodeDataService = historicNodeDataService;
        this.historicWayDataService = historicWayDataService;
        this.historicNodeDataDTOMapper = historicNodeDataDTOMapper;
        this.historicWayDataDTOMapper = historicWayDataDTOMapper;
        this.historicWayNodeDTOMapper = historicWayNodeDTOMapper;
    }

    public HistoricNodeDataDTO updateNode(int nodeId, int changesetId, NodeDTO newNode) throws NoSuchElementException, InvalidAttributesException {
        Node node = nodeService.getNodeById(nodeId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        Set<HistoricNodeData> historicNodeData = node.getHistoricNodeData();
        HistoricNodeData historicNodeDataEntry = new HistoricNodeData();

        // Create new historic node data entry
        historicNodeDataEntry.setNode(node);
        historicNodeDataEntry.setPosX(node.getPosX());
        historicNodeDataEntry.setPosY(node.getPosY());
        historicNodeDataEntry.setChangeset(changeset);
        historicNodeDataEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicNodeData.add(historicNodeDataEntry);
        nodeService.update(node, newNode);
        // Return Changeset DTO
        return historicNodeDataDTOMapper.apply(historicNodeDataEntry);
    }

    public HistoricWayDataDTO updateWay(int wayId, int changesetId, WayDTO newWay) throws NoSuchElementException, InvalidAttributesException {
        Way way = wayService.getWayById(wayId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        Set<HistoricWayData> historicWayData = way.getHistoricWayData();
        HistoricWayData historicWayDataEntry = new HistoricWayData();

        // Create new historic way data entry
        historicWayDataEntry.setWay(way);
        historicWayDataEntry.setName(way.getName());
        historicWayDataEntry.setChangeset(changeset);
        historicWayDataEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayData.add(historicWayDataEntry);
        wayService.update(way, newWay); // throws InvalidAttributesException
        // Return HistoricWay DTO
        return historicWayDataDTOMapper.apply(historicWayDataEntry);
    }

    public HistoricWayNodeDTO updateWayNode(int id, int changsetId, WayNodeDTO newWayNode) throws NoSuchElementException, InvalidAttributesException {
        WayNode wayNode = wayNodeService.getWayNodeById(id); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changsetId); // Throws NoSuchElementException
        Set<HistoricWayNode> historicWayNode = wayNode.getHistoricWayNode();
        HistoricWayNode historicWayNodeEntry = new HistoricWayNode();
        // Apply changes to the way node
        wayNode.setBlocked(newWayNode.isBlocked());
        // Create new historic way node entry
        historicWayNodeEntry.setWayNode(wayNode);
        historicWayNodeEntry.setHistoricWayData(historicWayDataService.findInitialVersionByWayId(wayNode.getWay().getId()));
        historicWayNodeEntry.setNode1(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode1().getId()));
        historicWayNodeEntry.setNode2(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode2().getId()));
        historicWayNodeEntry.setChangeset(changeset);
        historicWayNodeEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayNode.add(historicWayNodeEntry);
        wayNodeService.update(wayNode, newWayNode);


        // Return HistoricWayNode DTO
        return historicWayNodeDTOMapper.apply(historicWayNodeEntry);
    }

}

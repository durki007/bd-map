package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.*;
import pl.pwr.bdmap.exceptions.BlockedElementException;
import pl.pwr.bdmap.exceptions.ChangesetClosedException;
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

    public HistoricNodeDataDTO updateNode(int nodeId, int changesetId, NodeDTO newNode) throws NoSuchElementException, InvalidAttributesException, BlockedElementException, ChangesetClosedException {
        Node node = nodeService.getNodeById(nodeId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        // Check if changeset is closed
        if (changeset.getCloseDate() != null) {
            throw new ChangesetClosedException("Changeset is closed");
        }
        // Check if node is blocked
        if (node.getBlockedBy() != null && node.getBlockedBy().getId() != changesetId) {
            throw new BlockedElementException("Node is blocked by changeset " + node.getBlockedBy().getId());
        }
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
        // If node was not blocked, block it
        if (node.getBlockedBy() == null) {
            node.setBlockedBy(changeset);
        }
        nodeService.update(node, newNode);
        // Return Changeset DTO
        return historicNodeDataDTOMapper.apply(historicNodeDataEntry);
    }

    public HistoricWayDataDTO updateWay(int wayId, int changesetId, WayDTO newWay) throws NoSuchElementException, InvalidAttributesException, BlockedElementException, ChangesetClosedException {
        Way way = wayService.getWayById(wayId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        // Check if changeset is closed
        if (changeset.getCloseDate() != null) {
            throw new ChangesetClosedException("Changeset is closed");
        }
        // Check if way is blocked
        if (way.getBlockedBy() != null && way.getBlockedBy().getId() != changesetId) {
            throw new BlockedElementException("Way is blocked by changeset " + way.getBlockedBy().getId());
        }
        Set<HistoricWayData> historicWayData = way.getHistoricWayData();
        HistoricWayData historicWayDataEntry = new HistoricWayData();

        // Create new historic way data entry
        historicWayDataEntry.setWay(way);
        historicWayDataEntry.setName(way.getName());
        historicWayDataEntry.setChangeset(changeset);
        historicWayDataEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayData.add(historicWayDataEntry);
        // If way was not blocked, block it
        if (way.getBlockedBy() == null) {
            way.setBlockedBy(changeset);
        }
        wayService.update(way, newWay); // throws InvalidAttributesException
        // Return HistoricWay DTO
        return historicWayDataDTOMapper.apply(historicWayDataEntry);
    }

    public HistoricWayNodeDTO updateWayNode(int id, int changesetId, WayNodeDTO newWayNode) throws NoSuchElementException, InvalidAttributesException, BlockedElementException, ChangesetClosedException {
        WayNode wayNode = wayNodeService.getWayNodeById(id); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        // Check if changeset is closed
        if (changeset.getCloseDate() != null) {
            throw new ChangesetClosedException("Changeset is closed");
        }
        // Check if way node is blocked and blocker is not changesetId
        if (wayNode.getBlockedBy() != null && wayNode.getBlockedBy().getId() != changesetId) {
            throw new BlockedElementException("Way node is blocked by changeset " + wayNode.getBlockedBy().getId());
        }
        Set<HistoricWayNode> historicWayNode = wayNode.getHistoricWayNode();
        HistoricWayNode historicWayNodeEntry = new HistoricWayNode();

        // Create new historic way node entry
        historicWayNodeEntry.setWayNode(wayNode);
        historicWayNodeEntry.setHistoricWayData(historicWayDataService.findInitialVersionByWayId(wayNode.getWay().getId()));
        historicWayNodeEntry.setNode1(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode1().getId()));
        historicWayNodeEntry.setNode2(historicNodeDataService.findInitialVersionByNodeId(wayNode.getNode2().getId()));
        historicWayNodeEntry.setChangeset(changeset);
        historicWayNodeEntry.setTimestamp(new Timestamp(System.currentTimeMillis()));
        // Save
        historicWayNode.add(historicWayNodeEntry);
        // If way node was not blocked, block it
        if (wayNode.getBlockedBy() == null) {
            wayNode.setBlockedBy(changeset);
        }
        wayNodeService.update(wayNode, newWayNode);
        // Return HistoricWayNode DTO
        return historicWayNodeDTOMapper.apply(historicWayNodeEntry);
    }

}

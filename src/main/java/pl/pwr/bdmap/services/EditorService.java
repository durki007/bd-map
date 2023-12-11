package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dto.HistoricNodeDataDTO;
import pl.pwr.bdmap.dto.HistoricNodeDataDTOMapper;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.HistoricNodeData;
import pl.pwr.bdmap.model.Node;

import java.sql.Timestamp;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class EditorService {
    private final NodeService nodeService;
    private final ChangesetService changesetService;
    private final HistoricNodeDataService historicNodeDataService;

    private final HistoricNodeDataDTOMapper historicNodeDataDTOMapper;

    public EditorService(NodeService nodeService, ChangesetService changesetService, HistoricNodeDataService historicNodeDataService, HistoricNodeDataDTOMapper historicNodeDataDTOMapper) {
        this.nodeService = nodeService;
        this.changesetService = changesetService;
        this.historicNodeDataService = historicNodeDataService;
        this.historicNodeDataDTOMapper = historicNodeDataDTOMapper;
    }

    public HistoricNodeDataDTO updateNode(int nodeId, int changesetId, NodeDTO newNode) throws NoSuchElementException {
        Node node = nodeService.getNodeById(nodeId); // Throws NoSuchElementException
        Changeset changeset = changesetService.getChangeSetById(changesetId); // Throws NoSuchElementException
        Set<HistoricNodeData> historicNodeData = node.getHistoricNodeData();
        HistoricNodeData historicNodeDataEntry = new HistoricNodeData();
        // Apply changes to the node
        node.setPosX(newNode.posX());
        node.setPosY(newNode.posY());
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
}

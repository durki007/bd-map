package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.NodeRepository;
import pl.pwr.bdmap.dao.WayNodeRepository;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.dto.WayNodeDTOMapper;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.model.WayNode;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WayNodeService {
    private final WayRepository wayRepository;
    private final NodeRepository nodeRepository;
    private final WayNodeRepository wayNodeRepository;

    private final HistoricWayNodeService historicWayNodeService;


    private final WayNodeDTOMapper mapper;

    public WayNodeService(WayRepository wayRepository, NodeRepository nodeRepository, WayNodeRepository wayNodeRepository, HistoricNodeDataService historicNodeDataService, HistoricWayNodeService historicNodeDataService1, HistoricWayNodeService historicWayNodeService, WayNodeDTOMapper mapper) {
        this.wayRepository = wayRepository;
        this.nodeRepository = nodeRepository;
        this.wayNodeRepository = wayNodeRepository;
        this.historicWayNodeService = historicWayNodeService;
        this.mapper = mapper;
    }

    public WayNode save(WayNode wayNode) {
        return wayNodeRepository.save(wayNode);
    }

    public WayNodeDTO save(WayNodeDTO wayNodeDTO) throws NoSuchElementException {
        WayNode wayNode = new WayNode();
        wayNode.setBlocked(false);
        // Check if nodes exist
        var way = wayRepository.findById(wayNodeDTO.wayId()).orElseThrow();
        var node1 = nodeRepository.findById(wayNodeDTO.node1Id()).orElseThrow();
        var node2 = nodeRepository.findById(wayNodeDTO.node2Id()).orElseThrow();
        wayNode.setWay(way);
        wayNode.setNode1(node1);
        wayNode.setNode2(node2);
        // Save wayNode
        wayNodeRepository.save(wayNode);
        historicWayNodeService.saveInitialVersion(wayNode);
        // Save initial version
        return mapper.apply(wayNode);
    }

    public List<WayNode> getAll() {
        var a = wayNodeRepository.findAll();
        List<WayNode> wn = new ArrayList<>();
        a.forEach(wn::add);
        return wn;
    }

    public WayNodeDTO removeById(int id) throws NoSuchElementException {
        WayNode wayNode = wayNodeRepository.findById(id).orElseThrow();
        wayNodeRepository.deleteById(id);
        return mapper.apply(wayNode);
    }

    // Returns all Ways that contain given node
    public List<Way> getWaysByNodeId(int nodeId) {
        return wayNodeRepository.findAllByNode1_IdOrNode2_Id(nodeId, nodeId).stream().map(WayNode::getWay).toList();
    }

    public WayNode getWayNodeById(int id) throws NoSuchElementException {
        return wayNodeRepository.findById(id).orElseThrow();
    }
}

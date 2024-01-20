package pl.pwr.bdmap.init;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;
import pl.pwr.bdmap.dao.*;
import pl.pwr.bdmap.model.*;
import pl.pwr.bdmap.services.*;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit implements InitializingBean {

    @Value("${spring.datasource.initialize}")
    boolean initialize;

    private final HistoricNodeDataRepository historicNodeDataRepository;
    private final HistoricWayNodeRepository historicWayNodeRepository;
    private final HistoricWayDataRepository historicWayDataRepository;
    private NodeRepository nodeRepository;
    private final WayRepository wayRepository;
    private final WayNodeRepository wayNodeRepository;
    private final NodeTypeRepository nodeTypeRepository;
    private final WayTypeRepository wayTypeRepository;
    private final KeyWayTypeRepository keyWayTypeRepository;
    private final KeyNodeTypeRepository keyNodeTypeRepository;

    private final UserRepository userRepository;

    @Autowired
    public DataInit(HistoricNodeDataRepository historicNodeDataRepository, HistoricWayNodeRepository historicWayNodeRepository, HistoricWayDataRepository historicWayDataRepository, NodeRepository nodeRepository, WayRepository wayRepository, WayNodeRepository wayNodeRepository, NodeTypeRepository nodeTypeRepository, WayTypeRepository wayTypeRepository, KeyWayTypeRepository keyWayTypeRepository, KeyNodeTypeRepository keyNodeTypeRepository, UserRepository userRepository) {
        this.historicNodeDataRepository = historicNodeDataRepository;
        this.historicWayNodeRepository = historicWayNodeRepository;
        this.historicWayDataRepository = historicWayDataRepository;
        this.nodeRepository = nodeRepository;
        this.wayRepository = wayRepository;
        this.wayNodeRepository = wayNodeRepository;
        this.nodeTypeRepository = nodeTypeRepository;
        this.wayTypeRepository = wayTypeRepository;
        this.keyWayTypeRepository = keyWayTypeRepository;
        this.keyNodeTypeRepository = keyNodeTypeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!initialize)
            return;
//        initNodeTypes();
        initNodes();
        initWayTypes();
        initWays();
        initWayNodes();
        System.out.printf("Data initialized successfully\n");

    }
//rand czy normalne
    private void initNodeTypes() {
        NodeType nodeType = new NodeType();
        nodeType.setType("droga");
        nodeTypeRepository.save(nodeType);
    }

    private void initNodes() {
        NodeType nodeType = nodeTypeRepository.findAll().iterator().next();

        Node node1 = new Node();
        node1.setPosX(100);
        node1.setPosY(100);
        node1.setIsBlocked(false);
        node1.setTimestamp(new Timestamp(System.currentTimeMillis()));
        node1.setNodeType(nodeType);
        nodeRepository.save(node1);

        Node node2 = new Node(node1);
        node2.setPosX(200);
        nodeRepository.save(node2);

        Node node3 = new Node(node1);
        node3.setPosX(300);
        node3.setPosY(200);
        nodeRepository.save(node3);

        Node node4 = new Node(node1);
        node3.setPosX(350);
        node3.setPosY(200);
        nodeRepository.save(node4);
    }

    private void initWayTypes() {
        WayType wayType = new WayType();
        wayType.setType("test1");
        wayTypeRepository.save(wayType);

        WayType wayType2 = new WayType();
        wayType2.setType("test2");
        wayTypeRepository.save(wayType2);

    }
    private void initWays() {
        ArrayList<WayType> wayTypes = new ArrayList<>();
        wayTypeRepository.findAll().forEach(wayTypes::add);
        WayType wayType = wayTypes.getFirst();

        Way way = new Way();
        way.setIsBlocked(false);
        way.setName("droga1");
        way.setTimestamp(new Timestamp(System.currentTimeMillis()));
        way.setWayType(wayType);
        wayRepository.save(way);
    }

    private void initWayNodes() {
        ArrayList<Node> nodeList = new ArrayList<>();
        nodeRepository.findAll().forEach(nodeList::add);
        Way way = wayRepository.findAll().iterator().next();

        WayNode wayNode = new WayNode();
        wayNode.setNode1(nodeList.get(0));
        wayNode.setNode2(nodeList.get(1));
        wayNode.setBlocked(false);
        wayNode.setWay(way);
        wayNodeRepository.save(wayNode);

        WayNode wayNode1 = new WayNode();
        wayNode1.setNode1(nodeList.get(1));
        wayNode1.setNode2(nodeList.get(2));
        wayNode1.setWay(way);
        wayNodeRepository.save(wayNode1);

        WayNode wayNode2 = new WayNode();
        wayNode2.setNode1(nodeList.get(2));
        wayNode2.setNode2(nodeList.get(3));
        wayNode2.setWay(way);
        wayNodeRepository.save(wayNode2);

    }
}

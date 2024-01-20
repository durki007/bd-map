package pl.pwr.bdmap.init;

import org.hibernate.PersistentObjectException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Component;
import pl.pwr.bdmap.dao.*;
import pl.pwr.bdmap.dto.NodeDTO;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.model.*;
import pl.pwr.bdmap.services.*;

import javax.naming.directory.InvalidAttributesException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@DependsOn({"systemUserInit"})
public class DataInit implements InitializingBean {

    @Value("${spring.datasource.initialize:false}")
    boolean initialize;

    private final NodeService nodeService;
    private final WayService wayService;
    private final WayNodeService wayNodeService;
    private final WayRepository wayRepository;

    @Autowired
    public DataInit(NodeService nodeService, WayService wayService, WayNodeService wayNodeService, WayRepository wayRepository) {
        this.nodeService = nodeService;
        this.wayService = wayService;
        this.wayNodeService = wayNodeService;
        this.wayRepository = wayRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!initialize)
            return;
        initNodes();
        initWays();
        initWayNodes();
        System.out.print("Data initialized successfully\n");
    }


    private void initNodes() {
        nodeService.save(new NodeDTO(
                0,
                51.10955,
                17.06027,
                0,
                null,
                null
        ));

        nodeService.save(new NodeDTO(
                0,
                51.10925,
                17.06087,
                0,
                null,
                null
        ));

        nodeService.save(new NodeDTO(
                0,
                51.10950,
                17.06037,
                0,
                null,
                null
        ));

        nodeService.save(new NodeDTO(
                0,
                51.11955,
                17.16027,
                0,
                null,
                null
        ));

    }

    private void initWays() {
        wayService.save(new WayDTO(
                0,
                "droga1",
                0,
                null,
                null
        ));
        wayService.save(new WayDTO(
                0,
                "droga2",
                0,
                null,
                null
        ));
        wayService.save(new WayDTO(
                0,
                "droga3",
                0,
                null,
                null
        ));
    }

    private void initWayNodes() throws InvalidAttributesException {
        List<NodeDTO> nodeList = nodeService.list();
        int wayId = wayService.list().getFirst().id();

        wayNodeService.save(new WayNodeDTO(
                0,
                wayId,
                nodeList.get(0).id(),
                nodeList.get(1).id(),
                0
        ));

        wayNodeService.save(new WayNodeDTO(
                0,
                wayId,
                nodeList.get(1).id(),
                nodeList.get(2).id(),
                0
        ));

        wayNodeService.save(new WayNodeDTO(
                0,
                wayId,
                nodeList.get(3).id(),
                nodeList.get(4).id(),
                0
        ));
    }
}

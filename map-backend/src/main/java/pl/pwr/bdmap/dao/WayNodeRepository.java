package pl.pwr.bdmap.dao;

import org.springframework.data.repository.CrudRepository;
import pl.pwr.bdmap.model.WayNode;

import java.util.List;

public interface WayNodeRepository extends CrudRepository<WayNode, Integer> {

    List<WayNode> findAllByWay_Id(int id);

    List<WayNode> findAllByNode1_Id(int id);

    List<WayNode> findAllByNode2_Id(int id);

    List<WayNode> findAllByNode1_IdOrNode2_Id(int id1, int id2);

    List<WayNode> findAllByNode1_IdAndNode2_IdAndWay_IdOrNode1_IdAndNode2_IdAndWay_Id(int node1Id, int node2Id, int wayId, int node2Id1, int node1Id1, int wayId1);
}

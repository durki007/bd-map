package pl.pwr.bdmap.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeType;

import java.util.List;

public interface NodeRepository extends CrudRepository<Node, Integer> {
    @Query("SELECT n FROM Node n WHERE n.posX BETWEEN :minX AND :maxX " +
            "AND n.posY BETWEEN :minY AND :maxY")
    List<Node> findNodesInsideSquare(
            @Param("minX") double minX,
            @Param("maxX") double maxX,
            @Param("minY") double minY,
            @Param("maxY") double maxY
    );
}

package pl.pwr.bdmap.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.model.Way;

import java.util.List;

public interface WayRepository extends CrudRepository<Way, Integer> {
    @Query("SELECT DISTINCT w FROM Way w " +
            "JOIN w.wayNode wn " +
            "JOIN wn.node1 n1 " +
            "JOIN wn.node2 n2 " +
            "WHERE (n1.posX BETWEEN :minX AND :maxX AND n1.posY BETWEEN :minY AND :maxY) " +
            "   OR (n2.posX BETWEEN :minX AND :maxX AND n2.posY BETWEEN :minY AND :maxY)")
    List<Way> findWaysWithNodeOnScreen(
            @Param("minX") double minX,
            @Param("maxX") double maxX,
            @Param("minY") double minY,
            @Param("maxY") double maxY
    );
}

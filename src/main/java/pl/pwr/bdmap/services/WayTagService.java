package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayTagRepository;
import pl.pwr.bdmap.model.WayTag;

import java.util.List;

@Service
public class WayTagService {

    private final WayTagRepository wayTagRepository;

    @Autowired
    public WayTagService(WayTagRepository wayTagRepository) {
        this.wayTagRepository = wayTagRepository;
    }

    public List<String> getTags(int id) {
        List<WayTag> wayTags = wayTagRepository.findAllByWayId(id);

        List<String> tags = wayTags.stream().map(wayTag -> wayTag.getKey().getValue()).toList();
        return tags;
    }

}

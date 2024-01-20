package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayTypeRepository;
import pl.pwr.bdmap.model.WayType;

@Service
public class WayTypeService {
    private final WayTypeRepository wayTypeRepository;

    @Autowired
    public WayTypeService(WayTypeRepository wayTypeRepository) {
        this.wayTypeRepository = wayTypeRepository;
    }

    WayType save(WayType wayType){
        return wayTypeRepository.save(wayType);
    }

    /**
     * Creates new WayType only if there isn't an existing one
     * @param type
     * @return
     */
    WayType save(String type) {
        if (wayTypeRepository.findByType(type) == null){
            WayType wayType = new WayType();
            wayType.setType(type);
            return wayTypeRepository.save(wayType);
        } else {
            return wayTypeRepository.findByType(type);
        }
    }

}

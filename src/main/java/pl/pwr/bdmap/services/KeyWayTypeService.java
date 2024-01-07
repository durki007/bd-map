package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.KeyNodeTypeRepository;
import pl.pwr.bdmap.dao.KeyWayTypeRepository;
import pl.pwr.bdmap.dao.WayTypeRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyNodeType;
import pl.pwr.bdmap.model.KeyWayType;
import pl.pwr.bdmap.model.WayType;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class KeyWayTypeService {
    private final WayTypeRepository wayTypeRepository;
    private final KeyWayTypeRepository keyWayTypeRepository;


    public KeyWayTypeService(WayTypeRepository wayTypeRepository, KeyWayTypeRepository keyWayTypeRepository) {
        this.wayTypeRepository = wayTypeRepository;
        this.keyWayTypeRepository = keyWayTypeRepository;
    }

    public List<String> getAvailableKeys(WayType wayType) throws NoSuchElementException {

        List<KeyWayType> keyWayTypes = keyWayTypeRepository.findAllByWayTypeId(wayType.getId());

        List<String> keys = keyWayTypes.stream()
                .map(KeyWayType::getKey)
                .map(Key::getValue)
                .toList();

        return keys;
    }

    public String save(KeyWayType newKeyWayType) {
        keyWayTypeRepository.save(newKeyWayType);
        return newKeyWayType.toString();
    }
}

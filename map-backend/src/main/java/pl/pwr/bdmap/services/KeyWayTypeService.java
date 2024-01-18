package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.KeyWayTypeRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyWayType;
import pl.pwr.bdmap.model.WayType;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class KeyWayTypeService {
    private final KeyWayTypeRepository keyWayTypeRepository;


    public KeyWayTypeService(KeyWayTypeRepository keyWayTypeRepository) {
        this.keyWayTypeRepository = keyWayTypeRepository;
    }

    public List<String> getAvailableKeys(WayType wayType) throws NoSuchElementException {

        List<KeyWayType> keyWayTypes = keyWayTypeRepository.findAllByWayTypeId(wayType.getId());

        return keyWayTypes.stream()
                .map(KeyWayType::getKey)
                .map(Key::getValue)
                .toList();
    }

    public String save(KeyWayType newKeyWayType) {
        keyWayTypeRepository.save(newKeyWayType);
        return newKeyWayType.toString();
    }
}

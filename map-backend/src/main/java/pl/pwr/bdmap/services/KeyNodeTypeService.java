package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.KeyNodeTypeRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyNodeType;
import pl.pwr.bdmap.model.NodeType;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class KeyNodeTypeService {

    private final KeyNodeTypeRepository keyNodeTypeRepository;

    public KeyNodeTypeService(KeyNodeTypeRepository keyNodeTypeRepository) {
        this.keyNodeTypeRepository = keyNodeTypeRepository;
    }

    public List<String> getAvailableKeys(NodeType nodeType) throws NoSuchElementException {
        List<KeyNodeType> keyNodeTypes = keyNodeTypeRepository.findAllByNodeTypeId(nodeType.getId());

        return keyNodeTypes.stream()
                .map(KeyNodeType::getKey)
                .map(Key::getValue)
                .toList();
    }

    public String save(KeyNodeType newKeyNodeType) {
        keyNodeTypeRepository.save(newKeyNodeType);
        return newKeyNodeType.toString();
    }


}

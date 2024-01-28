package pl.pwr.bdmap.services;

import org.springframework.dao.DataAccessException;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.KeyRepository;
import pl.pwr.bdmap.dao.NodeTagRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Node;
import pl.pwr.bdmap.model.NodeTag;

import java.util.List;

@Service
public class KeyService {

    public final KeyRepository keyRepository;
    private final NodeTagRepository nodeTagRepository;

    public KeyService(KeyRepository keyRepository, NodeTagRepository nodeTagRepository) {
        this.keyRepository = keyRepository;
        this.nodeTagRepository = nodeTagRepository;
    }


    public boolean isKeyPresent(String keyName) {

        return keyRepository.findByValue(keyName) != null;

    }

    public Node addKeysToNode(Node node, List<Key> keys) throws DataAccessException {
        for (Key key : keys) {
            node = addKeyToNode(node, key);
        }
        return node;
    }

    public Node addKeyToNode(Node node, Key key) throws DataAccessException {
        key = keyRepository.save(key);
        NodeTag nodeTag = new NodeTag();
        nodeTag.setNode(node);
        nodeTag.setKey(key);
        nodeTagRepository.save(nodeTag);
        return node;
    }
}

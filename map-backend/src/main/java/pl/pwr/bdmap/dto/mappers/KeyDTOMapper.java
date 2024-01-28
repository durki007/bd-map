package pl.pwr.bdmap.dto.mappers;

import pl.pwr.bdmap.dto.KeyDTO;
import pl.pwr.bdmap.model.Key;

import java.util.function.Function;

public class KeyDTOMapper implements Function<Key, KeyDTO> {
    @Override
    public KeyDTO apply(Key key) {
        return new KeyDTO(key.getKey(), key.getValue());
    }
}

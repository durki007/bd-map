package pl.pwr.bdmap.services;

import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.KeyRepository;

@Service
public class KeyService {

    public final KeyRepository keyRepository;

    public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }


    /**
     * Returns true if keyName is present in Key table
     * @param keyName - name field of Key instance
     * @return
     */
    public boolean isKeyPresent(String keyName) {

        return keyRepository.findByValue(keyName) != null;

    }

}

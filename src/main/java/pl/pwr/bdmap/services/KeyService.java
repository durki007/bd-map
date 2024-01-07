package pl.pwr.bdmap.services;

import pl.pwr.bdmap.dao.KeyRepository;

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
        return (keyRepository.findByNodeValue(keyName));
    }

}

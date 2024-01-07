package pl.pwr.bdmap.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dao.KeyRepository;
import pl.pwr.bdmap.dao.KeyWayTypeRepository;
import pl.pwr.bdmap.dao.WayTypeRepository;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.KeyWayType;
import pl.pwr.bdmap.model.WayType;
import pl.pwr.bdmap.services.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class WayTagsController {
    private final WayService wayService;
    private final WayTypeService wayTypeService;
    private final WayTagService wayTagService;
    private final KeyService keyService;
    private final KeyWayTypeService keyWayTypeService;
    private final WayTypeRepository wayTypeRepository;
    private final KeyRepository keyRepository;
    private final KeyWayTypeRepository keyWayTypeRepository;


    public WayTagsController(WayService wayService, WayTypeService wayTypeService, WayTagService wayTagService, KeyService keyService, KeyWayTypeService keyWayTypeService, WayTypeRepository wayTypeRepository, KeyRepository keyRepository, KeyWayTypeRepository keyWayTypeRepository) {
        this.wayService = wayService;
        this.wayTypeService = wayTypeService;
        this.wayTagService = wayTagService;
        this.keyService = keyService;
        this.keyWayTypeService = keyWayTypeService;
        this.wayTypeRepository = wayTypeRepository;
        this.keyRepository = keyRepository;
        this.keyWayTypeRepository = keyWayTypeRepository;
    }

    @GetMapping("ways/{id}/getTags")
    List<String> wayTags(@PathVariable int id) {
        try{
            return wayTagService.getTags(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    @GetMapping("ways/{id}/getType")
    String wayTypes(@PathVariable int id) {
        try{
            return wayService.getWayType(id).getType();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }


    @GetMapping("ways/{id}/getAvailableKeys")
    List<String> getAvailableKeys(@PathVariable int id) {
        WayType wayType = wayService.getWayType(id);

        try{
            return  keyWayTypeService.getAvailableKeys(wayType);
        } catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }
    }

    @PutMapping("ways/type")
    String keysForType(@RequestParam("wayType") String wayTypeStr,
                       @RequestParam("keyName") String keyNameStr) {
        WayType wayType = wayTypeRepository.findByType(wayTypeStr);

        if(wayType == null){
            WayType newWayType = new WayType();
            newWayType.setType(wayTypeStr);
            wayType = wayTypeRepository.save(newWayType);
        }

        Key key = keyRepository.findByValue(keyNameStr);

        if (key == null) {
            Key newKey = new Key();
            newKey.setValue(keyNameStr);
            key = keyRepository.save(newKey);
        }

        KeyWayType existingKeyWayType = keyWayTypeRepository.findByKeyAndWayType(key, wayType);

        if(existingKeyWayType == null) {
            KeyWayType newKeyWayType = new KeyWayType();
            newKeyWayType.setKey(key);
            newKeyWayType.setWayType(wayType);
            keyWayTypeRepository.save(newKeyWayType);
            return keyWayTypeService.save(newKeyWayType);
        }

        return  keyWayTypeService.save(existingKeyWayType);
    }


}

package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dao.KeyRepository;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dao.WayTagRepository;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.model.Key;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.model.WayTag;
import pl.pwr.bdmap.services.WayService;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class WayController {

    private final WayService wayService;
    private final WayRepository wayRepository;
    private final KeyRepository keyRepository;
    private final WayTagRepository wayTagRepository;


    public  WayController(WayService wayService, WayRepository wayRepository, KeyRepository keyRepository, WayTagRepository wayTagRepository) {
        this.wayService = wayService;
        this.wayRepository = wayRepository;
        this.keyRepository = keyRepository;
        this.wayTagRepository = wayTagRepository;
    }

    @GetMapping("/ways")
    List<WayDTO> all(){
        return wayService.list();
    }


    //assuming that selected Key is legal for this Way (and both exists)
    @PutMapping("/ways/key")
    String addKeyToWay(@RequestParam("wayId") Integer wayId,
                       @RequestParam("keyName") String keyName ) {


        Way way;
        try{
            way = wayRepository.findById(wayId).orElseThrow();
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Way not found", e);
        }

        Key key;
        try{
            key = keyRepository.findByValue(keyName);
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Key not found", e);
        }

        WayTag existingWayTag = wayTagRepository.findByWayAndKey(way,key);

        if(existingWayTag == null) {
            WayTag newWayTag = new WayTag();
            newWayTag.setKey(key);
            newWayTag.setWay(way);

            newWayTag = wayTagRepository.save(newWayTag);
            //TODO: set version as timestamp?

            return newWayTag.toString();
        }
        else {
            return existingWayTag.toString();
        }
    }

}

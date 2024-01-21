package pl.pwr.bdmap.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import pl.pwr.bdmap.dto.MapAreaDTO;
import pl.pwr.bdmap.services.MapAreaService;

import javax.naming.directory.InvalidAttributesException;

@RestController
public class MapAreaController {
    private final MapAreaService mapAreaService;

    public MapAreaController(MapAreaService mapAreaService) {
        this.mapAreaService = mapAreaService;
    }

    @GetMapping("/map/screen")
    MapAreaDTO getMapAreaData(@RequestParam double maxX,
                              @RequestParam double minX,
                              @RequestParam double maxY,
                              @RequestParam double minY) throws InvalidAttributesException {
        try{
            return  mapAreaService.getMapAreaData(maxX, minX, maxY, minY);
        } catch (InvalidAttributesException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}

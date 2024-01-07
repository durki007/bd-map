package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.services.WayService;

import java.util.List;

@RestController
public class WayController {

    private final WayService wayService;

    public  WayController(WayService wayService) {
        this.wayService = wayService;
    }

    @GetMapping("/ways")
    List<WayDTO> all(){
        return wayService.list();
    }

//    @PostMapping("/way")
//    WayDTO newWay(@RequestBody WayDTO newWay){
//        return wayService.save(newWay);
//    }



//    @GetMapping("/ways/")
}

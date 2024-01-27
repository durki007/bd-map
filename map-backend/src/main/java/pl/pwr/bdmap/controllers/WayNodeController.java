package pl.pwr.bdmap.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pwr.bdmap.dto.WayNodeDTO;
import pl.pwr.bdmap.dto.mappers.WayNodeDTOMapper;
import pl.pwr.bdmap.services.WayNodeService;

import java.util.List;

@RestController
public class WayNodeController {
    private final WayNodeService wayNodeService;
    private final WayNodeDTOMapper mapper;

    public WayNodeController(WayNodeService wayNodeService, WayNodeDTOMapper mapper) {
        this.wayNodeService = wayNodeService;
        this.mapper = mapper;
    }

    @GetMapping("/wayNodes")
    public List<WayNodeDTO> getAll() {
        return wayNodeService.getAll().stream().map(mapper).toList();
    }

}

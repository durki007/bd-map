package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayDTOMapper;
import pl.pwr.bdmap.model.Way;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class WayService {
    private final WayRepository wayRepository;
    private final WayTypeService wayTypeService;
    private final WayDTOMapper mapper;

    @Autowired
    public WayService(WayRepository repository, WayTypeService wayTypeService, WayDTOMapper mapper) {
        this.mapper = mapper;
        this.wayRepository = repository;
        this.wayTypeService = wayTypeService;
    }

    public List<WayDTO> list() {
        List<Way> list = new ArrayList<>();
        wayRepository.findAll().forEach(list::add);
        return  list.stream().map(mapper).collect(Collectors.toList());
    }

    public WayDTO save(WayDTO wayDTO) {
        Way way = new Way();

        // TODO: Check if its correct
        way.setIsBlocked(false);
        way.setName(wayDTO.name());

        if(wayDTO.wayType() == null) {
            way.setWayType(wayTypeService.save("default"));
        } else {
            way.setWayType(wayTypeService.save(wayDTO.wayType()));
        }

        if(wayDTO.timestamp() == null) {
            way.setTimestamp(new Timestamp(System.currentTimeMillis()));
        } else {
            way.setTimestamp(wayDTO.timestamp());
        }

        // Save way
        way = wayRepository.save(way);
        return mapper.apply(way);

    }

    public List<WayDTO> save(List<WayDTO> wayDTOs) {
        List<WayDTO> savedWays = new ArrayList<>();
        for (WayDTO wayDTO : wayDTOs){
            savedWays.add(save(wayDTO));
        }
        return savedWays;
    }

    public WayDTO delete(int id) throws NoSuchElementException {
        Way way = wayRepository.findById(id).orElseThrow();
        wayRepository.delete(way);
        return mapper.apply(way);
    }

}

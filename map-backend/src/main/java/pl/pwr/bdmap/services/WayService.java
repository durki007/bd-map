package pl.pwr.bdmap.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pl.pwr.bdmap.dao.WayRepository;
import pl.pwr.bdmap.dao.WayTypeRepository;
import pl.pwr.bdmap.dto.WayDTO;
import pl.pwr.bdmap.dto.WayDTOMapper;
import pl.pwr.bdmap.model.Changeset;
import pl.pwr.bdmap.model.Way;
import pl.pwr.bdmap.model.WayType;

import javax.naming.directory.InvalidAttributesException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class WayService {
    private final WayRepository wayRepository;
    private final WayTypeService wayTypeService;
    private final HistoricWayDataService historicWayDataService;
    private final WayDTOMapper mapper;

    @Autowired
    public WayService(WayRepository repository, WayTypeService wayTypeService, @Lazy HistoricWayDataService historicWayDataService, WayDTOMapper mapper) {
        this.historicWayDataService = historicWayDataService;
        this.mapper = mapper;
        this.wayRepository = repository;
        this.wayTypeService = wayTypeService;
    }

    public List<WayDTO> list() {
        List<Way> list = new ArrayList<>();
        wayRepository.findAll().forEach(list::add);
        return  list.stream().map(mapper).toList();
    }

    public WayDTO save(WayDTO wayDTO) {
        Way way = new Way();

        // Map known fields
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
        // Save initial version
        historicWayDataService.saveInitialVersion(way);
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

    public Way getWayById(int wayId) throws NoSuchElementException {
        return wayRepository.findById(wayId).orElseThrow();
    }

    public void save(Way way) {
        wayRepository.save(way);
    }
    public WayType getWayType(int id) throws NoSuchElementException {
        Way way = wayRepository.findById(id).orElseThrow();
        return way.getWayType();
    }

    public Way update(Way way, WayDTO dtoNew) throws InvalidAttributesException {
        if (dtoNew.name() == null && dtoNew.wayType() == null) {
            throw new InvalidAttributesException("Invalid attributes for updating: " + dtoNew);
        }


        if(dtoNew.name() != null) {
            way.setName(dtoNew.name());
        }

        if(dtoNew.wayType() != null) {
            way.setWayType(wayTypeService.save(dtoNew.wayType()));
        }
        way.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return wayRepository.save(way);

    }

    public void blockWay(Integer wayId, Changeset changeset) throws NoSuchElementException {
        Way way = wayRepository.findById(wayId).orElseThrow(() -> new NoSuchElementException("Way with id " + wayId + " not found"));
        way.setBlockedBy(changeset);
        wayRepository.save(way);
    }
}

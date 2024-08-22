package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.Region;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.repository.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    private final RegionRepository repository;

    public RegionService(RegionRepository repository) {
        this.repository = repository;
    }



    public Page<Region> findAll(String searchTex, Pageable pageable) {

        // Construct Page object
        List<CountResult> countResults = repository.countAll(searchTex);

        List<Region> contents=repository.findAll(searchTex, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }



    public Region create(Region region) {
        return repository.save(region);
    }

    public Region update(String id, Region updatedRegion) {

//        Find the region with the given ID
        Region region = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Region not found"));

        region.setName(updatedRegion.getName());

        return repository.save(region);
    }


    public void delete(String id) {

//        Find the region with the given ID
        Region region = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Region not found"));

//        Delete the region by given id
        repository.delete(region);
    }


}

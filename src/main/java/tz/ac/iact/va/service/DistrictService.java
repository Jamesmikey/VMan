package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.District;
import tz.ac.iact.va.model.Region;
import tz.ac.iact.va.repository.DistrictRepository;
import tz.ac.iact.va.repository.RegionRepository;

import java.util.List;

@Service
public class DistrictService {

    private final DistrictRepository repository;

    private final RegionRepository regionRepository;

    public DistrictService(DistrictRepository repository, RegionRepository regionRepository) {
        this.repository = repository;
        this.regionRepository = regionRepository;
    }


    public Page<District> findAll(String searchTex, Pageable pageable) {

        // Construct Page object
        List<CountResult> countResults = repository.countAll(searchTex);

        List<District> contents = repository.findAll(searchTex, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }

    public Page<District> findAllByRegion(String regionId, Pageable pageable) {
        return repository.findAllByRegionId(regionId, pageable);
    }


    public District findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("District not found"));
    }

    public District create(District district) {

        //Check if region provided exists
        Region region = regionRepository.findById(district.getRegion().getId()).orElseThrow(() -> new DataNotFoundException("Region not found"));

        district.setRegion(region);

        return repository.save(district);
    }

    public District update(String id, District updatedDistrict) {

//        Find the district with the given ID
        District district = repository.findById(id).orElseThrow(() -> new DataNotFoundException("District not found"));

        //Check if region provided exists
        Region region = regionRepository.findById(updatedDistrict.getRegion().getId()).orElseThrow(() -> new DataNotFoundException("Region not found"));

        district.setName(updatedDistrict.getName());

        district.setRegion(region);

        return repository.save(district);
    }


    public void delete(String id) {

//        Find the district with the given ID
        District district = repository.findById(id).orElseThrow(() -> new DataNotFoundException("District not found"));

//        Delete the district by given id
        repository.delete(district);
    }


}

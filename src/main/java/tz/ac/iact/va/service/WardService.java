package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.District;
import tz.ac.iact.va.model.Region;
import tz.ac.iact.va.model.Ward;
import tz.ac.iact.va.repository.DistrictRepository;
import tz.ac.iact.va.repository.WardRepository;

import java.util.List;

@Service
public class WardService {

    private final WardRepository repository;

    private final DistrictRepository districtRepository;

    public WardService(WardRepository repository, DistrictRepository districtRepository) {
        this.repository = repository;
        this.districtRepository = districtRepository;
    }



    public Page<Ward> findAll(String searchTex, Pageable pageable) {

        // Construct Page object
        List<CountResult> countResults = repository.countAll(searchTex);

        List<Ward> contents=repository.findAll(searchTex, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }

    public Page<Ward> findAllByDistrict(String districtId,String searchText, Pageable pageable) {
        // Construct Page object
        List<CountResult> countResults = repository.countAllByDistrictId(districtId,searchText);

        List<Ward> contents=repository.findAllByDistrictId(districtId,searchText, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }


    public Ward findById(String id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ward not found"));
    }



    public Ward create(Ward ward) {

        //Check if region provided exists
        District district=districtRepository.findById(ward.getDistrict().getId()).orElseThrow(() -> new DataNotFoundException("District not found"));

        ward.setDistrict(district);

        return repository.save(ward);
    }

    public Ward update(String id, Ward updatedWard) {

//        Find the ward with the given ID
        Ward ward = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ward not found"));

        //Check if region provided exists
        District district=districtRepository.findById(updatedWard.getDistrict().getId()).orElseThrow(() -> new DataNotFoundException("District not found"));

        ward.setDistrict(district);

        ward.setName(updatedWard.getName());

        return repository.save(ward);
    }


    public void delete(String id) {

//        Find the ward with the given ID
        Ward ward = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ward not found"));

//        Delete the ward by given id
        repository.delete(ward);
    }


}

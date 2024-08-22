package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.Ward;
import tz.ac.iact.va.repository.WardRepository;

import java.util.List;

@Service
public class WardService {

    private final WardRepository repository;

    public WardService(WardRepository repository) {
        this.repository = repository;
    }



    public Page<Ward> findAll(String searchTex, Pageable pageable) {

        // Construct Page object
        List<CountResult> countResults = repository.countAll(searchTex);

        List<Ward> contents=repository.findAll(searchTex, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }

    public Page<Ward> findAllByDistrict(String districtId, Pageable pageable) {
        return repository.findAllByDistrictId(districtId, pageable);
    }




    public Ward create(Ward ward) {
        return repository.save(ward);
    }

    public Ward update(String id, Ward updatedWard) {

//        Find the ward with the given ID
        Ward ward = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Ward not found"));

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

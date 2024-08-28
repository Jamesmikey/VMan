package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.District;

import java.util.List;
import java.util.Optional;

public interface DistrictRepository extends MongoRepository<District, String> {

    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?1,'$options' : 'i'},'region': ObjectId(?0)}}",
    })
    Slice<District> findAllByRegionId(String regionId,String name,Pageable pageable);


    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?1,'$options' : 'i'},'region': ObjectId(?0)}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAllByRegionId(String regionId,String name);


    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
    })
    Slice<District> findAll(String name,Pageable pageable);



    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAll(String name);


}
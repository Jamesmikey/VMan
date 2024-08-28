package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.District;
import tz.ac.iact.va.model.Ward;

import java.util.List;

public interface WardRepository extends MongoRepository<Ward, String> {

    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?1,'$options' : 'i'},'district': ObjectId(?0)}}",
    })
    Slice<Ward> findAllByDistrictId(String districtId,String name,Pageable pageable);


    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?1,'$options' : 'i'},'district': ObjectId(?0)}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAllByDistrictId(String districtId,String name);


    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
    })
    Slice<Ward> findAll(String name,Pageable pageable);



    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAll(String name);


}
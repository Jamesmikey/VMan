package tz.ac.iact.va.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.Region;

import java.util.List;

public interface RegionRepository extends MongoRepository<Region, String> {

    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
    })
    Slice<Region> findAll(String name, Pageable pageable);

    @Aggregation(pipeline = {
            "{$match: {'name': {'$regex':?0,'$options' : 'i'}}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAll(String name);


}
package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Region;

public interface RegionRepository extends MongoRepository<Region, String> {

}
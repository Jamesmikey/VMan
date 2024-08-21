package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.District;

public interface DistrictRepository extends MongoRepository<District, String> {

}
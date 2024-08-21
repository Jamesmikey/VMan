package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Ward;

public interface WardRepository extends MongoRepository<Ward, String> {

}
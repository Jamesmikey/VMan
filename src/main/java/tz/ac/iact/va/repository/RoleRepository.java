package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Role;
public interface RoleRepository extends MongoRepository<Role, String> {

}
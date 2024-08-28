package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Assignment;

import java.util.List;
import java.util.Optional;

public interface AssignmentRepository extends MongoRepository<Assignment, String> {

    Optional<Assignment> findByWardId(String wardId);

    Optional<Assignment> findByUserIdAndWardId(String userId, String wardId);

    List<Assignment> findAllByUserId(String userId);

    boolean existsByUserIdAndWardId(String userId,String wardId);

}
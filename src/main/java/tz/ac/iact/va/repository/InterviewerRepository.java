package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Interviewer;

import java.util.Optional;

public interface InterviewerRepository extends MongoRepository<Interviewer, String> {

    Optional<Interviewer> findByWardId(String wardId);

    Optional<Interviewer> findByUserIdAndWardId(String userId,String wardId);

    boolean existsByUserIdAndWardId(String userId,String wardId);

}
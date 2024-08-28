package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Interview;
import tz.ac.iact.va.model.VAReport;

import java.util.Optional;

public interface VAReportRepository extends MongoRepository<VAReport, String> {


    Optional<VAReport> findByInterviewIdAndId(String interviewId, String id);



    void deleteByInterviewIdAndId(String interviewId, String id);

}
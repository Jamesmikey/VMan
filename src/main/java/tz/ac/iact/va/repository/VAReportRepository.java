package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.Interview;
import tz.ac.iact.va.model.VAReport;

public interface VAReportRepository extends MongoRepository<VAReport, String> {

    Page<VAReport> findAllByIdAndInterviewerId(String id,String interviewerId, Pageable pageable);

}
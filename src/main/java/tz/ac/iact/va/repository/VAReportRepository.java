package tz.ac.iact.va.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tz.ac.iact.va.model.VAReport;

public interface VAReportRepository extends MongoRepository<VAReport, String> {

}
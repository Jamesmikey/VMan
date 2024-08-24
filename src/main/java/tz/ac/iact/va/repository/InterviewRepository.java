package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tz.ac.iact.va.model.Interview;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface InterviewRepository extends MongoRepository<Interview, String> {

//    @Aggregation(pipeline = {
//            "{ '$lookup': { 'from': 'interviewers', 'localField': 'interviewer.oid', 'foreignField': 'interview', 'as': 'interviewer' } }",
//            "{'$unwind': { 'path': '$interviewer', 'preserveNullAndEmptyArrays': true}}"
//    })

    Page<Interview> findAllByInterviewerId(String interviewerId,Pageable pageable);

    Optional<Interview> findByIdAndInterviewerId(String id,String interviewerId);

}
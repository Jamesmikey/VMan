package tz.ac.iact.va.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.Interview;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface InterviewRepository extends MongoRepository<Interview, String> {

//    @Aggregation(pipeline = {
//            "{ '$lookup': { 'from': 'interviewers', 'localField': 'interviewer.oid', 'foreignField': 'interview', 'as': 'interviewer' } }",
//            "{'$unwind': { 'path': '$interviewer', 'preserveNullAndEmptyArrays': true}}"
//    })

    Page<Interview> findAllByAssignmentId(String interviewerId,Pageable pageable);

    Optional<Interview> findByIdAndAssignmentId(String id,String interviewerId);


    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'assignments', 'localField': 'assignment.oid', 'foreignField': 'interview', 'as': 'assignments' } }",
            "{'$unwind': { 'path': '$assignments', 'preserveNullAndEmptyArrays': true}}",
            "{'$match': { 'assignments.user': ?0}}"
    })
    Slice<Interview> findAllByUserId(String userId, Pageable pageable);

    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'assignments', 'localField': 'assignment.oid', 'foreignField': 'interview', 'as': 'assignments' } }",
            "{'$unwind': { 'path': '$assignments', 'preserveNullAndEmptyArrays': true}}",
            "{'$match': { 'assignments.user': ?0}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAllByUserId(String userId);

    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'assignments', 'localField': 'assignment.oid', 'foreignField': 'interview', 'as': 'assignments' } }",
            "{'$unwind': { 'path': '$assignments', 'preserveNullAndEmptyArrays': true}}",
            " {'$match': {'assignments.user':?0, 'assignments.ward':ObjectId(?1)}}"
    })
    Slice<Interview> findAllByUserIdAndWardId(String userId,String wardId, Pageable pageable);

    @Aggregation(pipeline = {
            "{ '$lookup': { 'from': 'assignments', 'localField': 'assignment.oid', 'foreignField': 'interview', 'as': 'assignments' } }",
            "{'$unwind': { 'path': '$assignments', 'preserveNullAndEmptyArrays': true}}",
            " {'$match': {'assignments.user':?0, 'assignments.ward':ObjectId(?1)}}",
            "{$count: 'count'}"
    })
    List<CountResult> countAllByUserIdAndWardId(String userId,String wardId);

}
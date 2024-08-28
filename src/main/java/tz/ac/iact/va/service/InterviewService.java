package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.*;
import tz.ac.iact.va.repository.InterviewRepository;
import tz.ac.iact.va.repository.AssignmentRepository;
import tz.ac.iact.va.repository.NotificationRepository;
import tz.ac.iact.va.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewService {

    private final InterviewRepository repository;

    private final AssignmentRepository assignmentRepository;

    private final NotificationRepository notificationRepository;

    private final UserDetailServiceImp userService;

    private final UserRepository userRepository;

    public InterviewService(InterviewRepository repository, AssignmentRepository assignmentRepository, NotificationRepository notificationRepository, UserDetailServiceImp userService, UserRepository userRepository) {
        this.repository = repository;
        this.assignmentRepository = assignmentRepository;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    public Page<Interview> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Page<Interview> findMyAll(Pageable pageable) {


        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Construct Page object
        List<CountResult> countResults = repository.countAllByUserId(user.getId());

        List<Interview> contents = repository.findAllByUserId(user.getId(), pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }


    public Page<Interview> findMyAllByWardId(String wardId,Pageable pageable) {


        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Construct Page object
        List<CountResult> countResults = repository.countAllByUserIdAndWardId(user.getId(),wardId);

        List<Interview> contents = repository.findAllByUserIdAndWardId(user.getId(),wardId, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }


    public Page<Interview> findAllByUserId(String userId,Pageable pageable) {


        // Construct Page object
        List<CountResult> countResults = repository.countAllByUserId(userId);

        List<Interview> contents = repository.findAllByUserId(userId, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);


    }


    public Interview findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interview not found"));
    }




    public Interview create(Interview interview) {

        //Check if interviewer exists
        Assignment assignment = assignmentRepository.findById(interview.getAssignment().getId()).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
        interview.setAssignment(assignment);

        //Check if notification exists
        Notification notification = notificationRepository.findById(interview.getAssignment().getId()).orElseThrow(() -> new DataNotFoundException("Notification not found"));
        interview.setNotification(notification);

        interview.setStatus(InterviewStatus.Scheduled);

        return repository.save(interview);

    }

    public Interview update(String id, Interview updatedInterview) {

        //Find the interview with the given ID
        Interview interview = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interview not found"));

        //Check if interviewer exists
        Assignment assignment = assignmentRepository.findById(updatedInterview.getAssignment().getId()).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
        interview.setAssignment(assignment);

        interview.setStatus(updatedInterview.getStatus());

        return repository.save(interview);

    }


    public void delete(String id) {

//        Find the interview with the given ID
        Interview interview = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interview not found"));

//        Delete the interview by given id
        repository.delete(interview);
    }



}

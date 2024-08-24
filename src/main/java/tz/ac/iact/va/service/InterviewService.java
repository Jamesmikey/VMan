package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.Interview;
import tz.ac.iact.va.model.Interviewer;
import tz.ac.iact.va.model.Notification;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.repository.InterviewRepository;
import tz.ac.iact.va.repository.InterviewerRepository;
import tz.ac.iact.va.repository.NotificationRepository;
import tz.ac.iact.va.repository.UserRepository;

@Service
public class InterviewService {

    private final InterviewRepository repository;

    private final InterviewerRepository interviewerRepository;

    private final NotificationRepository notificationRepository;

    private final UserDetailServiceImp userService;

    private final UserRepository userRepository;

    public InterviewService(InterviewRepository repository, InterviewerRepository interviewerRepository, NotificationRepository notificationRepository, UserDetailServiceImp userService, UserRepository userRepository) {
        this.repository = repository;
        this.interviewerRepository = interviewerRepository;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


    public Page<Interview> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Slice<Interview> findMyAll(String wardId,Pageable pageable) {


        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Get the interviewer associated with the logged-in user
        Interviewer interviewer=interviewerRepository.findByUserIdAndWardId(user.getId(),wardId).orElseThrow(() -> new DataNotFoundException("Not interviewer details associate with your account and given ward"));

        //Find the interview by user id
        return repository.findAllByInterviewerId(interviewer.getId(),pageable);

    }


    public Interview findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interview not found"));
    }


    public Interview create(Interview interview) {

        //Check if interviewer exists
        Interviewer interviewer = interviewerRepository.findById(interview.getInterviewer().getId()).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
        interview.setInterviewer(interviewer);

        //Check if notification exists
        Notification notification = notificationRepository.findById(interview.getInterviewer().getId()).orElseThrow(() -> new DataNotFoundException("Notification not found"));
        interview.setNotification(notification);

        interview.setStatus(InterviewStatus.Scheduled);

        return repository.save(interview);

    }

    public Interview update(String id, Interview updatedInterview) {

        //Find the interview with the given ID
        Interview interview = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interview not found"));

        //Check if interviewer exists
        Interviewer interviewer = interviewerRepository.findById(updatedInterview.getInterviewer().getId()).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
        interview.setInterviewer(interviewer);

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

package tz.ac.iact.va.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.*;
import tz.ac.iact.va.repository.*;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    private final UserRepository userRepository;

    private final InterviewRepository interviewRepository;

    private final InterviewerRepository interviewerRepository;

    private final WardRepository wardRepository;

    public NotificationService(NotificationRepository repository, UserRepository userRepository, InterviewRepository interviewRepository, InterviewerRepository interviewerRepository, WardRepository wardRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.interviewRepository = interviewRepository;
        this.interviewerRepository = interviewerRepository;
        this.wardRepository = wardRepository;
    }


    @Transactional
    public Notification create(Notification notification){

        //Save the new notification to the database
        User user=userRepository.findByEmail(getCurrentPrincipal().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        notification.setReportedBy(user);

        //Check if ward exists
        Ward ward = wardRepository.findById(notification.getWard().getId()).orElseThrow(() -> new DataNotFoundException("Ward not found"));
        notification.setWard(ward);


        //Save notification
        Notification savedNotification=repository.save(notification);

        //Search interviewer to create interview if no interview found save it anyway
        Interviewer interviewer=interviewerRepository.findByWardId(ward.getId()).orElse(null);

        //Build interview if there is interviewer in this ward
        if(interviewer!=null) {

            Interview interview = Interview.builder().interviewer(interviewer)
                    .status(InterviewStatus.Scheduled)
                    .notification(savedNotification)
                    .build();

            interviewRepository.save(interview);

        }

        return savedNotification;

    }


    public UserDetails getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}

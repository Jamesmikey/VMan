package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.exception.InvalidDataException;
import tz.ac.iact.va.model.Interview;
import tz.ac.iact.va.model.Assignment;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.model.VAReport;
import tz.ac.iact.va.repository.*;

import java.util.List;

@Service
public class VAReportService {

    private final VAReportRepository repository;

    private final NotificationRepository notificationRepository;

    private final UserDetailServiceImp userService;

    private final UserRepository userRepository;

    private final AssignmentRepository assignmentRepository;

    private final InterviewRepository interviewRepository;

    public VAReportService(VAReportRepository repository, NotificationRepository notificationRepository, UserDetailServiceImp userService, UserRepository userRepository, AssignmentRepository assignmentRepository, InterviewRepository interviewRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.assignmentRepository = assignmentRepository;
        this.interviewRepository = interviewRepository;
    }




    public VAReport findById(String interviewId,String id) {
        return repository.findByInterviewIdAndId(interviewId,id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));
    }


    @Transactional
    public VAReport create(String interviewId,VAReport vaReport) {

        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Get the interview associated with the logged-in user
        Interview interview=interviewRepository.findById(interviewId).orElseThrow(() -> new DataNotFoundException("Interview not found"));

        // Check if user can create this VAReport
        if(!interview.getAssignment().getUser().getId().equals(user.getId())){
            throw new AccessDeniedException("You can not create VA Report for this interview");
        }


        // Check if user can create this VAReport
        if(interview.getStatus()==InterviewStatus.Completed){
            throw new InvalidDataException("The interview was completed, You can not add a VA Report");
        }

        //Set the interview is completed
        interview.setStatus(InterviewStatus.Completed);

        interviewRepository.save(interview);

        vaReport.setInterview(interview);

        return repository.save(vaReport);

    }

    public VAReport update(String interviewId,String id, VAReport updatedVAReport) {

        //Find the vAReport with the given ID
        VAReport vAReport = repository.findByInterviewIdAndId(interviewId,id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));

//        //Check if vAReporter exists
//        VAReporter vAReporter = vAReporterRepository.findById(updatedVAReport.getVAReporter().getId()).orElseThrow(() -> new DataNotFoundException("VAReporter not found"));
//        vAReport.setVAReporter(vAReporter);
//
//        vAReport.setStatus(updatedVAReport.getStatus());

        return repository.save(vAReport);

    }


    public void delete(String interviewId,String id) {

//        Find the vAReport with the given ID
        VAReport vAReport = repository.findByInterviewIdAndId(interviewId,id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));

//        Delete the vAReport by given id
        repository.delete(vAReport);

    }



}

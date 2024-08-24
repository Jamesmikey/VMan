package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tz.ac.iact.va.enums.InterviewStatus;
import tz.ac.iact.va.enums.VAReportStatus;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.*;
import tz.ac.iact.va.model.VAReporter;
import tz.ac.iact.va.repository.*;
import tz.ac.iact.va.repository.VAReporterRepository;

@Service
public class VAReportService {

    private final VAReportRepository repository;

    private final NotificationRepository notificationRepository;

    private final UserDetailServiceImp userService;

    private final UserRepository userRepository;

    private final InterviewerRepository interviewerRepository;

    private final InterviewRepository interviewRepository;

    public VAReportService(VAReportRepository repository, NotificationRepository notificationRepository, UserDetailServiceImp userService, UserRepository userRepository, InterviewerRepository interviewerRepository, InterviewRepository interviewRepository) {
        this.repository = repository;
        this.notificationRepository = notificationRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.interviewerRepository = interviewerRepository;
        this.interviewRepository = interviewRepository;
    }


    public Page<VAReport> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Slice<VAReport> findMyAll(String wardId,String interviewId,Pageable pageable) {

        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Get the interviewer associated with the logged-in user
        Interviewer interviewer=interviewerRepository.findByUserIdAndWardId(user.getId(),wardId).orElseThrow(() -> new DataNotFoundException("Not interviewer details associate with your account and given ward"));

        //Find the vAReport by user id
        return repository.findAllByIdAndInterviewerId(interviewer.getId(),interviewId,pageable);

    }


    public VAReport findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));
    }


    @Transactional
    public VAReport create(String wardId,String interviewId,VAReport vaReport) {

        User user=userRepository.findByEmail(userService.getCurrentUser().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));

        // Get the interviewer associated with the logged-in user
        Interviewer interviewer=interviewerRepository.findByUserIdAndWardId(user.getId(),wardId).orElseThrow(() -> new DataNotFoundException("Not interviewer details associate with your account and given ward"));

        // Get the interview associated with the logged-in user
        Interview interview=interviewRepository.findByIdAndInterviewerId(interviewId,interviewer.getId()).orElseThrow(() -> new DataNotFoundException("Interview not found"));

        //Set the interview is completed
        interview.setStatus(InterviewStatus.Completed);

        interviewRepository.save(interview);

        vaReport.setInterview(interview);

        return repository.save(vaReport);

    }

    public VAReport update(String id, VAReport updatedVAReport) {

        //Find the vAReport with the given ID
        VAReport vAReport = repository.findById(id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));

        //Check if vAReporter exists
        VAReporter vAReporter = vAReporterRepository.findById(updatedVAReport.getVAReporter().getId()).orElseThrow(() -> new DataNotFoundException("VAReporter not found"));
        vAReport.setVAReporter(vAReporter);

        vAReport.setStatus(updatedVAReport.getStatus());

        return repository.save(vAReport);

    }


    public void delete(String id) {

//        Find the vAReport with the given ID
        VAReport vAReport = repository.findById(id).orElseThrow(() -> new DataNotFoundException("VAReport not found"));

//        Delete the vAReport by given id
        repository.delete(vAReport);
    }



}

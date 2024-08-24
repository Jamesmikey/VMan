package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.exception.InvalidDataException;
import tz.ac.iact.va.model.Interviewer;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.model.Ward;
import tz.ac.iact.va.repository.InterviewerRepository;
import tz.ac.iact.va.repository.UserRepository;
import tz.ac.iact.va.repository.WardRepository;

@Service
public class InterviewerService {

    private final InterviewerRepository repository;

    private final UserRepository userRepository;

    private final WardRepository wardRepository;

    public InterviewerService(InterviewerRepository repository, UserRepository userRepository, WardRepository wardRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.wardRepository = wardRepository;
    }


    public Page<Interviewer> findAll(Pageable pageable) {

       return repository.findAll(pageable);

    }


    public Interviewer findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
    }

    public Interviewer create(Interviewer interviewer) {


        //Check if user provided exists
        User user = userRepository.findById(interviewer.getUser().getId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        interviewer.setUser(user);


        //Check if ward provided exists
        Ward ward = wardRepository.findById(interviewer.getWard().getId()).orElseThrow(() -> new DataNotFoundException("Ward not found"));
        interviewer.setWard(ward);


        //Set created by
        User createdBy = userRepository.findById(getCurrentPrincipal().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));
        interviewer.setCreatedBy(createdBy);


        if(repository.existsByUserIdAndWardId(user.getId(),ward.getId())){
            throw new InvalidDataException("This user is already assigned to this ward");
        }

        //Create composite ID
        interviewer.setId(user.getId()+"-"+ward.getId());

        return repository.save(interviewer);

    }

    public Interviewer update(String id, Interviewer updatedInterviewer) {

        //Find the Interviewer with the given ID
        Interviewer interviewer = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));

        //Check if ward provided exists
        Ward ward = wardRepository.findById(updatedInterviewer.getUser().getId()).orElseThrow(() -> new DataNotFoundException("Ward not found"));
        interviewer.setWard(ward);


        return repository.save(interviewer);
    }


    public void delete(String id) {

//        Find the Interviewer with the given ID
        Interviewer interviewer = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));

//        Delete the Interviewer by given id
        repository.delete(interviewer);
    }


    public UserDetails getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

}

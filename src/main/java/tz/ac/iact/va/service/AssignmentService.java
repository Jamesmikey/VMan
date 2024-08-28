package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.exception.InvalidDataException;
import tz.ac.iact.va.model.Assignment;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.model.Ward;
import tz.ac.iact.va.repository.AssignmentRepository;
import tz.ac.iact.va.repository.UserRepository;
import tz.ac.iact.va.repository.WardRepository;

@Service
public class AssignmentService {

    private final AssignmentRepository repository;

    private final UserRepository userRepository;

    private final WardRepository wardRepository;

    public AssignmentService(AssignmentRepository repository, UserRepository userRepository, WardRepository wardRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.wardRepository = wardRepository;
    }


    public Page<Assignment> findAll(Pageable pageable) {

       return repository.findAll(pageable);

    }


    public Assignment findById(String id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));
    }

    public Assignment create(Assignment assignment) {


        //Check if user provided exists
        User user = userRepository.findById(assignment.getUser().getId()).orElseThrow(() -> new DataNotFoundException("User not found"));
        assignment.setUser(user);


        //Check if ward provided exists
        Ward ward = wardRepository.findById(assignment.getWard().getId()).orElseThrow(() -> new DataNotFoundException("Ward not found"));
        assignment.setWard(ward);


        //Set created by
        User createdBy = userRepository.findById(getCurrentPrincipal().getUsername()).orElseThrow(() -> new DataNotFoundException("User not found"));
        assignment.setCreatedBy(createdBy);


        if(repository.existsByUserIdAndWardId(user.getId(),ward.getId())){
            throw new InvalidDataException("This user is already assigned to this ward");
        }

        //Create composite ID
        assignment.setId(user.getId()+"-"+ward.getId());

        return repository.save(assignment);

    }

    public Assignment update(String id, Assignment updatedAssignment) {

        //Find the Interviewer with the given ID
        Assignment assignment = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));

        //Check if ward provided exists
        Ward ward = wardRepository.findById(updatedAssignment.getUser().getId()).orElseThrow(() -> new DataNotFoundException("Ward not found"));
        assignment.setWard(ward);


        return repository.save(assignment);
    }


    public void delete(String id) {

//        Find the Interviewer with the given ID
        Assignment assignment = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Interviewer not found"));

//        Delete the Interviewer by given id
        repository.delete(assignment);
    }


    public UserDetails getCurrentPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

}

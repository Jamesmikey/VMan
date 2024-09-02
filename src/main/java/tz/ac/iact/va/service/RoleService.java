package tz.ac.iact.va.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.CountResult;
import tz.ac.iact.va.model.Role;
import tz.ac.iact.va.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Page<Role> findAll(String searchTex, Pageable pageable) {

        // Construct Page object
        List<CountResult> countResults = repository.countAll(searchTex);

        List<Role> contents=repository.findAll(searchTex, pageable).getContent();

        return new PageImpl<>(contents, pageable, !countResults.isEmpty() ? countResults.get(0).getCount() : 0);

    }


    public Role findById(String id){
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Role not found"));
    }


    public Role create(Role role) {
        return repository.save(role);
    }

    public Role update(String id, Role updatedRole) {

//        Find the role with the given ID
        Role role = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Role not found"));

        role.setName(updatedRole.getName());

        return repository.save(role);
    }


    public void delete(String id) {

//        Find the role with the given ID
        Role role = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Role not found"));

//        Delete the role by given id
        repository.delete(role);
    }


}

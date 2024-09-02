package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tz.ac.iact.va.dto.role.RefRoleDTO;
import tz.ac.iact.va.dto.user.*;
import tz.ac.iact.va.exception.DataNotFoundException;
import tz.ac.iact.va.model.Role;
import tz.ac.iact.va.model.User;
import tz.ac.iact.va.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@Tag(name = "Users", description = "Manage Users")
@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService service;

    private final ModelMapper modelMapper;

    public UserController(UserService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all users in the system",description = "")
    public Page<ListUserDTO> findAll(@RequestParam(name = "filterText",defaultValue = "") String filterText, @RequestParam(name = "includeMe", defaultValue = "true") boolean includeMe, @Parameter(hidden = true) Pageable pageable) throws DataNotFoundException {
        return service.findAll(filterText,includeMe,pageable).map(user -> modelMapper.map(user, ListUserDTO.class));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get specific user by id",description = "")
    public DetailUserDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailUserDTO.class);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit user by id",description = "")
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER-ADMIN')")
    public ResponseEntity<MessageResponse> editUser(@PathVariable String id, @RequestBody UpdateUserDTO userDto) throws DataNotFoundException {
        service.update(id,modelMapper.map(userDto, User.class));
        return ResponseEntity.ok(new MessageResponse(true,"User updated successfully.."));
    }

    @PostMapping("/{id}/reset-password")
    @Operation(summary = "Reset password of user by id",description = "")
//    @PreAuthorize("hasAnyRole('ADMIN','SUPER-ADMIN')")
    public ResponseEntity<MessageResponse> resetPassword(@PathVariable String id, @RequestBody UpdateProfilePasswordRequest updateProfilePasswordRequest) throws DataNotFoundException {
        service.updatePasswordToDefault(id,updateProfilePasswordRequest.getNewPassword());
        return ResponseEntity.ok(new MessageResponse(true,"Password changed to 12345678"));
    }

    @GetMapping("/{id}/toggle-email-verified")
    @Operation(summary = "Toggle user verification status",description = "")
    @PreAuthorize("hasAnyRole('ADMIN','SUPER-ADMIN')")
    public DetailUserDTO toggleEmailVerified(@PathVariable String id) throws DataNotFoundException {
        return modelMapper.map(service.toggleEmailVerified(id),DetailUserDTO.class);
    }


    @PostMapping("/{id}/change-roles")
    @Operation(summary = "Change user roles by id",description = "")
//    @PreAuthorize("hasAnyRole('SUPER-ADMIN')")
    public ResponseEntity<?> changeRoles(@PathVariable String id,@RequestBody Set<RefRoleDTO> roles) throws DataNotFoundException {
         service.updateRoles(id,roles.stream().map(refRoleDTO -> modelMapper.map(refRoleDTO, Role.class)).collect(Collectors.toSet()));

        return ResponseEntity.ok(new MessageResponse(true,"User roles updated successfully!"));

    }

    @PostMapping
    @Operation(summary  = "Create new user",description = "")
    @ApiResponses(value = { @ApiResponse(responseCode = "200",description = "successful created user", content = { @Content(mediaType = "application/json") }) })
    @PreAuthorize("hasAnyRole('ADMIN','SUPER-ADMIN')")
    public ResponseEntity<?> save(@Valid @RequestBody CreateUserDTO newUserDto){

        //Convert this to User object
        User user = modelMapper.map(newUserDto, User.class);

        service.create(user);

        return ResponseEntity.ok(new MessageResponse(true,"User registered successfully!"));

    }


}
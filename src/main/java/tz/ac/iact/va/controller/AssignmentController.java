package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tz.ac.iact.va.dto.MessageDTO;
import tz.ac.iact.va.dto.assignment.*;
import tz.ac.iact.va.model.Assignment;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.AssignmentService;


@Tag(name = "Assignments", description = "Manage Assignments")
@RequestMapping("/api/v1/assignments")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class AssignmentController {

    private final AssignmentService service;

    private final DistrictService districtService;

    private final ModelMapper modelMapper;

    public AssignmentController(AssignmentService service, DistrictService districtService, ModelMapper modelMapper) {
        this.service = service;
        this.districtService = districtService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all assignments assignment", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignments fetched successfully"),
    })
    public Page<ListAssignmentDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(pageable).map(assignment -> modelMapper.map(assignment, ListAssignmentDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Assignment by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment fetched successfully"),
    })
    public DetailAssignmentDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), DetailAssignmentDTO.class);
    }



    @PostMapping
    @Operation(summary = "Create new assignment", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment created successfully"),
            @ApiResponse(responseCode = "400", description = "Assignment fields are not filled in correctly")
    })
    public CreatedAssignmentDTO create(@RequestBody @Valid CreateAssignmentDTO createAssignmentDTO) {

        Assignment assignment = modelMapper.map(createAssignmentDTO, Assignment.class);

        return modelMapper.map(service.create(assignment), CreatedAssignmentDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit assignment by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Assignment fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateAssignmentDTO updateAssignmentDTO) {

        Assignment assignment = modelMapper.map(updateAssignmentDTO, Assignment.class);

        service.update(id, assignment);

        return new MessageDTO(true, "Assignment updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete assignment by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Assignment deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "Assignment deleted successfully");

    }
}
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
import tz.ac.iact.va.dto.district.ListDistrictDTO;
import tz.ac.iact.va.dto.interviewer.*;
import tz.ac.iact.va.model.Interviewer;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.InterviewerService;


@Tag(name = "Interviewers", description = "Manage Interviewers")
@RequestMapping("/api/v1/interviewers")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class InterviewerController {

    private final InterviewerService service;

    private final DistrictService districtService;

    private final ModelMapper modelMapper;

    public InterviewerController(InterviewerService service, DistrictService districtService, ModelMapper modelMapper) {
        this.service = service;
        this.districtService = districtService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all interviewers interviewer", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviewers fetched successfully"),
    })
    public Page<ListInterviewerDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(pageable).map(interviewer -> modelMapper.map(interviewer, ListInterviewerDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Interviewer by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviewer fetched successfully"),
    })
    public DetailInterviewerDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailInterviewerDTO.class);
    }



    @PostMapping
    @Operation(summary = "Create new interviewer", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviewer created successfully"),
            @ApiResponse(responseCode = "400", description = "Interviewer fields are not filled in correctly")
    })
    public CreatedInterviewerDTO create(@RequestBody @Valid CreateInterviewerDTO createInterviewerDTO) {

        Interviewer interviewer = modelMapper.map(createInterviewerDTO, Interviewer.class);

        return modelMapper.map(service.create(interviewer), CreatedInterviewerDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit interviewer by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviewer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Interviewer fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateInterviewerDTO updateInterviewerDTO) {

        Interviewer interviewer = modelMapper.map(updateInterviewerDTO, Interviewer.class);

        service.update(id, interviewer);

        return new MessageDTO(true, "Interviewer updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete interviewer by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviewer deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "Interviewer deleted successfully");

    }
}
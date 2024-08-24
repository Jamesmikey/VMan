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
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;
import tz.ac.iact.va.dto.MessageDTO;
import tz.ac.iact.va.dto.interview.*;
import tz.ac.iact.va.model.Interview;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.InterviewService;


@Tag(name = "Interviews", description = "Manage Interviews")
@RequestMapping("/api/v1/interviews")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class InterviewController {

    private final InterviewService service;

    private final ModelMapper modelMapper;

    public InterviewController(InterviewService service,  ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all interviews", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviews fetched successfully"),
    })
    public Page<ListInterviewDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(pageable).map(interview -> modelMapper.map(interview, ListInterviewDTO.class));

    }


    @GetMapping("/my/all")
    @Operation(summary = "Get my all interviews", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interviews fetched successfully"),
    })
    public Slice<ListInterviewDTO> findMyAll(@RequestParam String wardId, @Parameter(hidden = true) Pageable pageable) {

        return service.findMyAll(wardId,pageable).map(interview -> modelMapper.map(interview, ListInterviewDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Interview by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interview fetched successfully"),
    })
    public DetailInterviewDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailInterviewDTO.class);
    }



    @PostMapping
    @Operation(summary = "Create new interview", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interview created successfully"),
            @ApiResponse(responseCode = "400", description = "Interview fields are not filled in correctly")
    })
    public CreatedInterviewDTO create(@RequestBody @Valid CreateInterviewDTO createInterviewDTO) {

        Interview interview = modelMapper.map(createInterviewDTO, Interview.class);

        return modelMapper.map(service.create(interview), CreatedInterviewDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit interview by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interview updated successfully"),
            @ApiResponse(responseCode = "400", description = "Interview fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateInterviewDTO updateInterviewDTO) {

        Interview interview = modelMapper.map(updateInterviewDTO, Interview.class);

        service.update(id, interview);

        return new MessageDTO(true, "Interview updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete interview by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Interview deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "Interview deleted successfully");

    }
}
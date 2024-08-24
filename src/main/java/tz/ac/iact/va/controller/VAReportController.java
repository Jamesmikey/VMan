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
import tz.ac.iact.va.model.VAReport;
import tz.ac.iact.va.service.VAReportService;


@Tag(name = "VAReports", description = "Manage VAReports")
@RequestMapping("/api/v1/va-reports")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class VAReportController {

    private final VAReportService service;

    private final ModelMapper modelMapper;

    public VAReportController(VAReportService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all vAReports", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReports fetched successfully"),
    })
    public Page<ListVAReportDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(pageable).map(vAReport -> modelMapper.map(vAReport, ListVAReportDTO.class));

    }


    @GetMapping("/my/all")
    @Operation(summary = "Get my all vAReports", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReports fetched successfully"),
    })
    public Slice<ListVAReportDTO> findMyAll(@RequestParam String wardId, @Parameter(hidden = true) Pageable pageable) {

        return service.findMyAll(wardId,pageable).map(vAReport -> modelMapper.map(vAReport, ListVAReportDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get VAReport by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport fetched successfully"),
    })
    public DetailVAReportDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailVAReportDTO.class);
    }



    @PostMapping
    @Operation(summary = "Create new vAReport", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport created successfully"),
            @ApiResponse(responseCode = "400", description = "VAReport fields are not filled in correctly")
    })
    public CreatedVAReportDTO create(@RequestBody @Valid CreateVAReportDTO createVAReportDTO) {

        VAReport vAReport = modelMapper.map(createVAReportDTO, VAReport.class);

        return modelMapper.map(service.create(vAReport), CreatedVAReportDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit vAReport by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport updated successfully"),
            @ApiResponse(responseCode = "400", description = "VAReport fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateVAReportDTO updateVAReportDTO) {

        VAReport vAReport = modelMapper.map(updateVAReportDTO, VAReport.class);

        service.update(id, vAReport);

        return new MessageDTO(true, "VAReport updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete vAReport by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "VAReport deleted successfully");

    }
}
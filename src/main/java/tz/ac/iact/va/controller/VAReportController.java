package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import tz.ac.iact.va.dto.MessageDTO;
import tz.ac.iact.va.dto.vaReport.CreateVAReportDTO;
import tz.ac.iact.va.dto.vaReport.CreatedVAReportDTO;
import tz.ac.iact.va.dto.vaReport.DetailVAReportDTO;
import tz.ac.iact.va.dto.vaReport.UpdateVAReportDTO;
import tz.ac.iact.va.model.VAReport;
import tz.ac.iact.va.service.VAReportService;


@Tag(name = "VAReports", description = "Manage VAReports")
@RequestMapping("/api/v1")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class VAReportController {

    private final VAReportService service;

    private final ModelMapper modelMapper;

    public VAReportController(VAReportService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/interviews/{interviewId}/va-reports/{id}")
    @Operation(summary = "Get VAReport by interviewId and ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport fetched successfully"),
    })
    public DetailVAReportDTO findById(@PathVariable String id, @PathVariable String interviewId) {
        return modelMapper.map(service.findById(interviewId,id),DetailVAReportDTO.class);
    }



    @PostMapping("/interviews/{interviewId}/va-reports")
    @Operation(summary = "Create new vAReport", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport created successfully"),
            @ApiResponse(responseCode = "400", description = "VAReport fields are not filled in correctly")
    })
    public CreatedVAReportDTO create(@RequestBody @Valid CreateVAReportDTO createVAReportDTO, @PathVariable String interviewId) {

        VAReport vAReport = modelMapper.map(createVAReportDTO, VAReport.class);

        return modelMapper.map(service.create(interviewId,vAReport), CreatedVAReportDTO.class);

    }


    @PutMapping("/interviews/{interviewId}/va-reports/{id}")
    @Operation(summary = "Edit vAReport by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport updated successfully"),
            @ApiResponse(responseCode = "400", description = "VAReport fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateVAReportDTO updateVAReportDTO, @PathVariable String interviewId) {

        VAReport vAReport = modelMapper.map(updateVAReportDTO, VAReport.class);

        service.update(interviewId,id, vAReport);

        return new MessageDTO(true, "VAReport updated successfully");
    }

    @DeleteMapping("/interviews/{interviewId}/va-reports/{id}")
    @Operation(summary = "Delete vAReport by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "VAReport deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id, @PathVariable String interviewId) {

        service.delete(interviewId,id);

        return new MessageDTO(true, "VAReport deleted successfully");

    }
}
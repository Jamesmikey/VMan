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
import tz.ac.iact.va.dto.region.DetailRegionDTO;
import tz.ac.iact.va.dto.ward.*;
import tz.ac.iact.va.model.Ward;
import tz.ac.iact.va.service.WardService;


@Tag(name = "Wards", description = "Manage Wards")
@RequestMapping("/api/v1/regions/{regionId}/districts/{districtId}")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class WardController {

    private final WardService service;

    private final ModelMapper modelMapper;

    public WardController(WardService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all wards ward", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wards fetched successfully"),
    })
    public Page<ListWardDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(searchText, pageable).map(ward -> modelMapper.map(ward, ListWardDTO.class));

    }


    @GetMapping("/{id}")
    @Operation(summary = "Get Ward by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ward fetched successfully"),
    })
    public DetailWardDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailWardDTO.class);
    }

    @PostMapping
    @Operation(summary = "Create new ward", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ward created successfully"),
            @ApiResponse(responseCode = "400", description = "Ward fields are not filled in correctly")
    })
    public CreatedWardDTO create(@RequestBody @Valid CreateWardDTO createWardDTO) {

        Ward ward = modelMapper.map(createWardDTO, Ward.class);

        return modelMapper.map(service.create(ward), CreatedWardDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit ward by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ward updated successfully"),
            @ApiResponse(responseCode = "400", description = "Ward fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateWardDTO updateWardDTO) {

        Ward ward = modelMapper.map(updateWardDTO, Ward.class);

        service.update(id, ward);

        return new MessageDTO(true, "Ward updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete ward by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ward deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);
        
        return new MessageDTO(true, "Ward deleted successfully");

    }
}
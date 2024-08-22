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
import tz.ac.iact.va.dto.district.CreateDistrictDTO;
import tz.ac.iact.va.dto.district.CreatedDistrictDTO;
import tz.ac.iact.va.dto.district.ListDistrictDTO;
import tz.ac.iact.va.dto.district.UpdateDistrictDTO;
import tz.ac.iact.va.dto.ward.ListWardDTO;
import tz.ac.iact.va.model.District;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.WardService;


@Tag(name = "Districts", description = "Manage Districts")
@RequestMapping("/api/v1/districts")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class DistrictController {

    private final DistrictService service;

    private final WardService wardService;

    private final ModelMapper modelMapper;

    public DistrictController(DistrictService service, WardService wardService, ModelMapper modelMapper) {
        this.service = service;
        this.wardService = wardService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all districts district", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Districts fetched successfully"),
    })
    public Page<ListDistrictDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(searchText, pageable).map(district -> modelMapper.map(district, ListDistrictDTO.class));

    }


    @GetMapping("/{id}/wards")
    @Operation(summary = "Get all wards belong to district with ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wards fetched successfully"),
    })
    public Page<ListWardDTO> findAllWardsByDistrict(@PathVariable String id, @Parameter(hidden = true) Pageable pageable) {

        return wardService.findAllByDistrict(id,pageable).map(district -> modelMapper.map(district, ListWardDTO.class));

    }

    @PostMapping
    @Operation(summary = "Create new district", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District created successfully"),
            @ApiResponse(responseCode = "400", description = "District fields are not filled in correctly")
    })
    public CreatedDistrictDTO create(@RequestBody @Valid CreateDistrictDTO createDistrictDTO) {

        District district = modelMapper.map(createDistrictDTO, District.class);

        return modelMapper.map(service.create(district), CreatedDistrictDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit district by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District updated successfully"),
            @ApiResponse(responseCode = "400", description = "District fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateDistrictDTO updateDistrictDTO) {

        District district = modelMapper.map(updateDistrictDTO, District.class);

        service.update(id, district);

        return new MessageDTO(true, "District updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete district by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "District deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);
        
        return new MessageDTO(true, "District deleted successfully");

    }
}
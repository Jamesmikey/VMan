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
import tz.ac.iact.va.dto.region.*;
import tz.ac.iact.va.dto.ward.ListWardDTO;
import tz.ac.iact.va.model.Region;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.RegionService;


@Tag(name = "Regions", description = "Manage Regions")
@RequestMapping("/api/v1/regions")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RegionController {

    private final RegionService service;

    private final DistrictService districtService;

    private final ModelMapper modelMapper;

    public RegionController(RegionService service, DistrictService districtService, ModelMapper modelMapper) {
        this.service = service;
        this.districtService = districtService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all regions region", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Regions fetched successfully"),
    })
    public Page<ListRegionDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(searchText, pageable).map(region -> modelMapper.map(region, ListRegionDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Region by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region fetched successfully"),
    })
    public DetailRegionDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailRegionDTO.class);
    }

    @GetMapping("/{id}/districts")
    @Operation(summary = "Get all districts belong to region with ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Districts fetched successfully"),
    })
    public Page<ListDistrictDTO> findAllWardsByRegion(@PathVariable String id,@RequestParam(required = false,defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return districtService.findAllByRegionId(id,searchText,pageable).map(district -> modelMapper.map(district, ListDistrictDTO.class));

    }

    @PostMapping
    @Operation(summary = "Create new region", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region created successfully"),
            @ApiResponse(responseCode = "400", description = "Region fields are not filled in correctly")
    })
    public CreatedRegionDTO create(@RequestBody @Valid CreateRegionDTO createRegionDTO) {

        Region region = modelMapper.map(createRegionDTO, Region.class);

        return modelMapper.map(service.create(region), CreatedRegionDTO.class);

    }


    @PutMapping("/{id}")
    @Operation(summary = "Edit region by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region updated successfully"),
            @ApiResponse(responseCode = "400", description = "Region fields are not filled in correctly")
    })
    public MessageDTO edit(@PathVariable String id, @RequestBody @Valid UpdateRegionDTO updateRegionDTO) {

        Region region = modelMapper.map(updateRegionDTO, Region.class);

        service.update(id, region);

        return new MessageDTO(true, "Region updated successfully");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete region by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Region deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "Region deleted successfully");

    }
}
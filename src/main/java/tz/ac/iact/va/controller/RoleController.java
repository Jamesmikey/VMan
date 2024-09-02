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
import tz.ac.iact.va.dto.role.*;
import tz.ac.iact.va.model.Role;
import tz.ac.iact.va.service.DistrictService;
import tz.ac.iact.va.service.RoleService;


@Tag(name = "Roles", description = "Manage Roles")
@RequestMapping("/api/v1/roles")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class RoleController {

    private final RoleService service;

    private final DistrictService districtService;

    private final ModelMapper modelMapper;

    public RoleController(RoleService service, DistrictService districtService, ModelMapper modelMapper) {
        this.service = service;
        this.districtService = districtService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    @Operation(summary = "Get all roles role", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles fetched successfully"),
    })
    public Page<ListRoleDTO> findAll(@RequestParam(required = false, defaultValue = "") String searchText, @Parameter(hidden = true) Pageable pageable) {

        return service.findAll(searchText, pageable).map(role -> modelMapper.map(role, ListRoleDTO.class));

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Role by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role fetched successfully"),
    })
    public DetailRoleDTO findById(@PathVariable String id) {
        return modelMapper.map(service.findById(id),DetailRoleDTO.class);
    }




    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role by id", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role deleted successfully")
    })
    public MessageDTO delete(@PathVariable String id) {

        service.delete(id);

        return new MessageDTO(true, "Role deleted successfully");

    }
}
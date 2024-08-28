package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import tz.ac.iact.va.dto.interview.DetailInterviewDTO;
import tz.ac.iact.va.dto.notification.CreateNotificationDTO;
import tz.ac.iact.va.dto.notification.CreatedNotificationDTO;
import tz.ac.iact.va.dto.notification.DetailNotificationDTO;
import tz.ac.iact.va.dto.notification.ListNotificationDTO;
import tz.ac.iact.va.model.Notification;
import tz.ac.iact.va.service.InterviewService;
import tz.ac.iact.va.service.NotificationService;


@Tag(name = "Notifications", description = "Manage Notifications")
@RequestMapping("/api/v1/notifications")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class NotificationController {

    private final NotificationService service;

    private final InterviewService interviewService;

    private final ModelMapper modelMapper;

    public NotificationController(NotificationService service, InterviewService interviewService, ModelMapper modelMapper) {
        this.service = service;
        this.interviewService = interviewService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    @Operation(summary = "Get all notifications", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification fetched successfully"),
    })
    public Page<ListNotificationDTO> findAll(Pageable pageable) {

        return service.findAll(pageable).map(notification -> modelMapper.map(notification, ListNotificationDTO.class));

    }


    @GetMapping("/{id}")
    @Operation(summary = "Get  notification by ID", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification fetched successfully"),
    })
    public DetailNotificationDTO findById(@PathVariable String id) {

        return modelMapper.map(service.findById(id), DetailNotificationDTO.class);

    }



    @PostMapping
    @Operation(summary = "Create new notification", description = "")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notification created successfully"),
            @ApiResponse(responseCode = "400", description = "Notification fields are not filled in correctly")
    })
    public CreatedNotificationDTO authenticate(@RequestBody @Valid CreateNotificationDTO createNotificationDTO) {

        Notification notification=modelMapper.map(createNotificationDTO,Notification.class);

        return modelMapper.map(service.create(notification),CreatedNotificationDTO.class);

    }
}
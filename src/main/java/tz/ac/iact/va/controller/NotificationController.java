package tz.ac.iact.va.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.ac.iact.va.dto.notification.CreateNotificationDTO;
import tz.ac.iact.va.dto.notification.CreatedNotificationDTO;
import tz.ac.iact.va.model.Notification;
import tz.ac.iact.va.service.NotificationService;


@Tag(name = "Notifications", description = "Manage Notifications")
@RequestMapping("/api/v1")
@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class NotificationController {

    private final NotificationService service;

    private final ModelMapper modelMapper;

    public NotificationController(NotificationService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
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
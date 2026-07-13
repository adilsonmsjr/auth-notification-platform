package projects.notification_services.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import projects.notification_services.dto.RequestNotificationDto;
import projects.notification_services.dto.ResponseDto;
import projects.notification_services.services.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> sendMessage(@RequestBody RequestNotificationDto dto){

        return ResponseEntity.ok(notificationService.sendNotification(dto));

    }



}

package com.pms.notification_service.controller;

import com.pms.notification_service.model.NotificationResponseDto;
import com.pms.notification_service.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The { NotificationController} class is a Spring REST controller that handles HTTP requests related to project notifications.
 * It provides endpoints for managing and triggering notifications based on project deadlines.
 *
 * BVGC BORALUWA 28-08-24 23196
 */
 @RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint to trigger notification check for a specific project by ID
    @GetMapping("/check/{id}")
    public ResponseEntity<?> checkProjectDeadline(@PathVariable Long id) {
        try {
            NotificationResponseDto response = notificationService.sendNotificationForProject(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}



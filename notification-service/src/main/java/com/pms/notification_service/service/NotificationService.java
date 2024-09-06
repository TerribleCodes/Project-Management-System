package com.pms.notification_service.service;

import com.pms.notification_service.model.NotificationResponseDto;
import com.pms.notification_service.model.Notification;
import com.pms.notification_service.repository.NotificationRepository;
import com.pms.notification_service.model.Project;
import com.pms.notification_service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * The {NotificationService} class provides business logic for generating and managing notifications related to project deadlines.
 * It interacts with the Project Management System (PMS) to monitor project deadlines and create notifications when a project's deadline
 * is approaching within the next 30 days
 *
 * BVGC BORALUWA 28-08-24 23196
 */

@Service
public class NotificationService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationResponseDto sendNotificationForProject(Long projectId) throws Exception {
        // Find the project by ID
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new Exception("Project not found with ID: " + projectId));

        LocalDateTime now = LocalDateTime.now();
        long daysUntilDeadline = ChronoUnit.DAYS.between(now, project.getDeadLine());

        // If the deadline is less than or equal to 30 days away
        if (daysUntilDeadline <= 30 && daysUntilDeadline >= 0) {
            Notification notification = new Notification();
            notification.setNotification("Project '" + project.getTitle() + "' is due in " + daysUntilDeadline + " days.");
            notification.setTime(now);

            // Save the notification to the database
            Notification savedNotification = notificationRepository.save(notification);

            // Return the notification response DTO
            return new NotificationResponseDto(
                    savedNotification.getNotificationId(),
                    savedNotification.getNotification(),
                    savedNotification.getTime()
            );
        } else {
            throw new Exception("No notification sent. Project deadline is more than 30 days away.");
        }
    }
}

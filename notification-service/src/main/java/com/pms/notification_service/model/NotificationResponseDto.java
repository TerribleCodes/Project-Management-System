package com.pms.notification_service.model;
import java.time.LocalDateTime;

public class NotificationResponseDto {
    private Long notificationId;
    private String notification;
    private LocalDateTime time;

    // Constructors
    public NotificationResponseDto() {}

    public NotificationResponseDto(Long notificationId, String notification, LocalDateTime time) {
        this.notificationId = notificationId;
        this.notification = notification;
        this.time = time;
    }

    // Getters and Setters
    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}

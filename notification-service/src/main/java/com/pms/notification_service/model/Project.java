package com.pms.notification_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    // Primary Key: Project ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Project title
    private String title;

    // Project description
    private String description;

    // Image for the project
    private String image;

    // ID of the user assigned to the project
    private Long assignedUserId;

    // Tags for the project
    @ElementCollection
    private List<String> tags = new ArrayList<>();

    // Enum for project status (PENDING, ASSIGNED, DONE)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    // Project deadline date and time
    private LocalDateTime deadLine;

    // Project creation date and time
    private LocalDateTime createdAt;
}

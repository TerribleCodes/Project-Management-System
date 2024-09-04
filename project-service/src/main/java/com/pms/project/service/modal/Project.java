package com.pms.project.service.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

//    create the modal for projects


    // project id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // project name(title)
    private String title;

    // project description
    private String description;

    // image for the project
    private String image;

    // id of the user assigned to the project
    private Long assignedUserId;

    // tags for the project
    private List<String> tags = new ArrayList<>();

//    status of project(
//            PENDING,
//            ASSIGNED or
//            DONE)
    private ProjectStatus status;

    // project deadline date and time
    private LocalDateTime deadLine;

    // project created date and time
    private LocalDateTime createdAt;

}

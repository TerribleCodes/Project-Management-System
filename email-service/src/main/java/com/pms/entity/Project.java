package com.pms.entity;

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

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED Project Model
 * */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue(strategy = GenerationType.IDENTITY) - FOR TESTING
    private Long id;

    private String title;
    private String description;
    private String image;
    private Long assignedUserId;
    private List<String> tags = new ArrayList<>();
    private TaskStatus status;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}

package com.pms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED Submission Model
 * */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue(strategy = GenerationType.IDENTITY) - FOR TESTING
    private Long id;

    private Long taskId;
    private String githubLink;
    private Long userId;
    private String status="PENDING";
    private LocalDateTime submissionTime;
}

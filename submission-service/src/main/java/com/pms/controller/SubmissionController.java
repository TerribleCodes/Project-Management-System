package com.pms.controller;

import com.pms.modal.Submission;
import com.pms.modal.UserDto;
import com.pms.service.SubmissionService;
import com.pms.service.ProjectService;
import com.pms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @PostMapping()
    public ResponseEntity<Submission>submitProject(
            @RequestParam Long project_id,
            @RequestParam String github_link,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Submission submission=submissionService.submitProject(project_id,github_link,user.getId(),jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }




    @GetMapping("/{id}")
    public ResponseEntity<Submission>getSubmissionById(
            @PathVariable Long id,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Submission submission=submissionService.getProjectSubmissionById(id);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<List<Submission>>getAllSubmissions(

            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Submission> submissions =submissionService.getAllProjectSubmissions();
        return new ResponseEntity<>(submissions, HttpStatus.CREATED);
    }



    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Submission>>getAllSubmissions(
            @PathVariable long projectId,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        List<Submission> submissions =submissionService.getProjectSubmissionsByProjectId(projectId);
        return new ResponseEntity<>(submissions, HttpStatus.CREATED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Submission>acceptOrDeclineSubmission(
            @PathVariable long id,
            @RequestParam("status") String status,
            @RequestHeader ("Authorization") String jwt
    )throws Exception{
        UserDto user=userService.getUserProfile(jwt);
        Submission submission=submissionService.acceptDeclineSubmission(id,status);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }



}

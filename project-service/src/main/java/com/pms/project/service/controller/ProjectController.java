package com.pms.project.service.controller;

import com.pms.project.service.modal.Project;
import com.pms.project.service.modal.ProjectStatus;
import com.pms.project.service.modal.UserDto;
import com.pms.project.service.service.ProjectService;
import com.pms.project.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    // get project service
    @Autowired
    private ProjectService projectService;

    // get user service
    @Autowired
    private UserService userService;

    // create a new project
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project, @RequestHeader("Authorization") String jwt) throws Exception {
        // get user details
        UserDto user = userService.getUserProfile(jwt);
        // create a new project
        Project createdProject = projectService.createProject(project, user.getRole());

        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    // get project using project id
    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable Long id, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Project project = projectService.getProjectById(id);

        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    // get projects assigned for a user
    @GetMapping("/user")
    public ResponseEntity<List<Project>> assignedUsersProject(@RequestParam(required = false)ProjectStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        // create a list of projects
        List<Project> projects = projectService.assignedUsersProjects(user.getId(),status);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // get all projects
    @GetMapping()
    public ResponseEntity<List<Project>> getAllProject(@RequestParam(required = false)ProjectStatus status, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);
        // create a list of projects
        List<Project> projects = projectService.getAllProject(status);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // assign a project to user
    @PutMapping("/{id}/user/{userId}/assigned")
    public ResponseEntity<Project> assignedProjectToUser(@PathVariable Long id,@PathVariable Long userId, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Project projects = projectService.assignedToUser(userId,id);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // update a project
    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id,@RequestBody Project req, @RequestHeader("Authorization") String jwt) throws Exception {

        UserDto user = userService.getUserProfile(jwt);

        Project projects = projectService.updateProject(id,req,user.getId());

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // complete a project
    @PutMapping("/{id}/complete")
    public ResponseEntity<Project> completeProject(@PathVariable Long id) throws Exception {

        Project projects = projectService.completeProject(id);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // delete a project
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) throws Exception {

        projectService.deleteProject(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

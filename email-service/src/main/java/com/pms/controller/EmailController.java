package com.pms.controller;

import com.pms.entity.Project;
import com.pms.entity.User;
import com.pms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * LHCMD SILVA | 22783 | 01.09.2024 | CREATED REST Controller
 * */

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-user-created")
    public String sendUserCreated(@RequestBody User user) {
        // 200 OK (02.09.2024 @4:47PM)
        emailService.sendUserCreatedEmail(user);

        // 200 OK (02.09.2024 @4:32PM)
        return "User creation email sent successfully!";
    }

    @PostMapping("/send-user-added-to-project")
    public String sendUserAddedToProject(@RequestParam Long id, @RequestBody Project project) {
        // 200 OK (02.09.2024 @9:14PM)
        emailService.sendUserAddedToProject(id, project);

        // 200 OK (02.09.2024 @4:35PM)
        return "User added to project email sent successfully!";
    }

    @PostMapping("/send-project-completed")
    public String sendProjectCompleted(@RequestParam Long id, @RequestBody Project project) {
        emailService.sendProjectCompletedEmail(id, project);

        // 200 OK (02.09.2024 @4:36PM)
        return "Project completion email sent successfully!";
    }

    @GetMapping("/send-assigned-projects/{id}")
    public String sendAssignedProjects(@PathVariable Long id) {
        emailService.sendAssignedProjectsToUser(id);

        // 200 OK (02.09.2024 @4:39PM)
        return "Assigned project data email sent successfully!";
    }
}


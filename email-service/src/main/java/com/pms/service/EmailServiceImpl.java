package com.pms.service;

import com.pms.entity.Project;
import com.pms.entity.User;
import com.pms.repository.ProjectRepository;
import com.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/*
 * LHCMD SILVA | 22783 | 01.09.2024 | CREATED Email Service Implementation
 * */

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // 1. Send email when a new user is created
    @Override
    public void sendUserCreatedEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Project Management System");

        // Welcome Message
        String emailBody = "Hello,\n\n" +
                "Welcome to the Project Management System. \n\n" +
                "Your role as " + user.getRole() + " will allow you to contribute to various projects and manage tasks.\n\n" +
                "If you ever need assistance or have any questions, feel free to reach out to us at support@projectmanagement.com.\n\n" +
                "Your login details:\n" +
                "- Email: " + user.getEmail() + "\n" +
                "- Password: " + user.getPassword() + " (Please ensure to change your password after your first login)\n\n" +
                "Best regards,\n" +
                "The Project Management Team";

        message.setText(emailBody);
        mailSender.send(message);
    }

    // 2. Send email when user is added to a project
    @Override
    public void sendUserAddedToProject(Long id, Project project) {
        User user = userRepository.findById(id).orElseThrow();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());

        // User message
        message.setSubject("You have been added to the project: " + project.getTitle());
        message.setText("Dear " + user.getEmail() + ", you have been added to the project " + project.getTitle());
        mailSender.send(message);
    }

    // 3. Scheduled reminders for project deadlines
    @Override
    @Scheduled(cron = "0 0 12 * * ?")  // Runs every day at 12 PM
    public void sendDeadlineReminders() {
        List<Project> projects = projectRepository.findAll();
        for (Project project : projects) {
            LocalDateTime deadline = project.getDeadline();
            LocalDate today = LocalDate.now();
            long daysLeft = java.time.Duration.between(today.atStartOfDay(), deadline).toDays();

            if (daysLeft == 30 || daysLeft == 14 || daysLeft == 7 || daysLeft == 1) {
                User user = userRepository.findById(project.getAssignedUserId()).orElseThrow();
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(user.getEmail());
                message.setSubject("Reminder: " + daysLeft + " days left for project: " + project.getTitle());
                message.setText("Dear " + user.getId() + ", only " + daysLeft + " days are left for the project submission.");
                mailSender.send(message);
            }
        }
    }

    // 4. Send email when project is completed
    @Override
    public void sendProjectCompletedEmail(Long userId, Project project) {
        User user = userRepository.findById(userId).orElseThrow();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Project Completed: " + project.getTitle());
        message.setText("Hello, your project " + project.getTitle() + " has been successfully completed.");
        mailSender.send(message);
    }

    // 5. Send latest project data to a user
    @Override
    public void sendAssignedProjectsToUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        List<Project> projects = projectRepository.findByAssignedUserId(id);
        StringBuilder projectData = new StringBuilder();

        for (Project project : projects) {
            projectData.append("Title: ").append(project.getTitle())
                    .append("\nDescription: ").append(project.getDescription())
                    .append("\nStatus: ").append(project.getStatus())
                    .append("\nDeadline: ").append(project.getDeadline())
                    .append("\n\n");
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Your Assigned Projects");
        message.setText(projectData.toString());
        mailSender.send(message);
    }
}

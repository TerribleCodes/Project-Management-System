package com.pms.service;

import com.pms.entity.Project;
import com.pms.entity.User;

/*
 * LHCMD SILVA | 22783 | 01.09.2024 | CREATED Email Service
 * */

public interface EmailService {

    // 1. Send email when a new user is created
    void sendUserCreatedEmail(User user);

    // 2. Send email when user is added to a project
    void sendUserAddedToProject(Long id, Project project);

    // 3. Scheduled reminders for project deadlines
    void sendDeadlineReminders();

    // 4. Send email when a project is completed
    void sendProjectCompletedEmail(Long id, Project project);

    // 5. Send latest project data to a user
    void sendAssignedProjectsToUser(Long id);
}

package com.pms.project.service.service;

import com.pms.project.service.ProjectServiceApplication;
import com.pms.project.service.modal.Project;
import com.pms.project.service.modal.ProjectStatus;

import java.util.List;

public interface ProjectService {

    //    create methods

    Project createProject(Project project,String requesterRole)throws Exception;

    Project getProjectById(Long id)throws Exception;

    List<Project> getAllProject(ProjectStatus status);

    Project updateProject(Long id,Project updatedProject,Long userId)throws Exception;

    void deleteProject(Long id) throws Exception;

    Project assignedToUser(Long userId, Long projectId)throws Exception;

    List<Project> assignedUsersProjects(Long userId, ProjectStatus status);

    Project completeProject(Long projectId)throws Exception;
}

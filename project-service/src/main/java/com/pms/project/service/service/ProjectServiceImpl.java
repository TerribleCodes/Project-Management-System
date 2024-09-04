package com.pms.project.service.service;

import com.pms.project.service.modal.Project;
import com.pms.project.service.modal.ProjectStatus;
import com.pms.project.service.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    // implementation services

    // get the project repository
    @Autowired
    private ProjectRepository projectRepository;

    // method implementation for create a new project
    @Override
    public Project createProject(Project project, String requesterRole) throws Exception {
        if (!requesterRole.equals(("ROLE_ADMIN"))){
            throw new Exception("only admin can create project");
        }
        // set project status to pending
        project.setStatus(ProjectStatus.PENDING);
        // set project created date and time
        project.setCreatedAt(LocalDateTime.now());
        return projectRepository.save(project);
    }

    // method implementation for get the project using project id
    @Override
    public Project getProjectById(Long id) throws Exception {
        return projectRepository.findById(id).orElseThrow(()->new Exception("project not found with id"+id));
    }

    // method implementation for get the project using the project status
    @Override
    public List<Project> getAllProject(ProjectStatus status) {
        // get all projects
        List<Project> allProjects = projectRepository.findAll();
        //  if user not entered the status(status == null) return all projects
        // if user entered the status return the projects equal to the status
        List<Project> filteredProjects = allProjects.stream().filter(
                project -> status==null || project.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filteredProjects;
    }

    // method implementation for update the project
    @Override
    public Project updateProject(Long id, Project updatedProject, Long userId) throws Exception {
        Project existingProject =getProjectById(id);

        if (updatedProject.getTitle()!=null){
            existingProject.setTitle(updatedProject.getTitle());
        }
        if (updatedProject.getImage()!=null){
            existingProject.setImage(updatedProject.getImage());
        }
        if (updatedProject.getDescription()!=null){
            existingProject.setDescription(updatedProject.getDescription());
        }
        if (updatedProject.getStatus()!=null){
            existingProject.setStatus(updatedProject.getStatus());
        }
        if (updatedProject.getDeadLine()!=null){
            existingProject.setDeadLine(updatedProject.getDeadLine());
        }
        return projectRepository.save(existingProject);
    }

    // method implementation for delete a project
    @Override
    public void deleteProject(Long id) throws Exception {
        getProjectById(id);
        projectRepository.deleteById(id);
    }

    // method implementation for assigning a project to user
    @Override
    public Project assignedToUser(Long userId, Long projectId) throws Exception {
        Project project = getProjectById(projectId);
        project.setAssignedUserId(userId);
        project.setStatus(ProjectStatus.ASSIGNED);
        return projectRepository.save(project);
    }

    // method implementation for filtering projects
    @Override
    public List<Project> assignedUsersProjects(Long userId, ProjectStatus status) {
        List<Project> allProjects = projectRepository.findByAssignedUserId(userId);
        List<Project> filteredProjects = allProjects.stream().filter(
                project -> status==null || project.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filteredProjects;
    }

    // method implementation for set status as done to completed projects
    @Override
    public Project completeProject(Long projectId) throws Exception {
        Project project = getProjectById(projectId);
        project.setStatus(ProjectStatus.DONE);
        return projectRepository.save(project);
    }
}

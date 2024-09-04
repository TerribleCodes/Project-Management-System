package com.pms.project.service.repository;

import com.pms.project.service.modal.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
    public List<Project> findByAssignedUserId(Long userId);
}

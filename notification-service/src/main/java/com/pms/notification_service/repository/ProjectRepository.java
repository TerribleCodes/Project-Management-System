package com.pms.notification_service.repository;

import com.pms.notification_service.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Custom query method to find projects where deadline is less than 30 days
    List<Project> findByDeadLineBefore(java.time.LocalDateTime dateTime);
}

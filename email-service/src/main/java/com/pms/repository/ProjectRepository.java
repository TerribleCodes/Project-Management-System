package com.pms.repository;

import com.pms.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED Project Repository
 * */

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByAssignedUserId(Long id);
}

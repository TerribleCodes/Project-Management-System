package com.pms.repository;

import com.pms.modal.Submission;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission,Long> {

    List<Submission> findByProjectId(Long projectId);


}

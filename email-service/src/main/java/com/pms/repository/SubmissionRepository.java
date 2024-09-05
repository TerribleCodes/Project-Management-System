package com.pms.repository;

import com.pms.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * LHCMD SILVA | 22783 | 30.08.2024 | CREATED Submission Repository
 * */

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
}
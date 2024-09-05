package com.pms.service;

import com.pms.modal.Submission;

import java.util.List;

public interface SubmissionService {

    Submission submitProject(Long projectId,String githubLink, Long userId, String jwt) throws Exception;

    Submission getProjectSubmissionById(Long submissionId) throws Exception;

    List<Submission> getAllProjectSubmissions();

    List<Submission> getProjectSubmissionsByProjectId(Long projectId);

    Submission acceptDeclineSubmission(Long id, String status) throws Exception;


}

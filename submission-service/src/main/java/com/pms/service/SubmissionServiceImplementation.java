package com.pms.service;

import com.pms.modal.Submission;
import com.pms.modal.ProjectDto;
import com.pms.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImplementation implements SubmissionService{

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private ProjectService projectService;

    @Override
    public Submission submitProject(Long projectId, String githubLink, Long userId, String jwt) throws Exception {
        ProjectDto task= projectService.getProjectById(projectId,jwt);
        if(task!=null){
            Submission submission = new Submission();
            submission.setProjectId(projectId);
            submission.setUserId(userId);
            submission.setGithubLink(githubLink);
            submission.setSubmissionTime(LocalDateTime.now());
            return submissionRepository.save(submission);

        }
        throw new Exception("Task not found with id : "+projectId);
    }



    @Override
    public Submission getProjectSubmissionById(Long submissionId) throws Exception {
        return submissionRepository.findById(submissionId).orElseThrow(()->
                new Exception("Task Submission not found with id"+submissionId));
    }

    @Override
    public List<Submission> getAllProjectSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public List<Submission> getProjectSubmissionsByProjectId(Long projectId) {
        return submissionRepository.findByProjectId(projectId);
    }

    @Override
    public Submission acceptDeclineSubmission(Long id, String status) throws Exception {
        Submission submission=getProjectSubmissionById(id);
        submission.setStatus(status);
        if(status.equals("ACCEPT")){
            projectService.completeProject(submission.getProjectId());
        }
        return submissionRepository.save(submission);
    }
}

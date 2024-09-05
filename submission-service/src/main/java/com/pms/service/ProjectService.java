package com.pms.service;

import com.pms.modal.ProjectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "submission-service",url = "http://localhost:5003/")
public interface ProjectService {

    @GetMapping("/api/projects/{id}")
    public ProjectDto getProjectById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt) throws Exception;

    @PutMapping("/api/projects/{id}/complete")
    public ProjectDto completeProject(
            @PathVariable Long id) throws Exception;







}

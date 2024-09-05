
package com.pms.controller;

import com.itextpdf.io.font.constants.StandardFonts;
import com.pms.model.Project;
import com.pms.model.Submission;
import com.pms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pms.service.ReportService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/home")
    public String home(){
        return "Hello";
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return reportService.getAllUsers();
    }

    @GetMapping("/submission")
    public List<Submission> getAllSubmissions() {
        return reportService.getAllSubmissions();
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return reportService.getAllProjects();
    }


    // Endpoint to generate PDF report for users
    @GetMapping(value = "/users/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getUserReport() {
        ByteArrayInputStream bis = reportService.generateUserReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=user_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bis.readAllBytes());
    }

    // Endpoint to generate PDF report for submissions
    @GetMapping(value = "/submissions/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getSubmissionReport() {
        ByteArrayInputStream bis = reportService.generateSubmissionReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=submission_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bis.readAllBytes());
    }

    // Endpoint to generate PDF report for projects
    @GetMapping(value = "/projects/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getProjectReport() {
        ByteArrayInputStream bis = reportService.generateProjectReport();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=project_report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(bis.readAllBytes());
    }
}

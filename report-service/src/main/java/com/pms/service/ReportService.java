package com.pms.service;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.pms.model.Project;
import com.pms.model.Submission;
import com.pms.model.User;
import org.springframework.stereotype.Service;
import com.pms.repository.ProjectRepository;
import com.pms.repository.SubmissionRepository;
import com.pms.repository.UserRepository;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportService {
    private final UserRepository userRepository;
    private final SubmissionRepository submissionRepository;
    private final ProjectRepository projectRepository;

    public ReportService(UserRepository userRepository, SubmissionRepository submissionRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.submissionRepository = submissionRepository;
        this.projectRepository = projectRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    // Method to generate PDF report for users
    public ByteArrayInputStream generateUserReport() {
        List<User> users = userRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            document.add(new Paragraph("User Report").setFontSize(18));
            for (User user : users) {
                document.add(new Paragraph("ID: " + user.getId()));
                document.add(new Paragraph("Email: " + user.getEmail()));
                document.add(new Paragraph("Role: " + user.getRole()));
                document.add(new Paragraph("Password: " + user.getPassword()));
                document.add(new Paragraph(" "));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Method to generate PDF report for submissions
    public ByteArrayInputStream generateSubmissionReport() {
        List<Submission> submissions = submissionRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            document.add(new Paragraph("Submission Report").setFontSize(18));
            for (Submission submission : submissions) {
                document.add(new Paragraph("ID: " + submission.getId()));
                document.add(new Paragraph("Task ID: " + submission.getTaskId()));
                document.add(new Paragraph("Github Link: " + submission.getGithubLink()));
                document.add(new Paragraph("User ID: " + submission.getUser().getId()));
                document.add(new Paragraph("Status: " + submission.getStatus()));
                document.add(new Paragraph("Submission Time: " + submission.getSubmissionTime()));
                document.add(new Paragraph(" "));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    // Method to generate PDF report for projects
    public ByteArrayInputStream generateProjectReport() {
        List<Project> projects = projectRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            document.setFont(font);

            document.add(new Paragraph("Project Report").setFontSize(18));
            for (Project project : projects) {
                document.add(new Paragraph("ID: " + project.getId()));
                document.add(new Paragraph("Title: " + project.getTitle()));
                document.add(new Paragraph("Description: " + project.getDescription()));
                document.add(new Paragraph("Assignment: " + project.getAssignment()));
                document.add(new Paragraph("User ID: " + project.getUser().getId()));
                document.add(new Paragraph("Status: " + project.getStatus()));
                document.add(new Paragraph("Deadline: " + project.getDeadline()));
                document.add(new Paragraph("Created At: " + project.getCreatedAt()));
                document.add(new Paragraph(" "));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}

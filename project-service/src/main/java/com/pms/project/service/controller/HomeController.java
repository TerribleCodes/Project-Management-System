package com.pms.project.service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/projects")
    public ResponseEntity<String> projects() {
        //display the welcome massage
        return new ResponseEntity<>("welcome to project service", HttpStatus.OK);
    }
}

package com.cpyproject2spring.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cpyproject2spring.demo2.entities.ResponseMassage;
import com.cpyproject2spring.demo2.service.FileService;

@RestController
public class FileController {
    
    @Autowired
    private FileService fileService;


    @PostMapping("/upload")
    public ResponseEntity<ResponseMassage> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String generatedFileName = fileService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseMassage(HttpStatus.OK, "File uploaded successfully", generatedFileName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
                new ResponseMassage(HttpStatus.EXPECTATION_FAILED, "File upload failed", null));
        }

    }


}

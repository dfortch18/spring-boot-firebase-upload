package com.dfortch.sfirebaseupload.controller;

import com.dfortch.sfirebaseupload.service.impl.FirebaseStorageService;
import com.google.cloud.storage.Blob;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class UploadController {

    private final FirebaseStorageService firebaseStorageService;

    public UploadController(FirebaseStorageService firebaseStorageService) {
        this.firebaseStorageService = firebaseStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Blob blob = firebaseStorageService.uploadFile(file.getOriginalFilename(), file);

        return ResponseEntity.ok(blob.getMediaLink());
    }
}

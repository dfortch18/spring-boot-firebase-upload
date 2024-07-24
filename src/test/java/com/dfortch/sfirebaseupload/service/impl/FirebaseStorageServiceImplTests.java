package com.dfortch.sfirebaseupload.service.impl;

import com.dfortch.sfirebaseupload.service.impl.impl.FirebaseStorageServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FirebaseStorageServiceImplTests {

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @Test
    void uploadGetAndDeleteFile() throws IOException {
        MultipartFile file = new MockMultipartFile("hello-world.txt", "Hello World".getBytes());

        String blobName = "file.txt";

        assertNotNull(firebaseStorageService.uploadFile(blobName, file));

        assertNotNull(firebaseStorageService.getFileUrl(blobName));

        assertTrue(firebaseStorageService.deleteFile(blobName));
    }
}

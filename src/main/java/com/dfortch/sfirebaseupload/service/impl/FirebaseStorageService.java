package com.dfortch.sfirebaseupload.service.impl;

import com.google.cloud.storage.Blob;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FirebaseStorageService {

    String getFileUrl(String blob);

    Blob uploadFile(String blobName, MultipartFile file) throws IOException;

    boolean deleteFile(String blobName);
}

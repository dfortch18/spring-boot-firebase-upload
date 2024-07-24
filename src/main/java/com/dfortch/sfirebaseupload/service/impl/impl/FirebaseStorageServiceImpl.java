package com.dfortch.sfirebaseupload.service.impl.impl;

import com.dfortch.sfirebaseupload.service.impl.FirebaseStorageService;
import com.google.cloud.storage.Blob;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FirebaseStorageServiceImpl implements FirebaseStorageService {

    private final StorageClient firebaseStorage;

    public FirebaseStorageServiceImpl(StorageClient firebaseStorage) {
        this.firebaseStorage = firebaseStorage;
    }

    @Override
    public String getFileUrl(String blob) {
        return firebaseStorage.bucket().get(blob).getMediaLink();
    }

    @Override
    public Blob uploadFile(String blobName, MultipartFile file) throws IOException {
        return firebaseStorage.bucket().create(blobName, file.getBytes());
    }

    @Override
    public boolean deleteFile(String blobName) {
        return firebaseStorage.bucket().get(blobName).delete();
    }
}

package com.dfortch.sfirebaseupload.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;

@Configuration
public class FirebaseConfig {

    private static final Logger log = LoggerFactory.getLogger(FirebaseConfig.class);

    @Value("${firebase.credentials-path}")
    private String firebaseCredentialsPath;

    @Value("${firebase.storage.bucket-name}")
    private String firebaseStorageBucketName;

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        log.info("Initializing FirebaseApp with credentials path: {}", firebaseCredentialsPath);

        try (InputStream inputStream = new FileInputStream(firebaseCredentialsPath)) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .setStorageBucket(firebaseStorageBucketName)
                    .build();

            FirebaseApp.initializeApp(options);

            FirebaseApp app = FirebaseApp.getInstance();

            if (app != null) {
                log.info("FirebaseApp instance obtained successfully.");
                return app;
            } else {
                String errorMessage = "FirebaseApp initialization failed, no FirebaseApp instance available.";
                log.error(errorMessage);
                throw new IOException(errorMessage);
            }
        } catch (IOException e) {
            log.error("Failed to initialize FirebaseApp with credentials from path: {}", firebaseCredentialsPath, e);
            throw e;
        }
    }

    @Bean
    public StorageClient firebaseStorage(FirebaseApp firebaseApp) {
        return StorageClient.getInstance(firebaseApp);
    }
}

# Spring Boot Firebase Upload

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/es/)
[![Maven](https://img.shields.io/badge/Apache_Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![JUnit](https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Firebase](https://img.shields.io/badge/firebase-ffca28?style=for-the-badge&logo=firebase&logoColor=black)](https://firebase.google.com)

This project is a demonstration of how to upload files to Firebase Storage using Spring Boot.

## Requirements

- Java 21
- Maven
- Firebase account with access to Firebase Storage and a JSON credentials file

## Configuration

1. **Configure Firebase**

   Make sure you have a project in Firebase and download the JSON credentials file. This file is used to authenticate the application with Firebase.

2. **Set up environment variables**

   We use environment variables to configure access to Firebase. Create a `.env` file in the root directory of the project based on the example file `.env.example`:

   ```env
   FIREBASE_CREDENTIALS_PATH=/path/to/your/firebase/credentials.json
   FIREBASE_STORAGE_BUCKET_NAME=your-firebase-storage-bucket
   ```

   Replace `/path/to/your/firebase/credentials.json` with the path to the JSON credentials file and `your-firebase-storage-bucket` with the name of your Firebase Storage bucket.

3. **Configure the `application.properties` file**

   The `src/main/resources/application.properties` file should contain:

   ```conf
   spring.application.name=spring-firebase-upload

   firebase.credentials-path=${FIREBASE_CREDENTIALS_PATH}
   firebase.storage.bucket-name=${FIREBASE_STORAGE_BUCKET_NAME}
   ```

## Running the application

1. **Load the environment variables**

   Make sure the environment variables are set. In IntelliJ IDEA, you can add the variables from the `.env` file to the environment variables in the run configurations:

   - Open the "Run/Debug Configurations" dialog.
   - Select your run configuration.
   - In the "Environment Variables" field, click the folder button.
   - Choose the `.env` file in the file chooser.
   - Save the changes.

   You can find the Jetbrains Official Tutorial [here](https://www.jetbrains.com/help/objc/add-environment-variables-and-program-arguments.html#add-environment-variables).

2. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

   The application will be available at `http://localhost:8080`.

## Endpoints

   - **POST `/upload`**

      This endpoint allows you to upload files to Firebase Storage. You can send files using the file parameter in the request.

      Example usage with curl:

      ```bash
      curl -F "file=@/path/to/your/file.txt" http://localhost:8080/upload
      ```

      It will return the public link of the uploaded file.

## Tests

Tests are set up to verify the upload, retrieval, and deletion of files in Firebase Storage. To run the tests, use the following command:

```bash
mvn test
```


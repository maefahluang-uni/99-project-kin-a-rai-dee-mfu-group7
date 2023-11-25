package th.mfu;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
@Service
public class FileStorageService {

@Value("${upload.path}")
private String uploadPath;

    private static final String UPLOAD_DIR = "/path/to/your/upload/directory"; // Specify your upload directory

    public String storeFile(MultipartFile file) throws IOException {
        // Generate a unique filename for the file
        String fileName = generateFileName(file);

        // Create the upload directory if it doesn't exist
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // Save the file to the server
        file.transferTo(new File(uploadDir, fileName));

        return fileName;
    }

    private String generateFileName(MultipartFile file) {
        // Generate a unique filename using UUID and keep the original file extension
        return UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
    }

    public void deleteFile(String fileName) {
        Path filePath = Paths.get(uploadPath).resolve(fileName);
        FileSystemUtils.deleteRecursively(filePath.toFile());
    }

}

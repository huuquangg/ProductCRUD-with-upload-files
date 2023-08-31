package com.cpyproject2spring.demo2.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FilenameUtils;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import com.cpyproject2spring.demo2.repositories.FileRepository;

@Service
public class FileServiceImpl implements FileService{
    
    private final Path fileStorageLocation = Paths.get("upload").toAbsolutePath().normalize();

    // @Autowired
    // private FileRepository fileRepository;

    private boolean isImgFile(MultipartFile file) {
        // check file is image
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList("png", "jpg", "jpeg").contains(fileExtension.trim().toLowerCase());
    }



    @Override
    public String storeFile(MultipartFile fileName) {
        try {
            if(fileName.isEmpty()) {
                throw new RuntimeException("Failed to store empty file") ;
            }

            if (!isImgFile(fileName)) {
                throw new RuntimeException("Failed to store file, file is not image");
            }

            float fileSize = fileName.getSize() / 1000000;
            if(fileSize > 5.0f) {
                throw new RuntimeException("Failed to store file, file must be less than 5MB");
            }
            
            String fileExtention = FilenameUtils.getExtension(fileName.getOriginalFilename());
            String generatedfileName = UUID.randomUUID().toString().replace("-", "");
            generatedfileName = generatedfileName + "." + fileExtention;
            Path targetLocation = this.fileStorageLocation.resolve(Paths.get(generatedfileName))
                                                                        .normalize()
                                                                        .toAbsolutePath();
            if(targetLocation.getParent().equals(this.fileStorageLocation.toAbsolutePath())) {
                throw new RuntimeException("Failed to store file, file is not image");
            }
            try(InputStream InputStream = fileName.getInputStream()) {
                Files.copy(InputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedfileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file", ex);
        }
    }



    @Override
    public Stream<Path> loadAll() {
        throw new UnsupportedOperationException("Unimplemented method 'loadAll'");
    }

    @Override
    public byte[] readFileContent(String fileName) {
        throw new UnsupportedOperationException("Unimplemented method 'readFileContent'");
    }

    @Override
    public void deleteFile(String fileName) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteFile'");
    }

    

     
}

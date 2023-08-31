package com.cpyproject2spring.demo2.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String storeFile(MultipartFile fileName);
    public Stream<Path> loadAll();
    public byte[] readFileContent(String fileName);
    public void deleteFile(String fileName);
}

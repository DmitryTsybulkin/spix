package com.swallow.spix.service;

import com.swallow.spix.exceptions.StorageException;
import com.swallow.spix.exceptions.StorageFileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileStorageImpl implements FileStorage {

    @Value("${upload.path}")
    private String path;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init() {
        try {
            Files.createDirectory(Paths.get(path));
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                log.error("StorageException: failed to store empty file");
                throw new StorageException();
            }
            Files.copy(file.getInputStream(), Paths.get(this.path).resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(Paths.get(this.path), 1)
                    .filter(path1 -> !path1.equals(Paths.get(this.path)))
                    .map(path1 -> Paths.get(this.path).relativize(path1));
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        return null;
    }

    @Override
    public Path load(String filename) {
        return Paths.get(this.path).resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException();
            }
        } catch (MalformedURLException e) {
            e.getLocalizedMessage();
        }
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(this.path).toFile());
    }
}

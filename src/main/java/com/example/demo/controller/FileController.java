package com.example.demo.controller;

import com.example.demo.model.DBFile;
import com.example.demo.service.DBFileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private DBFileStorageService dbFileStorageService;

    @PostMapping("/upload/{name}")
    public DBFile uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String name) {
    	
        DBFile dbFile = dbFileStorageService.storeFile(file, name, "");

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return dbFile;
    }

    @PostMapping("/upload/{name}/{partner}")
    public DBFile uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String name, @PathVariable String partner) {

        DBFile dbFile = dbFileStorageService.storeFile(file, name, partner);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return dbFile;
    }

    @GetMapping("/download/{fileId}")
    public DBFile downloadFileById(@PathVariable String fileId) {
        // Load file from database
        DBFile dbFile = dbFileStorageService.getFileById(fileId);
        if (dbFile != null) {
        	logger.info("Got file " + dbFile.getFileType() + " " + dbFile.getFileName());
        } else {
        	logger.error("File not found");
        }
        return dbFile;
    }
    
    @GetMapping("/download/type/{type}")
    public List<DBFile> downloadFilesByType(@PathVariable String type) {
    	return dbFileStorageService.getFileByType(type);
    }
    
    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> removeFile(@PathVariable String id) {
    	dbFileStorageService.removeFile(id);
		 logger.info("Deleted file : " + id);
		 return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}
}
package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.DBFile;

import java.util.List;

public interface IDBFileStorageService {

	public DBFile storeFile(MultipartFile file, String name, String partner);
	public DBFile getFileById(String fileId);
	public List<DBFile> getFileByType(String type);
	public void removeFile(String id);
}

package com.example.demo.service;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.DBFile;
import com.example.demo.repository.DBFileRepository;

import java.io.IOException;
import java.util.List;

@Service
public class DBFileStorageService implements IDBFileStorageService{

	final static Logger logger = Logger.getLogger(DBFileStorageService.class);

	@Autowired
	private DBFileRepository dbFileRepository;

	@Override
	public DBFile storeFile(MultipartFile file, String name, String partner) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if (fileName.contains("..")) {
				logger.error("Sorry! Filename contains invalid path sequence " + fileName);
			}
			DBFile dbFile = new DBFile(name + '.' + fileName.substring(fileName.lastIndexOf('.') + 1), file.getContentType(), file.getBytes());
			dbFile.setPartner(partner);
			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			logger.error("Could not store file " + fileName + ". Please try again!", ex);
		}
		return new DBFile();
	}
	
	private DBFile getEmptyFile() {
	    return new DBFile();
	}

	@Override
	public DBFile getFileById(String fileId) {
		return dbFileRepository.findById(fileId).orElse(getEmptyFile());
	}

	@Override
	public List<DBFile> getFileByType(String type) {
		return dbFileRepository.findFileByType(type);
	}

	@Override
	public void removeFile(String id) {
		logger.info("delete file with id " + id);
		dbFileRepository.deleteById(id);
	}

}
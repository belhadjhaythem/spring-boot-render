package com.example.demo.controller;

import com.example.demo.model.DBFile;
import com.example.demo.model.Model;
import com.example.demo.service.DBFileStorageService;
import com.example.demo.service.ModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/model")
public class ModelController {

	private static final Logger logger = LoggerFactory.getLogger(ModelController.class);
	
	@Autowired
	private ModelService modelService;
	
	@Autowired
	private DBFileStorageService dbFileService;
	
	@GetMapping(path="/all")
	ResponseEntity<List<Model>> getModels() {
		List<Model> models = modelService.getAllModels();
		if (models == null) {
			return new ResponseEntity<List<Model>>(HttpStatus.NOT_FOUND);
		}
		logger.info("Get Models with total : " + models.size());
		return new ResponseEntity<List<Model>>(models, HttpStatus.OK);
	}
	
	@PostMapping(path="/add/{fileid}")
	ResponseEntity<Model> addModel(@RequestBody Model m, @PathVariable String fileid) {
		String fileId = "";
		if (m == null) {
			return new ResponseEntity<Model>(HttpStatus.BAD_REQUEST);
		}
		if (m.getFile() != null) {
			fileId = m.getFile().getId();
		}
		DBFile file = dbFileService.getFileById(fileid);
		m.setFile(file);
		logger.info("Adding Model ..");
		modelService.addModel(m);
		if (fileId != "") {
			dbFileService.removeFile(fileId);
		}
		return new ResponseEntity<Model>(m, HttpStatus.OK);
	}
	
	@DeleteMapping(path="remove/{id}")  
	ResponseEntity<String> removeModelById(@PathVariable String id) {
		 modelService.removeModelById(id);
		 logger.info("Deleted model : " + id);
		 return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}
}

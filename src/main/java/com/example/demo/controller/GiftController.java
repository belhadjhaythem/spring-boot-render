package com.example.demo.controller;

import com.example.demo.model.DBFile;
import com.example.demo.model.Gift;
import com.example.demo.service.DBFileStorageService;
import com.example.demo.service.GiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/gift")
public class GiftController {

private static final Logger logger = LoggerFactory.getLogger(GiftController.class);
	
	@Autowired
	private GiftService giftService;
	
	@Autowired
	private DBFileStorageService dbFileService;
	
	@GetMapping(path="/all")
	ResponseEntity<List<Gift>> getModels() {
		List<Gift> models = giftService.getAllGifts();
		if (models == null) {
			return new ResponseEntity<List<Gift>>(HttpStatus.NOT_FOUND);
		}
		logger.info("Get Gifts with total : " + models.size());
		return new ResponseEntity<List<Gift>>(models, HttpStatus.OK);
	}
	
	@PostMapping(path="/add/{fileid}")
	ResponseEntity<Gift> addModel(@RequestBody Gift m, @PathVariable String fileid) {
		String fileId = "";
		if (m == null) {
			return new ResponseEntity<Gift>(HttpStatus.BAD_REQUEST);
		}
		if (m.getFile() != null) {
			fileId = m.getFile().getId();
		}
		DBFile file = dbFileService.getFileById(fileid);
		m.setFile(file);
		logger.info("Adding Gift ..");
		giftService.addGift(m);
		if (fileId != "") {
			dbFileService.removeFile(fileId);
		}
		return new ResponseEntity<Gift>(m, HttpStatus.OK);
	}
	
	@DeleteMapping(path="remove/{id}")  
	ResponseEntity<String> removeModelById(@PathVariable String id) {
		giftService.removeGiftById(id);
		 logger.info("Deleted gift : " + id);
		 return new ResponseEntity<String>("Successfully deleted gift", HttpStatus.OK);
	}
	
	@DeleteMapping(path="removeMultiple")  
	void removeModels(@RequestBody List<Gift> gifts) {
		giftService.removeGifts(gifts);
		 logger.info("Deleted gifts : " + gifts.size());
	}
}

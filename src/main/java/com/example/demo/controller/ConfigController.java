package com.example.demo.controller;

import com.example.demo.model.Config;
import com.example.demo.model.DBFile;
import com.example.demo.model.Platform;
import com.example.demo.service.ConfigService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/config")
public class ConfigController {

	final static Logger logger = Logger.getLogger(ConfigController.class);
	
	@Autowired
	private ConfigService configService;
	
	@GetMapping(path="/all") 
	ResponseEntity<List<Config>> getConfig() {
		List<Config> configs = configService.getAll();
		if (configs == null) {
			return new ResponseEntity<List<Config>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get configs with total : " + configs.size());
		return new ResponseEntity<List<Config>>(configs, HttpStatus.OK);
	}
	
	@GetMapping(path="/all/{type}") 
	ResponseEntity<List<Config>> getConfigByType(@PathVariable String type) {
		List<Config> configs = configService.getConfigBytype(type);
		if (configs == null) {
			return new ResponseEntity<List<Config>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get configs " + type + " with total : " + configs.size());
		return new ResponseEntity<List<Config>>(configs, HttpStatus.OK);
	}
	
	@PostMapping(path="/add")
	ResponseEntity<String> addConfig(@RequestBody Config a) {
		if (a == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Adding Config ..");
		configService.addConfig(a);
		return new ResponseEntity<String>("Successfully added", HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	ResponseEntity<String> removeClient(@RequestBody Config a) {
		configService.removeConfig(a);
		 logger.info("Deleted Config : " + a.getId());
		 return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	ResponseEntity<String> removeClient(@PathVariable String id) {
		configService.removeConfigById(id);
		 logger.info("Deleted Config : " + id);
		 return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
	}
	
	@DeleteMapping(path="removeMultiple")  
	void removeModels(@RequestBody List<Config> configs) {
		configService.removeAll(configs);
		 logger.info("Deleted configs : " + configs.size());
	}

	@GetMapping(path="/platform/all")
	ResponseEntity<List<Platform>> getPlatforms() {
		List<Platform> configs = configService.getAllPlatforms();
		if (configs == null) {
			return new ResponseEntity<List<Platform>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get platforms with total : " + configs.size());
		return new ResponseEntity<List<Platform>>(configs, HttpStatus.OK);
	}

	@GetMapping(path="/platform/getByCode/{clientCode}")
	ResponseEntity<Platform> getPlatformByCode(@PathVariable String clientCode) {
		logger.info(clientCode);
		Platform platform = configService.getPlatformByCode(clientCode);
		if (platform == null) {
			return new ResponseEntity<Platform>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get platform with code : " + platform.getClientCode());
		return new ResponseEntity<Platform>(platform, HttpStatus.OK);
	}

	@GetMapping(path="/platform/getVideoByCode/{clientCode}")
	ResponseEntity<DBFile> getVideoByCode(@PathVariable String clientCode) {
		logger.info(clientCode);
		DBFile video = configService.getVideoPlatform(clientCode);
		if (video == null) {
			return new ResponseEntity<DBFile>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get platform with code : " + video.getFileName());
		return new ResponseEntity<DBFile>(video, HttpStatus.OK);
	}

	@PostMapping(path="/platform/add")
	ResponseEntity<String> addPlatform(@RequestBody Platform a) {
		if (a == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Adding platform ..");
		configService.addPlatform(a);
		return new ResponseEntity<String>("Successfully added", HttpStatus.OK);
	}

	@DeleteMapping(path="/platform/delete")
	void removePlatforms(@RequestBody List<Platform> configs) {
		configService.removePlatform(configs);
		logger.info("Deleted platforms : " + configs.size());
	}
}

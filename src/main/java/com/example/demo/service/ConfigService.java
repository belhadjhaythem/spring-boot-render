package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Config;
import com.example.demo.model.DBFile;
import com.example.demo.model.Platform;
import com.example.demo.repository.ConfigRepository;
import com.example.demo.repository.DBFileRepository;
import com.example.demo.repository.PlatformRepository;

import java.util.List;

@Service
public class ConfigService implements IConfigService{

	@Autowired
	private ConfigRepository configRepository;
	@Autowired
	private PlatformRepository platformRepository;
	@Autowired
	private DBFileRepository dbFileRepository;
	
	@Override
	public List<Config> getConfigBytype(String type) {
		return configRepository.findConfigByType(type);
	}

	@Override
	public void addConfig(Config a) {
		configRepository.save(a);
	}

	@Override
	public void removeConfig(Config a) {
		configRepository.delete(a);
	}

	@Override
	public void removeConfigById(String id) {
		configRepository.deleteById(id);
	}

	@Override
	public List<Config> getAll() {
		return configRepository.findAll();
	}

	@Override
	public List<Platform> getAllPlatforms() {
		return platformRepository.findAll();
	}

	@Override
	public Platform getPlatformByCode(String clientCode) {
		return platformRepository.getPlatformByCode(clientCode);
	}

	@Override
	public DBFile getVideoPlatform(String clientCode) {
		return dbFileRepository.findFileByPartner(clientCode);
	}

	@Override
	public void addPlatform(Platform a) {
		platformRepository.save(a);
	}

	@Override
	public void removeAll(List<Config> a) {
		configRepository.deleteAll(a);
	}

	@Override
	public void removePlatform(List<Platform> a) {
		for(Platform platform : a) {
			platformRepository.delete(platform);
		}
	}

}

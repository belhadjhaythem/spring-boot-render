package com.example.demo.service;

import com.example.demo.model.Config;
import com.example.demo.model.DBFile;
import com.example.demo.model.Platform;

import java.util.List;


public interface IConfigService {

	List<Config> getAll();

	List<Platform> getAllPlatforms();
	List<Config> getConfigBytype(String type);
	void addConfig(Config a);

	Platform getPlatformByCode(String clientCode);

	DBFile getVideoPlatform(String clientCode);

	void addPlatform(Platform a);
	void removeConfig(Config a);
	void removeConfigById(String a);
	void removeAll(List<Config> a);

	void removePlatform(List<Platform> a);
}

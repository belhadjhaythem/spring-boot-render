package com.example.demo.service;

import com.example.demo.model.Architect;
import com.example.demo.model.User;

public interface IUserService {

	User getUserByUsernameOrEmail(String input);
	
	Architect getArchitectByUsername(String input);
	
	void updatePassword(String password);
}

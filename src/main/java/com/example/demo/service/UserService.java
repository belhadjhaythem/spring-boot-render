package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Architect;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserByUsernameOrEmail(String input) {
		return userRepository.findUserByEmailOrUsername(input);
	}

	@Override
	public Architect getArchitectByUsername(String input) {
		return userRepository.findArchitectByEmailOrUsername(input);
	}

	@Override
	public void updatePassword(String password) {
		userRepository.updatePassword(password);
	}

}

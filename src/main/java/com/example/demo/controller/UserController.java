package com.example.demo.controller;

import com.example.demo.model.Architect;
import com.example.demo.model.Connexion;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
public class UserController {

	final static Logger logger = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("/test")
	public ResponseEntity<String> index() {
		return new ResponseEntity<String>("Greeting from intellekt", HttpStatus.OK);
	}

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestBody User user) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		logger.info("test add ");
		return "Saved";
	}

	@PostMapping(path = "/login")
	ResponseEntity<User> login(@RequestBody Connexion user) {
		User connectedUser = new User();
		connectedUser = userService.getUserByUsernameOrEmail(user.getUsername());
		logger.info(connectedUser);
		if (connectedUser != null && (!connectedUser.getDecriminatorValue().equals("ADMIN") || !"Actif".equalsIgnoreCase(connectedUser.getStatus()))) {
			return new ResponseEntity<User>(connectedUser, HttpStatus.FORBIDDEN);
		}
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

		if (connectedUser != null && connectedUser.getPassword().equals(password)) {
			logger.info("User service : Login success");
			return new ResponseEntity<User>(connectedUser, HttpStatus.OK);
		}
		logger.error("User service : Bad credentials");
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

	}
	
	@PostMapping(path = "/login/architect")
	ResponseEntity<Architect> loginArchitect(@RequestBody Connexion user) {
		Architect connectedUser = new Architect();
		connectedUser = userService.getArchitectByUsername(user.getUsername());
		logger.info(connectedUser);
		if (connectedUser != null && (!connectedUser.getDecriminatorValue().equals("ARCHITECT") || !"Actif".equalsIgnoreCase(connectedUser.getStatus()))) {
			Architect res = new Architect();
			res.setEmail("INACTIF");
			return new ResponseEntity<Architect>(res, HttpStatus.FORBIDDEN);
		}
		String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

		if (connectedUser != null && connectedUser.getPassword().equals(password)) {
			logger.info("User service : Login success");
			return new ResponseEntity<Architect>(connectedUser, HttpStatus.OK);
		}
		logger.error("User service : Bad credentials");
		return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);

	}
	
	@PostMapping(path = "/password/update")
	ResponseEntity<String> updatePassword(@RequestBody Connexion user) {
		try {
			userService.updatePassword(user.getPassword());
			return new ResponseEntity<String>("ok", HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Error updating password : " + e.getMessage());
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		
	}

}
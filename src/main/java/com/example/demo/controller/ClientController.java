package com.example.demo.controller;

import com.example.demo.model.Architect;
import com.example.demo.model.Client;
import com.example.demo.service.ArchitectService;
import com.example.demo.service.ClientService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/client")
public class ClientController {

	final static Logger logger = Logger.getLogger(ClientController.class);
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private ArchitectService architectService;
	
	@GetMapping(path="/all") 
	ResponseEntity<List<Client>> getClients() {
		List<Client> Clients = clientService.getAllClients();
		if (Clients == null) {
			return new ResponseEntity<List<Client>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get Clients with total : " + Clients.size());
		return new ResponseEntity<List<Client>>(Clients, HttpStatus.OK);
	}
	
	@GetMapping(path="/architect/{id}") 
	ResponseEntity<Architect> getArchitect(@PathVariable String id) {
		Architect architect = clientService.getArchitectFromClient(id);
		if (architect == null) {
			return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get Clients with total : " + architect.getId());
		return new ResponseEntity<Architect>(architect, HttpStatus.OK);
	}

	@GetMapping(path="/architect/get/{id}")
	ResponseEntity<List<Client>> getClientbyArchitect(@PathVariable int id) {
		List<Client> clients = clientService.getClientsByArchitect(id);
		if (clients == null) {
			return new ResponseEntity<List<Client>>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get clients for architect with id : " + id);
		return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
	}

	@GetMapping(path="/get/{id}")
	ResponseEntity<Client> getClientById(@PathVariable int id) {
		Client client = clientService.findOneClient(id);
		if (client == null) {
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Get clients with id : " + id);
		return new ResponseEntity<Client>(client, HttpStatus.OK);
	}

	@PostMapping(path="/sendConfirmation")
	ResponseEntity<Client> sendConfirmation(@RequestBody Client a) {
		if (a == null) {
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		}
		logger.info("sending Client confirmation code in email..");
		clientService.sendCodeInEmail(a);
		return new ResponseEntity<Client>(a, HttpStatus.OK);
	}
	
	@PostMapping(path="/add")
	ResponseEntity<Client> addClient(@RequestBody Client a) {
		if (a == null) {
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Adding Client ..");
		clientService.addClient(a);
		return new ResponseEntity<Client>(a, HttpStatus.OK);
	}

	@PostMapping(path="/update")
	ResponseEntity<String> updateClient(@RequestBody Client a) {
		if (a == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Updating Client ..");
		clientService.updateClient(a);
		return new ResponseEntity<String>("Successfully added", HttpStatus.OK);
	}

	@PostMapping(path="/loyalty")
	ResponseEntity<String> generateLoyaltyCode(@RequestBody Client a) {
		if (a == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Generating loyalty code for Client ..");
		String response = clientService.generateLoyaltyCode(a);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/remove")
	ResponseEntity<Client> removeClient(@RequestBody Client Client) {
		 clientService.removeClient(Client);
		 logger.info("Deleted Client : " + Client.getId());
		 return new ResponseEntity<Client>(new Client(), HttpStatus.OK);
	}
	
	@DeleteMapping("/multiple/remove")
	void removeClients(@RequestBody List<Client> Clients) {
		for (int i = 0 ; i < Clients.size() ; i++) {
			clientService.removeClient(Clients.get(i));
		}
		logger.info("Deleted " + Clients.size() + " Clients");
	}
}
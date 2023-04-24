package com.example.demo.service;

import com.example.demo.model.Architect;
import com.example.demo.model.Client;

import java.util.List;


public interface IClientService {

	List<Client> getAllClients();
	void addClient(Client a);
	void updateClient(Client a);
	void removeClient(Client a);
	void removeClientById(Client a);
	Architect getArchitectFromClient(String id);
	String generateLoyaltyCode(Client c);
	List<Client> getClientsByArchitect(int id);
	Client findOneClient(int id);
	int findArchitectByClient(String id);
	int sendCodeInEmail(Client c);
}

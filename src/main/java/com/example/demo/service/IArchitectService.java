package com.example.demo.service;

import com.example.demo.enumeration.PurchaseStatus;
import com.example.demo.model.Achat;
import com.example.demo.model.Architect;
import com.example.demo.model.Gift;

import java.util.List;

public interface IArchitectService {

	Architect getById(int id);
	List<Architect> getAllArchitects();
	Architect addArchitect(Architect a);
	void removeArchitect(Architect a);
	void removeArchitectById(Architect a);
	//void removeMultiple(List<Architect> list);
	int updatePurchase(Architect a);
	int purchaseArchitect(int id, Gift a);
	void updateAchatStatus(Achat a);
	List<Achat> getAchats();
	List<PurchaseStatus> getPurchaseStatus();

	Architect addInscriptionArchitect(Architect a);

	Architect resetPassword(String email);
}

package com.example.demo.service;

import com.example.demo.enumeration.PurchaseStatus;
import com.example.demo.model.Achat;
import com.example.demo.model.Architect;
import com.example.demo.model.Config;
import com.example.demo.model.Gift;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArchitectService implements IArchitectService {

	private static final Logger logger = LoggerFactory.getLogger(ArchitectService.class);

	@Autowired
	private ArchitectRepository architectRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ConfigRepository configRepository;

	@Autowired
	private AchatRepository achatRepository;

	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserRepository userRepository;


	//@Value("${default.architect.id}")
	private String defaultIdArchitect;

	@Override
	public List<Architect> getAllArchitects() {
		Iterable<Architect> iterableArchites = architectRepository.findAll();
		return StreamSupport.stream(iterableArchites.spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public Architect addArchitect(Architect a) {
		return architectRepository.save(a);
	}



	@Override
	public Architect addInscriptionArchitect(Architect a) {
		Architect isUsername = userRepository.findArchitectByEmailOrUsername(a.getUsername());
		Architect isEmail = userRepository.findArchitectByEmailOrUsername(a.getEmail());
		a.setStatus("INACTIF");
		if (isUsername != null || isEmail != null) {
			Architect result = new Architect();
			result.setEmail("EXIST");
			return result;
		}
		a.setCin(genrateRandom()+"");
		emailService.sendArchitectConfirmation("Code confirmation inscription Architec", a.getEmail(), a.getCin(), a.getFirstName(), a.getLastName());
		return architectRepository.save(a);
	}

	@Override
	public Architect resetPassword(String email) {
		Architect a = userRepository.findArchitectByEmailOrUsername(email);
		if (a != null) {
			a.setCin(genrateRandom()+"");
			emailService.sendPasswordRenew("Réinitialisation mot de passe Architec", a.getEmail(), a.getCin(), a.getFirstName(), a.getLastName());
			return a;
		}
		return null;
	}

	public int genrateRandom() {
		Random r = new Random( System.currentTimeMillis() );
		return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
	}

	@Override
	public void removeArchitect(Architect a) {
		architectRepository.delete(a);
	}

	@Override
	public void removeArchitectById(Architect a) {
		architectRepository.deleteById(a.getId());
	}

	@Override
	public int updatePurchase(Architect a) {
		List<Config> configs = configRepository.findConfigByType("POINTS");
		int points = 0;
		if (configs != null && configs.size() > 0) {
			points = Integer.parseInt(configs.get(0).getValue());
		}
		logger.info(" Points in database configured : " + points);
		a.setNumberOfPoints(a.getNumberOfPoints() + points);
		architectRepository.save(a);
		return points;
	}

	@Override
	public int purchaseArchitect(int id, Gift a) {
		Architect architect = getById(id);
		Achat achat = new Achat();
		achat.setArchitectId(architect);
		achat.setDate(LocalDateTime.now());
		achat.setGiftId(a);
		achat.setStatus(PurchaseStatus.NOUVEAU);
		achatRepository.save(achat);
		architect.setNumberOfPoints(architect.getNumberOfPoints() - a.getPoints());
		architectRepository.save(architect);
		return architect.getNumberOfPoints();
	}

	@Override
	public void updateAchatStatus(Achat a) {
		achatRepository.save(a);
	}

	@Override
	public List<Achat> getAchats() {
		Iterable<Achat> iterableArchites = achatRepository.findAll();
		return StreamSupport.stream(iterableArchites.spliterator(), false)
				.collect(Collectors.toList());
	}

	@Override
	public List<PurchaseStatus> getPurchaseStatus() {
		return Arrays.asList(PurchaseStatus.values());
	}


	@Override
	public Architect getById(int id) {
		return architectRepository.findById(id).get();
	}

	/* @Override
	public void removeMultiple(List<Architect> list) {
		for(Architect a: list) {
			for(Client c : a.getClients()) {
				c.setArchitect(architectRepository.findById(Integer.parseInt(this.defaultIdArchitect)).get());
				clientRepository.save(c);
			}
			
		}
		
		architectRepository.deleteAll(list);
	}*/

}

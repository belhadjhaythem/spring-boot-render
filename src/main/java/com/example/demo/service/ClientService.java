package com.example.demo.service;

import com.example.demo.model.Architect;
import com.example.demo.model.Client;
import com.example.demo.repository.ArchitectRepository;
import com.example.demo.repository.ClientRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService implements IClientService {

	private final String  TOPIC_DESTINATION = "/topic/sms";
	final static Logger logger = Logger.getLogger(ClientService.class);

	@Autowired
	EmailService emailService;

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ArchitectRepository architectRepository;

	@Autowired
	private SimpMessagingTemplate webSocket;

	@Value("${SMS}")
	private String smsBody;
	
	@Override
	public List<Client> getAllClients() {
		Iterable<Client> iterableClients = clientRepository.findAll();
		return StreamSupport.stream(iterableClients.spliterator(), false)
	    .collect(Collectors.toList());
	}

	@Override
	public Client findOneClient(int id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public int findArchitectByClient(String id) {
		return clientRepository.findArchitectByClient(Integer.parseInt(id));
	}

	@Override
	public int sendCodeInEmail(Client c) {
		return emailService.sendEmailConfirmation("Code promo Architec", c.getEmail(), c.getLoyaltyCode(), c.getFirstName(), c.getLastName());
	}

	@Override
	public String generateLoyaltyCode(Client c) {
		String code = String.valueOf(c.getFirstName().charAt(0)).toUpperCase() + String.valueOf(c.getLastName().charAt(0)).toUpperCase() + "-TIP00" + c.getId();
		c.setLoyaltyCode(code);
		Architect associatedArchitect = architectRepository.findById(clientRepository.findArchitectByClient(c.getId())).get();
		c.setArchitect(associatedArchitect);
		clientRepository.save(c);
		/*SMS sms = new SMS();
		sms.setTo(c.getPhone());
		sms.setMessage(smsBody + " " + code);
		try {
			sendSms(sms);
		} catch (Exception e) {
			String message = "SMS was not sent to " + c.getPhone();
			logger.error(message +"\n" + e.getMessage());
			return message;
		}*/
		emailService.sendEmailConfirmation("Code promo Architec", c.getEmail(), code, c.getFirstName(), c.getLastName());
		return code;
	}

	@Override
	public List<Client> getClientsByArchitect(int id) {
		return clientRepository.findClientsForArchitect(id);
	}

	@Override
	public void addClient(Client a) {
		Architect associatedArchitect = a.getArchitect();
		logger.info(associatedArchitect);
		if (associatedArchitect != null) {
            logger.info("setting points for architect " + a.getId());
			if (a.getId() == 0) {
				associatedArchitect.setNumberOfPoints(a.getArchitect().getNumberOfPoints() + 1);
			}
			architectRepository.save(associatedArchitect);
		}
		clientRepository.save(a);
	}



	@Override
	public void updateClient(Client a) {
		Architect associatedArchitect = architectRepository.findById(clientRepository.findArchitectByClient(a.getId())).get();
		a.setArchitect(associatedArchitect);
		clientRepository.save(a);
	}

	@Override
	public void removeClient(Client a) {
		clientRepository.delete(a);		
	}

	@Override
	public void removeClientById(Client a) {
		clientRepository.deleteById(a.getId());
	}

	@Override
	public Architect getArchitectFromClient(String id) {
		return architectRepository.findById(clientRepository.findArchitectByClient(Integer.parseInt(id))).get();
	}


	/*public Boolean sendSms(SMS sms) {
		try{
			service.send(sms);
		}
		catch(Exception e){
			webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": Error sending the SMS: "+e.getMessage());
			logger.error(e.getMessage());
			return false;
		}
		webSocket.convertAndSend(TOPIC_DESTINATION, getTimeStamp() + ": SMS has been sent!: "+sms.getTo());
		return true;
	}*/

	private String getTimeStamp() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}

}

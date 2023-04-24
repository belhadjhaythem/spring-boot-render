package com.example.demo.controller;

import com.example.demo.enumeration.PurchaseStatus;
import com.example.demo.model.Achat;
import com.example.demo.model.Architect;
import com.example.demo.model.Gift;
import com.example.demo.service.ArchitectService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/architect")
public class ArchitectController {

    final static Logger logger = Logger.getLogger(ArchitectController.class);

    @Autowired
    private ArchitectService architectService;

    @GetMapping(path = "/all")
    ResponseEntity<List<Architect>> getArchitects() {
        List<Architect> architects = architectService.getAllArchitects();
        if (architects == null) {
            return new ResponseEntity<List<Architect>>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Get architects with total : " + architects.size());
        return new ResponseEntity<List<Architect>>(architects, HttpStatus.OK);
    }

    @GetMapping(path = "/achat/all")
    ResponseEntity<List<Achat>> getAchats() {
        List<Achat> achats = architectService.getAchats();
        if (achats == null) {
            return new ResponseEntity<List<Achat>>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Get achats with total : " + achats.size());
        return new ResponseEntity<List<Achat>>(achats, HttpStatus.OK);
    }

    @GetMapping(path = "/achat/status/all")
    ResponseEntity<List<PurchaseStatus>> getStatusAchats() {
        List<PurchaseStatus> achats = architectService.getPurchaseStatus();
        if (achats == null) {
            return new ResponseEntity<List<PurchaseStatus>>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Get purchase status with total : " + achats.size());
        return new ResponseEntity<List<PurchaseStatus>>(achats, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{id}")
    ResponseEntity<Architect> getArchitectById(@PathVariable int id) {
        Architect architect = architectService.getById(id);
        if (architect == null) {
            return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Get architect with id : " + id);
        return new ResponseEntity<Architect>(architect, HttpStatus.OK);
    }

	@GetMapping(path = "/passwordreset/{email}")
	ResponseEntity<Architect> sendPasswordReset(@PathVariable String email) {
		Architect architect = architectService.resetPassword(email);
		if (architect == null) {
			return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);
		}
		logger.info("Send password reset to : " + email);
		return new ResponseEntity<Architect>(architect, HttpStatus.OK);
	}

    @PostMapping(path = "/add")
    ResponseEntity<Architect> addArchitect(@RequestBody Architect a) {
        if (a == null) {
            return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Adding architect ..");
        Architect architect = architectService.addArchitect(a);
        return new ResponseEntity<Architect>(architect, HttpStatus.OK);
    }

    @PostMapping(path = "/add/signup")
    ResponseEntity<Architect> addInscriptionArchitect(@RequestBody Architect a) {
        if (a == null) {
            return new ResponseEntity<Architect>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Adding architect ..");
        Architect architect = architectService.addInscriptionArchitect(a);
        return new ResponseEntity<Architect>(architect, HttpStatus.OK);
    }

    @PostMapping(path = "/purchase")
    ResponseEntity<String> purchase(@RequestBody Architect a) {
        if (a == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Purchase client update architect ..");
        int points = architectService.updatePurchase(a);
        return new ResponseEntity<String>(String.valueOf(points), HttpStatus.OK);
    }

    @PostMapping(path = "/purchaseArchitect/{id}")
    ResponseEntity<String> purchaseArchitect(@PathVariable int id, @RequestBody Gift a) {
        if (a == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Purchase architect ..");
        int points = architectService.purchaseArchitect(id, a);
        return new ResponseEntity<String>(String.valueOf(points), HttpStatus.OK);
    }

    @PostMapping(path = "/updateAchat")
    ResponseEntity<String> updateAchat(@RequestBody Achat a) {
        if (a == null) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Update Achat status ..");
        architectService.updateAchatStatus(a);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    ResponseEntity<String> removeArchitect(@RequestBody Architect architect) {
        architectService.removeArchitect(architect);
        logger.info("Deleted architect : " + architect.getId());
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }


	
	/*@DeleteMapping("/multiple/remove")
	void removeArchitects(@RequestBody List<Architect> architects) {
		architectService.removeMultiple(architects);
		logger.info("Deleted " + architects.size() + " architects");
	}*/
}

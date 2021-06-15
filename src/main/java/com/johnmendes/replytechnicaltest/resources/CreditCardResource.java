package com.johnmendes.replytechnicaltest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.johnmendes.replytechnicaltest.entity.CreditCard;
import com.johnmendes.replytechnicaltest.service.CreditCardService;

@RestController
@RequestMapping("/payments")
public class CreditCardResource {

	@Autowired
	private CreditCardService creditCardService;
	
	@RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<List<CreditCard>> List(){
		return ResponseEntity.status(HttpStatus.OK).body(creditCardService.list());
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody CreditCard creditCard) {		
		if(validateCreditCard(creditCard.getCreditCard())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
			
		creditCard = creditCardService.save(creditCard);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	private boolean validateCreditCard(String creditCard) {
		if(creditCard.length() != 16) {
			return true;
		}
		return false;
	}
}

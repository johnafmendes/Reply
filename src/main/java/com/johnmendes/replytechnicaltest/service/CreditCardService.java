package com.johnmendes.replytechnicaltest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnmendes.replytechnicaltest.entity.CreditCard;
import com.johnmendes.replytechnicaltest.entity.User;
import com.johnmendes.replytechnicaltest.exceptions.CreditCardExistException;
import com.johnmendes.replytechnicaltest.repository.CreditCardRepository;
import com.johnmendes.replytechnicaltest.repository.UserRepository;

@Service
public class CreditCardService {

	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<CreditCard> list(){
		return creditCardRepository.findAll();
	}
	
	public CreditCard save(CreditCard creditCard) {
		if(creditCard.getCreditCard() != null){
			User resultCreditCard = userRepository.findByCreditCard(creditCard.getCreditCard());
			
			if(resultCreditCard != null){
				throw new CreditCardExistException("Credit Card already exist.");
			}
		}
		return creditCardRepository.save(creditCard);
	}
}

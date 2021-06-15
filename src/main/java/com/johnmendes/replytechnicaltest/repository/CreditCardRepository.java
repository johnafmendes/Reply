package com.johnmendes.replytechnicaltest.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.SessionScope;

import com.johnmendes.replytechnicaltest.entity.CreditCard;

@SessionScope
@Repository
public class CreditCardRepository {

	private List<CreditCard> creditCardList;
	
	public CreditCardRepository() {
		creditCardList = new ArrayList<CreditCard>();
	}
	
	public CreditCard save(CreditCard creditCard) {
		creditCardList.add(creditCard);
		return creditCard;
	}
	
	public List<CreditCard> findAll(){
		return creditCardList;
	}
}

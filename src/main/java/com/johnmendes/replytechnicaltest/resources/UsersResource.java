package com.johnmendes.replytechnicaltest.resources;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.johnmendes.replytechnicaltest.entity.User;
import com.johnmendes.replytechnicaltest.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersResource {

	@Autowired
	private UsersService usersService;
	
	@RequestMapping(method = RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<MappingJacksonValue> List(){
		SimpleBeanPropertyFilter simpleBeanPropertyFilter =
                SimpleBeanPropertyFilter.serializeAllExcept("amount");

        FilterProvider filterProvider = new SimpleFilterProvider()
                .addFilter("userFilter", simpleBeanPropertyFilter);
        
        List<User> listUser = usersService.list();
        MappingJacksonValue listUserWithFilter = new MappingJacksonValue(listUser);
        listUserWithFilter.setFilters(filterProvider);
        
		return ResponseEntity.status(HttpStatus.OK).body(listUserWithFilter);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody User user){
		if(validateUserName(user.getUserName())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if(validateDoB(user.getDob())) {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
		
		if(validateCreditCard(user.getCreditCard().getCreditCard())) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		user = usersService.save(user);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	


	@RequestMapping(value = "/creditcard/{CreditCard}", method = RequestMethod.GET)
	public ResponseEntity<MappingJacksonValue> find(@PathVariable ("CreditCard") String creditCard){		
		if(creditCard.compareTo("yes") == 0 && creditCard != null) {
			SimpleBeanPropertyFilter simpleBeanPropertyFilter =
	                SimpleBeanPropertyFilter.serializeAllExcept("amount");

	        FilterProvider filterProvider = new SimpleFilterProvider()
	                .addFilter("userFilter", simpleBeanPropertyFilter);
	        
	        List<User> listUser = usersService.list();
	        MappingJacksonValue listUserWithFilter = new MappingJacksonValue(listUser);
	        listUserWithFilter.setFilters(filterProvider);
	        
			return ResponseEntity.status(HttpStatus.OK).body(listUserWithFilter);
		} else if(creditCard.compareTo("no") == 0) {
			SimpleBeanPropertyFilter simpleBeanPropertyFilter =
	                SimpleBeanPropertyFilter.serializeAllExcept("creditCard");

	        FilterProvider filterProvider = new SimpleFilterProvider()
	                .addFilter("userFilter", simpleBeanPropertyFilter);
	        
	        List<User> listUser = usersService.list();
	        MappingJacksonValue listUserWithFilter = new MappingJacksonValue(listUser);
	        listUserWithFilter.setFilters(filterProvider);
	        
			return ResponseEntity.status(HttpStatus.OK).body(listUserWithFilter);
		}
		return null;
	}
	
		private boolean validateCreditCard(String creditCard) {
		if(creditCard.length() != 16) {
			return true;
		}
		return false;
	}
		
	private boolean validateDoB(Date dob) {		
		Calendar a = getCalendar(dob);
	    Calendar b = getCalendar(new Date());
	    int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
	    if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || 
	        (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
	        diff--;
	    }
	    if(diff < 18) {
	    	return false;
	    } else {
	    	return true;
	    }
	}
	
	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.ENGLISH);
	    cal.setTime(date);
	    return cal;
	}

	private boolean validateUserName(String userName) {
		return userName.matches("/^[a-zA-Z0-9]{0,40}$/");
		
	}

}

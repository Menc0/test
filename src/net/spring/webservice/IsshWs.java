package net.spring.webservice;

import javax.jws.WebService;

import net.spring.model.User;

@WebService
public interface IsshWs {

	public User getUserByName(String name);
	
}

package net.spring.webservice.impl;

import javax.jws.WebService;


import net.spring.model.User;
import net.spring.service.UserService;
import net.spring.webservice.IsshWs;
@WebService(endpointInterface="net.spring.service.UserService" ,targetNamespace="http://localhost:8081/webService/")
public class SshWsImpl implements IsshWs{

	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userService.getUserByName(name);
	}
	

}

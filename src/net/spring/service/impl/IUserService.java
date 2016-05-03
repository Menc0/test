package net.spring.service.impl;

import java.util.List;

import net.spring.dao.UserDAO;
import net.spring.dao.impl.IUserDAO;
import net.spring.model.User;
import net.spring.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value="userService")
public class IUserService implements UserService{
	
	@Autowired
	private UserDAO userdao ;

	@Override
	public List<User> searchAllUser(int currentpage,int pagesize) {
		// TODO Auto-generated method stub
		return userdao.queryallUser(currentpage,pagesize);
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("userservice");
		userdao.insertUser(user);
		return true;
	}
	

	@Override
	public boolean deleteUser(int id) {
		
		return userdao.deleteUser(id);
	}

	@Override
	public User searchUser(int id) {
		// TODO Auto-generated method stub
		return userdao.searchUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userdao.updateUser(user);
	}

	@Override
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userdao.getUserByName(name);
	}
	
}

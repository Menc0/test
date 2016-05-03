package net.spring.test;

import net.spring.dao.UserDAO;
import net.spring.dao.impl.IUserDAO;
import net.spring.model.User;

public class usertest {
	public static void main(String[] args) {
		UserDAO dao = new IUserDAO();
		User user = new User();
		user.setPassword("123456");
		user.setUsername("zhang");
		dao.insertUser(user);
	}
}

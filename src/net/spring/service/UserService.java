package net.spring.service;

import java.util.List;

import net.spring.model.User;

public interface UserService {
	public List<User> searchAllUser(int currentpage,int pagesize);
	
	public boolean insertUser(User user);
	
	public boolean deleteUser(int id);
	
	public User searchUser(int id);
	
	public boolean updateUser(User user);
	
	public User getUserByName(String name);
	void deleteCacheByName(String name);
	public void removeAllCache();
}

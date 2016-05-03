package net.spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import net.spring.model.User;

public interface UserDAO {
	/**查询所有用户信息**/
	public List<User> queryallUser(int currentpage,int pagesize);
		
	
	
	/**添加用户信息**/
	public void insertUser(User user);
	
	/**查询用信息**/
	public User searchUser(int id);
    /**通过名字查询**/
	public User getUserByName(String name);
	
	/**删除用户信息**/
	public boolean deleteUser(int id);
	
	/**查询用户记录数**/
	public int getUserCount();
	/**修改用户信息**/
	public boolean updateUser(User user);
}

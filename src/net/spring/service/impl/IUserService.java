package net.spring.service.impl;

import java.util.List;

import net.spring.dao.UserDAO;
import net.spring.dao.impl.IUserDAO;
import net.spring.model.User;
import net.spring.service.UserService;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
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
	 //将查询到的数据缓存到myCache中,并使用方法名称加上参数中的name作为缓存的key  
    //通常更新操作只需刷新缓存中的某个值,所以为了准确的清除特定的缓存,故定义了这个唯一的key,从而不会影响其它缓存值  
	@Cacheable(value="myCache", key="'getUserByName'+#name")  
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		return userdao.getUserByName(name);
	}
	
}

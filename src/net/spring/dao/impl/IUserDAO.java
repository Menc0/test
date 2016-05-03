package net.spring.dao.impl;

import java.util.ArrayList;
import java.util.List;

import net.spring.dao.UserDAO;
import net.spring.model.User;
import net.spring.util.HibernateSessionFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class IUserDAO implements UserDAO {
	

	
	public List<User> queryallUser(int currentpage, int pagesize) {
		Session session = HibernateSessionFactory.getSession();
		List<User> userList = new ArrayList<User>();

		Transaction tran = session.beginTransaction();
		String hql = "from User";

		Query query = session.createQuery(hql);

		query.setFirstResult((currentpage - 1) * pagesize);
		query.setMaxResults(pagesize);
		userList = query.list();
		tran.commit();
		HibernateSessionFactory.closeSession(session);
		return userList;
	}

	
	public void insertUser(User user) {
		Session session = HibernateSessionFactory.getSession();
		System.out.println("userdao--->insertuser()");

		Transaction tran = session.beginTransaction();

		session.save(user);

		tran.commit();
		System.out.println("ture1");
		HibernateSessionFactory.closeSession(session);

	}

	
	public User searchUser(int id) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User where id = ?";
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		User user = (User) query.uniqueResult();
		tran.commit();
		HibernateSessionFactory.closeSession(session);
		return user;
	}
	
	public User getUserByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User where username = ?";
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setParameter(0,name);
		User user = (User) query.uniqueResult();
		tran.commit();
		HibernateSessionFactory.closeSession(session);
		return user;
	}

	
	public boolean deleteUser(int id) {
		Session session = HibernateSessionFactory.getSession();
		Transaction tran = session.beginTransaction();
		String hql = "update User set status = 0 where id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, id);
		int i = query.executeUpdate();
		tran.commit();
		HibernateSessionFactory.closeSession(session);
		if (i > 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public int getUserCount() {
		Session session = HibernateSessionFactory.getSession();
		String hql = "from User";
		Transaction tran = session.beginTransaction();
		Query query = session.createQuery(hql);
		tran.commit();
		return query.list().size();
	}

	
	public boolean updateUser(User user) {
		Session session = HibernateSessionFactory.getSession();
		String hql = "update User set username = ?, password = ?,status = ? where id = ?";
		Query query = session.createQuery(hql);
		Transaction tran = session.beginTransaction();
		query.setParameter(0, user.getUsername());
		query.setParameter(1, user.getPassword());
		query.setParameter(2, user.getStatus());
		query.setParameter(3, user.getId());
		int i = query.executeUpdate();
		tran.commit();
		HibernateSessionFactory.closeSession(session);
		if (i>0){
			return true;
		}else{
			return false;
		}
	}

}

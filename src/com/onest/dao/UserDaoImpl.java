package com.onest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onest.bean.Page;
import com.onest.bean.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		User u = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName=? and password=?");
		query.setString(0, user.getUserName());
		query.setString(1,user.getPassword());
		u = (User)query.uniqueResult();
		return u;
	}
	@Override
	public boolean regist(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return true;
	}
	@Override
	public boolean userUpdate(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User u set u.userName=?,u.password=?,u.email=? where u.userId=?");
		query.setString(0, user.getUserName());
		query.setString(1, user.getPassword());
		query.setString(2, user.getEmail());
		query.setInteger(3, user.getUserId());
		int ref = query.executeUpdate();
		if (ref > 0) {
			return true;
		}
		return false;
	}
	@Override
	public List<User> selectAllUser(Page page) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User");
		query.setFirstResult((page.getDpage()-1) * page.getPagecount());
		query.setMaxResults(page.getPagecount());
		List<User> userList = query.list();
		return userList;
	}
	@Override
	public boolean deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from User where userId = ?");
		query.setInteger(0, userId);
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}
	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from User");
		Long count = (Long)query.uniqueResult();
		return count;
	}
	@Override
	public boolean checkUser(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userName=?");
		query.setString(0, userName);
		User user = (User)query.uniqueResult();
		if (user!=null)
			return true;
		return false;
	}
	@Override
	public User selectUser(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userId=?");
		query.setInteger(0, userId);
		User user = (User)query.uniqueResult();
		return user;
	}
	@Override
	public void updateImg(String realPath,User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set imgUrl=? where userId=?");
		query.setString(0, realPath);
		query.setInteger(1, user.getUserId());
		query.executeUpdate();
	}
}

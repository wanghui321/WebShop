package com.onest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Cart;
import com.onest.bean.Page;
import com.onest.bean.User;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(cart);
		return true;
	}
	@Override
	public List<Cart> showCart(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cart where user = ?");
		query.setParameter(0, user);
		List<Cart> cartList = query.list();
		return cartList;
	}
	@Override
	public boolean deleteCart(Integer bookId, User u) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Cart where user=? and bookId=?");
		query.setParameter(0, u);
		query.setInteger(1, bookId);
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}
	@Override
	public Cart selectOneCart(Integer bookId, User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Cart where bookId=? and user=?");
		query.setInteger(0, bookId);
		query.setParameter(1, user);
		Cart cart = (Cart)query.uniqueResult();
		return cart;
	}
	@Override
	public boolean updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Cart set number=?,amount=? where bookId=? and user=?");
		query.setInteger(0, cart.getNumber());
		query.setInteger(1, cart.getAmount());
		query.setInteger(2, cart.getBookId());
		query.setParameter(3, cart.getUser());
		int ref = query.executeUpdate();
		if (ref > 0) 
			return true;
		return false;
	}
	@Override
	public Long getCount(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Cart where user=?");
		query.setParameter(0, user);
		Long totalCount = (Long)query.uniqueResult();
		return totalCount;
	}
	@Override
	public boolean deleteAll(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Cart where user=?");
		query.setParameter(0, user);
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}
	@Override
	public boolean deleSomeCart(User user, Integer[] delete) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		for (int i = 0; i < delete.length; i++) {
			Query query = session.createQuery("delete from Cart where user=?and bookId=?");
			query.setParameter(0, user);
			query.setInteger(1, delete[i]);
			int ref = query.executeUpdate();
			if (ref > 0) {
				if (i == delete.length-1)
					return true;
				continue;
			}else
				return false;
		}
		return false;
	}
	@Override
	public void updateCount(Cart cart) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Cart set number=?,amount=? where bookId=? and user=?");
		query.setInteger(0, cart.getNumber());
		query.setInteger(1, cart.getAmount());
		query.setInteger(2, cart.getBookId());
		query.setParameter(3, cart.getUser());
		query.executeUpdate();
	}

}

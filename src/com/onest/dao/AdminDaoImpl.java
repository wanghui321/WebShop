package com.onest.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Admin;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Admin where adminName=? and password=?");
		query.setString(0, admin.getAdminName());
		query.setString(1,admin.getPassword());
		Admin adm = (Admin)query.uniqueResult();
		return adm;
	}

}

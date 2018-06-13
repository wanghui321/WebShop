package com.onest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.onest.bean.Address;
import com.onest.bean.User;

@Repository
public class AddressDaoImpl implements AddressDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Address> selectAddress(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Address where user=?");
		query.setParameter(0, user);
		List<Address> addressList = query.list();
		return addressList;
	}

	@Override
	public boolean addAddress(Address address) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(address);
		return true;
	}

	@Override
	public Address getAddress(Integer addressId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Address where addressId=?");
		query.setInteger(0, addressId);
		Address address = (Address)query.uniqueResult();
		return address;
	}

	@Override
	public List<Address> selectAddressByUserId(Integer userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Address where userId=?");
		query.setParameter(0, userId);
		List<Address> addressList = query.list();
		return addressList;
	}

	@Override
	public void updateAddress(Address address) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Address set province=?,city=?,area=? where addressId=?");
		query.setString(0,address.getProvince());
		query.setString(1,address.getCity());
		query.setString(2,address.getArea());
		query.setInteger(3,address.getAddressId());
		query.executeUpdate();
	}

	@Override
	public void deleteAddress(Integer addressId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Address where addressId=?");
		query.setInteger(0, addressId);
		query.executeUpdate();
	}

}

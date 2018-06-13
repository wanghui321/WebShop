package com.onest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Address;
import com.onest.bean.User;
import com.onest.dao.AddressDao;

@Service
@Transactional
public class AddressService {
	@Autowired
	private AddressDao addressDao;
	
	public List<Address> selectAddress(User user) {
		List<Address> addressList = addressDao.selectAddress(user);
		return addressList;
	}

	public boolean addAddress(User user, Address address) {
		address.setUser(user);
		boolean flag = addressDao.addAddress(address);
		return flag;
	}

	public List<Address> selectAddressByUserId(Integer userId) {
		List<Address> addressList = addressDao.selectAddressByUserId(userId);
		return addressList;
	}

	public void updateAddress(Address address) {
		addressDao.updateAddress(address);
	}

	public void deleteAddress(Integer addressId) {
		addressDao.deleteAddress(addressId);
		
	}
}

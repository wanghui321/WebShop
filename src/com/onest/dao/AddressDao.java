package com.onest.dao;

import java.util.List;

import com.onest.bean.Address;
import com.onest.bean.User;

public interface AddressDao {
	public List<Address> selectAddress(User user);
	public boolean addAddress(Address address);
	public Address getAddress(Integer addressId);
	public List<Address> selectAddressByUserId(Integer userId);
	public void updateAddress(Address address);
	public void deleteAddress(Integer addressId);
}

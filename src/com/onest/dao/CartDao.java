package com.onest.dao;

import java.util.List;

import com.onest.bean.Cart;
import com.onest.bean.User;

public interface CartDao {
	public boolean addCart(Cart cart);
	public List<Cart> showCart(User user);
	public boolean deleteCart(Integer bookId, User u);
	public Cart selectOneCart(Integer bookId, User user);
	public boolean updateCart(Cart cart);
	public Long getCount(User user);
	public boolean deleteAll(User user);
	public boolean deleSomeCart(User user, Integer[] delete);
	public void updateCount(Cart cart);
}

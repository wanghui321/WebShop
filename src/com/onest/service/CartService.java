package com.onest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Book;
import com.onest.bean.Cart;
import com.onest.bean.User;
import com.onest.dao.BookDao;
import com.onest.dao.CartDao;

@Service
@Transactional
public class CartService {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private BookDao bookDao;
	
	public boolean addCart(Integer bookId,Integer count,User user) {
		Cart cart = null;
		boolean flag = false;
		cart = cartDao.selectOneCart(bookId,user);
		if (cart != null) {
			int totalCount = cart.getNumber() + count;
			cart.setNumber(totalCount);
			cart.setAmount();
			flag = cartDao.updateCart(cart);
		} else {
			Book book = bookDao.selectOneBook(bookId);
			cart = new Cart();
			cart.setUser(user);
			cart.setBookId(bookId);
			cart.setBookName(book.getBookName());
			cart.setPrice(book.getPrice());
			cart.setNumber(count);
			cart.setAmount();
			flag = cartDao.addCart(cart);
		}
		return flag;
	}

	public List<Cart> showCart(User user) {
		List<Cart> cartList = cartDao.showCart(user);
		return cartList;
	}

	public boolean deleteCart(Integer bookId, User u) {
		boolean flag = cartDao.deleteCart(bookId,u);
		return flag;
		
	}

	public boolean deleteAll(User user) {
		boolean flag = cartDao.deleteAll(user);
		return flag;
	}

	public boolean deleteSomeCart(User user, Integer[] delete) {
		boolean flag = cartDao.deleSomeCart(user,delete);
		return false;
	}

	public void updateCount(Cart cart) {
		// TODO Auto-generated method stub
		cartDao.updateCount(cart);
	}
}

package com.onest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Address;
import com.onest.bean.Book;
import com.onest.bean.Order;
import com.onest.bean.OrderDetail;
import com.onest.bean.Orders;
import com.onest.bean.Page;
import com.onest.bean.User;
import com.onest.dao.AddressDao;
import com.onest.dao.BookDao;
import com.onest.dao.CartDao;
import com.onest.dao.OrderDao;

@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private CartDao cartDao;

	public boolean addOrders(User user, String time, Integer bookId, Integer count,Integer addressId) {
		Address address = addressDao.getAddress(addressId);
		Book book = bookDao.selectOneBook(bookId);
		//添加到Orders表中
		Orders orders = new Orders();
		orders.setUser(user);
		orders.setOrderTime(time);
		orders.setState("未删除");
		orders.setPayState("未支付");
		orders.setSignState("未签收");
		orders.setSendState("未发货");
		boolean flag1 = orderDao.addOrders(orders);
		//添加到OrderDetail表中
		if (flag1) {
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBookId(bookId);
			orderDetail.setNumber(count);
			orderDetail.setBookName(book.getBookName());
			orderDetail.setPrice(book.getPrice());
			orderDetail.setAmount();
			orderDetail.setOrders(orders);
			orderDetail.setAddress(address);
			boolean flag2 = orderDao.addOrderDetail(orderDetail);
			//从cart表中删除该记录
			if (flag2) {
				boolean flag3 = cartDao.deleteCart(bookId, user);
				if (flag3)
					return true;
			}
		}
		return false;
	}

	public List<Order> checkOrder(User user,Page page) {
		List<Order> orderList = new ArrayList<Order>();
		List<Orders> ordersList = orderDao.getOrders(user,page);
		for (Orders o : ordersList) {
			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setOrderId(o.getOrderId());
			order.setOrderTime(o.getOrderTime());
			order.setState(o.getState());
			order.setPayState(o.getPayState());
			order.setSignState(o.getSignState());
			order.setSendState(o.getSendState());
			OrderDetail detail = orderDao.getOrderDetail(o);
			order.setBookId(detail.getBookId());
			order.setCount(detail.getNumber());
			order.setBookName(detail.getBookName());
			order.setPrice(detail.getPrice());
			order.setAmount();
			Address address = addressDao.getAddress(detail.getAddress().getAddressId());
			order.setAddress(address);
			orderList.add(order);
		}
		return orderList;
	}

	public List<Order> checkAllOrder(Page page) {
		List<Order> orderList = new ArrayList<Order>();
		List<Orders> ordersList = orderDao.getAllOrders(page);
		for (Orders o : ordersList) {
			Order order = new Order();
			order.setUserId(o.getUser().getUserId());
			order.setOrderId(o.getOrderId());
			order.setOrderTime(o.getOrderTime());
			order.setState(o.getState());
			order.setPayState(o.getPayState());
			order.setSignState(o.getSignState());
			order.setSendState(o.getSendState());
			OrderDetail detail = orderDao.getOrderDetail(o);
			order.setBookId(detail.getBookId());
			order.setCount(detail.getNumber());
			order.setBookName(detail.getBookName());
			order.setPrice(detail.getPrice());
			order.setAmount();
			order.setAddress(detail.getAddress());
			orderList.add(order);
		}
		return orderList;
	}

	public Long getCount() {
		Long count = orderDao.getCount();
		return count;
	}

	public Long getMyCount(User user) {
		Long count = orderDao.getMyCount(user);
		return count;
	}

	public void deleteOrder(Integer orderId) {
		orderDao.deleteOrder(orderId);
		
	}

	public void updateOrder(Integer orderId) {
		orderDao.updateOrder(orderId);
		
	}

	public List<Order> checkOrderByUser(User user) {
		List<Order> orderList = new ArrayList<Order>();
		List<Orders> ordersList = orderDao.getOrdersByUser(user);
		for (Orders o : ordersList) {
			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setOrderId(o.getOrderId());
			order.setOrderTime(o.getOrderTime());
			order.setState(o.getState());
			order.setSendState(o.getSendState());
			order.setPayState(o.getPayState());
			order.setSignState(o.getSignState());
			OrderDetail detail = orderDao.getOrderDetail(o);
			Book book = bookDao.selectOneBook(detail.getBookId());
			order.setBookId(detail.getBookId());
			order.setCount(detail.getNumber());
			order.setBookName(book.getBookName());
			order.setPrice(book.getPrice());
			order.setAmount();
			Address address = addressDao.getAddress(detail.getAddress().getAddressId());
			order.setAddress(address);
			orderList.add(order);
		}
		return orderList;
	}

	public boolean payForOrder(Integer orderId) {
		// TODO Auto-generated method stub
		boolean flag = orderDao.payForOrder(orderId);
		return flag;
	}

	public void sendOrder(Integer orderId) {
		// TODO Auto-generated method stub
		orderDao.sendOrder(orderId);
	}

	public void signForOrder(Integer orderId) {
		// TODO Auto-generated method stub
		orderDao.signForOrder(orderId);
	}
}

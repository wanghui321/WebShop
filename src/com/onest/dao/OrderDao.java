package com.onest.dao;

import java.util.List;

import com.onest.bean.OrderDetail;
import com.onest.bean.Orders;
import com.onest.bean.Page;
import com.onest.bean.User;

public interface OrderDao {

	public boolean addOrders(Orders orders);
	public boolean addOrderDetail(OrderDetail orderDetail);
	public List<Orders> getOrders(User user,Page page);
	public OrderDetail getOrderDetail(Orders order);
	public List<Orders> getAllOrders(Page page);
	public Long getCount();
	public Long getMyCount(User user);
	public void deleteOrder(Integer orderId);
	public void updateOrder(Integer orderId);
	public List<Orders> getOrdersByUser(User user);
	public boolean payForOrder(Integer orderId);
	public void sendOrder(Integer orderId);
	public void signForOrder(Integer orderId);
	
}

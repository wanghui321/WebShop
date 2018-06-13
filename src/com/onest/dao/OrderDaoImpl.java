
package com.onest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.onest.bean.OrderDetail;
import com.onest.bean.Orders;
import com.onest.bean.Page;
import com.onest.bean.User;

@Repository
@Service
public class OrderDaoImpl implements OrderDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addOrders(Orders orders) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(orders);
		return true;
	}

	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(orderDetail);
		return true;
	}

	@Override
	public List<Orders> getOrders(User user,Page page) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Orders where user=? and state=?");
		query.setFirstResult((page.getDpage()-1) * page.getPagecount());
		query.setMaxResults(page.getPagecount());
		query.setParameter(0, user);
		query.setString(1, "未删除");
		List<Orders> list = query.list();
		return list;
	}

	@Override
	public OrderDetail getOrderDetail(Orders order) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from OrderDetail where orders=?");
		query.setParameter(0, order);
		OrderDetail orderDetail = (OrderDetail)query.uniqueResult();
		return orderDetail;
	}

	@Override
	public List<Orders> getAllOrders(Page page) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Orders order by userId asc");
		query.setFirstResult((page.getDpage()-1) * page.getPagecount());
		query.setMaxResults(page.getPagecount());
		List<Orders> list = query.list();
		return list;
	}

	@Override
	public Long getCount() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Orders");
		Long count = (Long)query.uniqueResult();
		return count;
	}

	@Override
	public Long getMyCount(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Orders where user=? and state=?");
		query.setParameter(0, user);
		query.setString(1, "未删除");
		Long count = (Long)query.uniqueResult();
		return count;
	}

	@Override
	public void deleteOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Orders where orderId=?");
		query.setInteger(0, orderId);
		query.executeUpdate();
	}

	@Override
	public void updateOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Orders set state=? where orderId=?");
		query.setString(0, "已删除");
		query.setInteger(1, orderId);
		query.executeUpdate();
	}

	@Override
	public List<Orders> getOrdersByUser(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Orders where user=?");
		query.setParameter(0, user);
		List<Orders> list = query.list();
		return list;
	}

	@Override
	public boolean payForOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Orders set payState=? where orderId=?");
		query.setString(0, "已支付");
		query.setInteger(1,orderId);
		int ref = query.executeUpdate();
		if (ref > 0)
			return true;
		return false;
	}

	@Override
	public void sendOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Orders set sendState=? where orderId=?");
		query.setString(0, "已发货");
		query.setInteger(1,orderId);
		query.executeUpdate();
	}

	@Override
	public void signForOrder(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Orders set signState=? where orderId=?");
		query.setString(0, "已签收");
		query.setInteger(1,orderId);
		query.executeUpdate();
	}
}

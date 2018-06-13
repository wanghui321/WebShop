package com.onest.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onest.bean.Order;
import com.onest.bean.Page;
import com.onest.bean.User;
import com.onest.page.Paging;
import com.onest.service.OrderService;
import com.onest.service.UserService;

@Controller
public class OrderAction {
	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	@RequestMapping("/submitOrder")
	public String addOrders(Model model,HttpSession session,Integer addressId) {
		User user = (User)session.getAttribute("user");
		Integer bookId = (Integer) session.getAttribute("bookId");
		Integer count = (Integer) session.getAttribute("count");
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		boolean flag = orderService.addOrders(user,time,bookId,count,addressId);
		if (flag)
			return "redirect:/checkMyOrder.do"; 
		else {
			model.addAttribute("errorMsg","提交订单失败");
			return "redirect:/showCart.do";
		}
	}
	
	@RequestMapping("/checkMyOrder")
	public String checkOrder(HttpSession session,Model model,Integer pageS) {
		User user  = (User)session.getAttribute("user");
		Long totalCount = orderService.getMyCount(user);
		Paging paging = new Paging();
		Page page = paging.checkByPage(totalCount, pageS);
		List<Order> orderList= orderService.checkOrder(user,page);
		model.addAttribute("page",page);
		model.addAttribute("orderList",orderList);
		return "checkMyOrder";
	}
	
	@RequestMapping("/checkAllOrder")
	public String checkAllOrder(Model model,Integer pageS) {
		Long totalCount = orderService.getCount();
		Paging paging = new Paging();
		Page page = paging.checkByPage(totalCount, pageS);
		List<Order> orderList = orderService.checkAllOrder(page);
		model.addAttribute("page",page);
		model.addAttribute("orderList",orderList);
		return "checkAllOrder";
	}
	
	@RequestMapping("/deleteOrder")
	public String deleteOrder(Integer orderId) {
		orderService.deleteOrder(orderId);
		return "redirect:/checkMyOrder.do";
	}
	
	@RequestMapping("/updateOrder")
	public String updateOrder(Integer orderId) {
		orderService.updateOrder(orderId);
		return "redirect:/checkMyOrder.do";
	}
	
	@RequestMapping("/selectSomeOrder")
	public String selectSomeOrder(Integer userId,Model model,Integer pageS) {
		User user = userService.selectUser(userId);
		List<Order> orderList= orderService.checkOrderByUser(user);
		model.addAttribute("orderList",orderList);
		return "checkSomeOrder";
	}
	
	@RequestMapping("/deleteOneOrder")
	public String deleteOneOrder(Integer orderId) {
		orderService.deleteOrder(orderId);
		return "redirect:/checkAllOrder.do";
	}
	
	@RequestMapping("/payForOrder")
	public String payForOrder(Integer orderId,Model model) {
		boolean flag = orderService.payForOrder(orderId);
		if (flag)
			return "redirect:/checkMyOrder.do";
		model.addAttribute("errorMsg","支付失败");
		return "payForOrder";
	}
	
	@RequestMapping("/sendOrder")
	public String sendOrder(Integer orderId) {
		orderService.sendOrder(orderId);
		return "redirect:/checkAllOrder.do";
	}
	
	@RequestMapping("signForOrder")
	public String signForOrder(Integer orderId) {
		orderService.signForOrder(orderId);
		return "redirect:/checkMyOrder.do";
	}
}

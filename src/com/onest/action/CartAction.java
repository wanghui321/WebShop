package com.onest.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onest.bean.Cart;
import com.onest.bean.User;
import com.onest.service.CartService;

@Controller
public class CartAction {
	@Autowired
	private CartService cartService;
	
	@RequestMapping("/judgeUser")
	public String judgeUser(HttpSession session,Model model,Integer bookId) {
		User u = (User)session.getAttribute("user");
		if (u != null) {
			model.addAttribute("bookId",bookId);
			return "getCount";
		}
		return "userLogin";
	}
	
	@RequestMapping("/addCart")
	public String addCart(HttpSession session,Integer bookId,Integer count,Model model) {
		if (count<=0) {
			model.addAttribute("errorMsg","请输入大于零的数值");
			model.addAttribute("bookId",bookId);
			return "getCount";
		}
		User u = (User)session.getAttribute("user");
		boolean flag = cartService.addCart(bookId,count,u);
		if (flag)
			return "redirect:/showCart.do";
		return "redirect:/selectOneBook.do";
	}
	
	@RequestMapping("/showCart")
	public String showCart(HttpSession session,Model model) {
		User u = (User)session.getAttribute("user");
		List<Cart> cartList = cartService.showCart(u);
		int total = 0;
		for (Cart cart : cartList) {
			total += cart.getAmount();
		}
		model.addAttribute("total",total);
		model.addAttribute("cartList",cartList);
		return "showCart";
	}
	
	@RequestMapping("/deleteCart")
	public String deleteCart(HttpSession session,Integer bookId) {
		User u = (User)session.getAttribute("user");
		boolean flag = cartService.deleteCart(bookId,u);
		return "redirect:/showCart.do";
	}
	
	@RequestMapping("/deleteAll")
	public String deleteAll(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		boolean flag = cartService.deleteAll(user);
		if(!flag)
			model.addAttribute("errorMsg","删除失败");
		return "redirect:/showCart.do?errorMsg=errorMsg";
	}
	
	@RequestMapping("/deleteSomeCart")
	public String deleteSomeCart(HttpSession session,Model model,Integer [] delete) {
		User user = (User)session.getAttribute("user");
		boolean flag = cartService.deleteSomeCart(user,delete);
		if (!flag)
			model.addAttribute("errorMsg","批量删除失败");
		return "redirect:/showCart.do";
	}
	
	@RequestMapping("/updateCount")
	public String updateCount(HttpSession session,Cart cart) {
		User user = (User)session.getAttribute("user");
		cart.setUser(user);
		cart.setAmount();
		cartService.updateCount(cart);
		return "redirect:/showCart.do";
	}
}

package com.onest.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onest.bean.Address;
import com.onest.bean.User;
import com.onest.service.AddressService;

@Controller
public class AddressAction {
	@Autowired
	private AddressService addressService;
	@RequestMapping("/judgeAddress")
	public String selectAddress(HttpSession session,Model model,Integer bookId,Integer count) {
		User user = (User)session.getAttribute("user");
		List<Address> addressList = addressService.selectAddress(user);
		session.setAttribute("bookId", bookId);
		session.setAttribute("count", count);
		if(addressList.size() == 0)
			return "addAddress";
		return "redirect:/selectAllAddress.do";
	}
	
	@RequestMapping("/addAddress")
	public String addAddress(HttpSession session,Model model,Address address) {
		User user = (User)session.getAttribute("user");
		boolean flag = addressService.addAddress(user,address);
		if (flag)
			return "redirect:/selectAllAddress.do";
		model.addAttribute("errorMsg","ÃÌº” ß∞‹");
		return "addAddress";
	}
	
	@RequestMapping("/selectAllAddress")
	public String selectAllAddress(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		List<Address> addressList = addressService.selectAddress(user);
		model.addAttribute("addressList",addressList);
		return "selectAddress";
	}
	
	@RequestMapping("/updateAddress")
	public String updateAddress(Address address) {
		addressService.updateAddress(address);
		return "redirect:/userSelect.do";
	}
	
	@RequestMapping("/deleteAddress")
	public String deleteAddress(Integer addressId) {
		addressService.deleteAddress(addressId);
		return "redirect:/userSelect.do";
	}
}

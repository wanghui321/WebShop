package com.onest.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onest.bean.Admin;
import com.onest.service.AdminService;

@Controller
public class AdminAction {
	@Autowired
	private AdminService adminService;
	
	@RequestMapping("/judgeAdmin")
	public String judgeAdmin(HttpSession session) {
		Admin admin = (Admin)session.getAttribute("admin");
		if (admin != null) {
			return "adminLoginSuccess";
		}
		return "adminLogin";
	}
	
	@RequestMapping("/adminLogin")
	public String login(Model model,HttpSession session,Admin admin) {
		Admin a = adminService.login(admin);
		if (a != null) {
			session.setAttribute("admin", a);
			return "adminLoginSuccess";
		} else {
			model.addAttribute("errorMsg","用户名或密码错误");
			return "adminLogin";
		}
	}
	
	@RequestMapping("/removeAdmin")
	public String removeAdmin(HttpSession session) {
		session.removeAttribute("admin");
		return "adminLogin";
	}
}

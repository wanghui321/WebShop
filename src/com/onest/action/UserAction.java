package com.onest.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onest.bean.Address;
import com.onest.bean.Page;
import com.onest.bean.User;
import com.onest.page.Paging;
import com.onest.service.AddressService;
import com.onest.service.UserService;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	private AddressService addressService;
	@RequestMapping("/userLogin")
	public String login(Model model,HttpSession session,User user) {
		User u = userService.login(user);
		if (u != null) {
			session.setAttribute("user", u);
		}
		else {
			model.addAttribute("errorMsg","ÓÃ»§Ãû»òÃÜÂë´íÎó");
			return "userLogin";
		}
		return "redirect:/selectAllBook.do";
	}
	
	@RequestMapping("/userRegist")
	public String regist(HttpServletRequest request,Model model,User user,@RequestParam MultipartFile file) {
		String rootPath = request.getServletContext().getRealPath("/");
		try {
			FileCopyUtils.copy(file.getBytes(),new File(rootPath + "/headImg",file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String realPath = "headImg/" + file.getOriginalFilename();
		user.setImgUrl(realPath);
		boolean flag = userService.regist(user);
		if (flag)
			return "userRegistSuccess";
		else {
			model.addAttribute("errorMsg","ÓÃ»§×¢²áÊ§°Ü£¡");
			return "userRegist";
		}
	}
	
	@RequestMapping("/userSelect")
	public String userSelect(HttpSession session,Model model) {
		User user = (User)session.getAttribute("user");
		if (user == null)
			return "userLogin";
		List<Address> addressList = addressService.selectAddress(user);
		model.addAttribute("addressList",addressList);
		return "userUpdate";
	}
	
	@RequestMapping("/userUpdate")
	public String userUpdate(Model model,User user) {
		boolean flag = userService.userUpdate(user);
		if (flag) {
			return "userUpdateSuccess";
		} else {
			model.addAttribute("errorMsg","ÐÞ¸ÄÊ§°Ü");
			return "userUpdate";
		}
	}
	
	@RequestMapping("/selectAllUser")
	public String selectAllUser(Model model,Integer pageS) {
		Long totalCount = userService.getCount();
		Paging paging = new Paging();
		Page page = paging.checkByPage(totalCount, pageS);
		List<User> userList = userService.selectAllUser(page);
		model.addAttribute("userList",userList);
		model.addAttribute("page",page);
		return "allUser";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(Model model,Integer userId) {
		boolean flag = userService.deleteUser(userId);
		if (!flag)
			model.addAttribute("errorMsg","É¾³ýÊ§°Ü");
		return "redirect:/selectAllUser.do";
	}
	
	@RequestMapping("/updateImg")
	public String updateImg(HttpServletRequest request,HttpSession session,@RequestParam MultipartFile file) {
		User user = (User)session.getAttribute("user");
		String rootPath = request.getServletContext().getRealPath("/");
		try {
			FileCopyUtils.copy(file.getBytes(),new File(rootPath + "/headImg",file.getOriginalFilename()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String realPath = "headImg/" + file.getOriginalFilename();
		userService.updateImg(realPath,user);
		User u = userService.selectUser(user.getUserId());
		session.setAttribute("user", u);
		return "updateImgSuccess";
	}
	
	@RequestMapping("/removeUser")
	public String removeUser(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/selectAllBook.do";
	}
	
	@RequestMapping("/checkUser")
	public void checkUser(String userName,HttpServletResponse response) {
		boolean flag = userService.checkUser(userName);
		if (flag) {
			try {
				response.getWriter().print("yes");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				response.getWriter().print("no");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

package com.onest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Page;
import com.onest.bean.User;
import com.onest.dao.UserDaoImpl;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserDaoImpl userDao;
	
	public User login(User user) {
		User u = userDao.login(user);
		return u;
	}
	
	public boolean regist(User user) {
		boolean flag = userDao.regist(user);
		return flag;
	}
	
	public boolean userUpdate(User u) {
		boolean flag = userDao.userUpdate(u);
		return flag;
	}
	
	public List<User> selectAllUser(Page page) {
		List<User> userList = userDao.selectAllUser(page);
		return userList;
	}
	
	public boolean deleteUser(Integer userId) {
		boolean flag  = userDao.deleteUser(userId);
		return flag;
	}

	public Long getCount() {
		Long count = userDao.getCount();
		return count;
	}

	public boolean checkUser(String userName) {
		boolean flag = userDao.checkUser(userName);
		return flag;
	}

	public User selectUser(Integer userId) {
		User user = userDao.selectUser(userId);
		return user;
	}

	public void updateImg(String realPath,User user) {
		// TODO Auto-generated method stub
		userDao.updateImg(realPath,user);
	}
}

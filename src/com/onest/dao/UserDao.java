package com.onest.dao;

import java.util.List;

import com.onest.bean.Page;
import com.onest.bean.User;

public interface UserDao {
	public User login(User user);
	public boolean regist(User user);
	public boolean userUpdate(User user);
	public List<User> selectAllUser(Page page);
	public boolean deleteUser(Integer userId);
	public Long getCount();
	public boolean checkUser(String userName);
	public User selectUser(Integer userId);
	public void updateImg(String realPath,User user);
}

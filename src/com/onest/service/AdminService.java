package com.onest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onest.bean.Admin;
import com.onest.dao.AdminDao;

@Service
@Transactional
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public Admin login(Admin admin) {
		Admin a = adminDao.login(admin);
		return a;
	}
}

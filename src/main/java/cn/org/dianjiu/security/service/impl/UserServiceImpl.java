package cn.org.dianjiu.security.service.impl;

import cn.org.dianjiu.security.dao.UserDao;
import cn.org.dianjiu.security.entity.UserEntity;
import cn.org.dianjiu.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	/**
	 * 保存用户
	 */
	@Override
	public void saveUser(UserEntity user) {
		userDao.insertUser(user);
	}

}

package cn.org.dianjiu.security.service;


import cn.org.dianjiu.security.entity.UserEntity;

public interface UserService {
	/**
	 * 保存用户
	 * @param user
	 */
	void saveUser(UserEntity user);

}

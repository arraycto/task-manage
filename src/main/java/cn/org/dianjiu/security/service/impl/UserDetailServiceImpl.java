package cn.org.dianjiu.security.service.impl;

import cn.org.dianjiu.security.dao.MenuDao;
import cn.org.dianjiu.security.dao.RoleDao;
import cn.org.dianjiu.security.dao.UserDao;
import cn.org.dianjiu.security.entity.Menu;
import cn.org.dianjiu.security.entity.Role;
import cn.org.dianjiu.security.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 获取用户相关信息
 * @author DianJiu
 *
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private MenuDao menuDao;

	@Override
	public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户名查找用户
		UserEntity user = userDao.getUserByUsername(username);
		System.out.println(user);
		if (user != null) {
			System.out.println("UserDetailsService");
			//根据用户id获取用户角色
			List<Role> roles = roleDao.getUserRoleByUserId(user.getId());
			// 填充权限
			Collection<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
			for (Role role : roles) {
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			}
			//填充权限菜单
			List<Menu> menus=menuDao.getRoleMenuByRoles(roles);
			return new UserEntity(username,user.getPassword(),authorities,menus);
		} else {
			System.out.println(username +" not found");
			throw new UsernameNotFoundException(username +" not found");
		}		
	}

}

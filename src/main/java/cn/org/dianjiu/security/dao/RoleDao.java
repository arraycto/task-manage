package cn.org.dianjiu.security.dao;

import cn.org.dianjiu.security.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleDao {
	/**
	 * 根据用户id获取用户
	 * @param id
	 * @return
	 */
	List<Role> getUserRoleByUserId(Long id);

}

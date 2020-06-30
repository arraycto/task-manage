package cn.org.dianjiu.task.entity;

import lombok.Data;
            /**
 * (TUserRoles)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TUserRoles {
    /**
    * 用户角色对照ID
    */
    private Integer id;
    /**
    * 用户ID
    */
    private Integer userId;
    /**
    * 角色ID
    */
    private Integer roleId;

}
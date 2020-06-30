package cn.org.dianjiu.task.entity;

import lombok.Data;
            /**
 * (TRoleMenus)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TRoleMenus {
    /**
    * 角色菜单id
    */
    private Integer id;
    /**
    * 角色id
    */
    private Integer roleId;
    /**
    * 菜单id
    */
    private Integer menuId;

}
package cn.org.dianjiu.task.entity;

import lombok.Data;

import java.util.Date;

/**
 * (TUser)实体类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TUser {
    /**
    * 用户id
    */
    private Integer id;
    /**
    * 用户名
    */
    private String username;
    /**
    * 密码
    */
    private String password;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private String updator;
    /**
     * 修改时间
     */
    private Date updateTime;
}
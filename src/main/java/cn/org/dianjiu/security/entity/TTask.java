package cn.org.dianjiu.security.entity;

import java.util.Date;
import lombok.Data;
                                                    import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 定时任务信息表(TTask)实体类
 *
 * @author makejava
 * @since 2020-06-17 23:42:10
 */
@Data 
public class TTask {
    
    private Long id;
    /**
    * 任务编号
    */
    private String taskNo;
    /**
    * 任务名称
    */
    private String taskName;
    /**
    * 分组名称
    */
    private String groupName;
    /**
    * 任务描述
    */
    private String taskDesc;
    /**
    * CRON表达式
    */
    private String cornRule;
    /**
    * 请求方式
    */
    private String sendType;
    /**
    * 请求地址
    */
    private String sendUrl;
    /**
    * 请求参数
    */
    private String sendParam;
    /**
    * 任务状态: 0-停用 1-启用
    */
    private Integer status;
    /**
    * 删除状态：0-删除 1-存在
    */
    private Integer deleted;
    /**
    * 下次执行时间
    */
    private Long nextExecuteTime;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 最近修改时间
    */
    private Date updateTime;

}
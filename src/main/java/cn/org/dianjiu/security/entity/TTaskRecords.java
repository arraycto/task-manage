package cn.org.dianjiu.security.entity;

import java.util.Date;
import lombok.Data;
                                                import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 定时任务执行情况记录表(TTaskRecords)实体类
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data 
public class TTaskRecords {
    
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
    * 返回信息
    */
    private String returnInfo;
    /**
    * 执行时间
    */
    private Long executeTime;
    /**
    * 执行状态
    */
    private String executeStatus;
    /**
    * 失败次数
    */
    private String failCount;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 最近修改时间
    */
    private Date updateTime;

}
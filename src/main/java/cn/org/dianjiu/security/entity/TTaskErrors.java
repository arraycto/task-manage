package cn.org.dianjiu.security.entity;

import java.util.Date;
import lombok.Data;
                    import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 定时任务出错现场信息表(TTaskErrors)实体类
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data 
public class TTaskErrors {
    
    private Long id;
    /**
    * 任务执行记录Id
    */
    private String recordId;
    /**
    * 信息关键字 task:errMeg
    */
    private String errorKey;
    /**
    * 信息内容
    */
    private String errorValue;
    /**
    * 创建时间
    */
    private Date createtime;
    /**
    * 最近修改时间
    */
    private Date updateTime;

}
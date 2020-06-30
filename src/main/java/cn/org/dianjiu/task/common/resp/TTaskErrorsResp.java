package cn.org.dianjiu.task.common.resp;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 定时任务出错现场信息表(TTaskErrorsResp) Resp
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data
public class TTaskErrorsResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    private Long id;
    @ApiModelProperty("任务执行记录Id")
    private String recordId;
    @ApiModelProperty("信息关键字 task:errMeg")
    private String errorKey;
    @ApiModelProperty("信息内容")
    private String errorValue;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createtime;
    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
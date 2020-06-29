package cn.org.dianjiu.security.common.req;

import java.util.Date;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 定时任务出错现场信息表(TTaskErrorsReq) Req
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data
public class TTaskErrorsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    private Long id;
    @ApiModelProperty("任务执行记录Id")
    @NotBlank(message = "任务执行记录Id不能为空")
    private String recordId;
    @ApiModelProperty("信息关键字 task:errMeg")
    @NotBlank(message = "信息关键字 task:errMeg不能为空")
    private String errorKey;
    @ApiModelProperty("信息内容")
    @NotBlank(message = "信息内容不能为空")
    private String errorValue;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @ApiModelProperty("最近修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
package cn.org.dianjiu.security.common.req;

import java.util.Date;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 定时任务执行情况记录表(TTaskRecordsReq) Req
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data
public class TTaskRecordsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    private Long id;
    @ApiModelProperty("任务编号")
    private String taskNo;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("分组名称")
    private String groupName;
    @ApiModelProperty("请求方式")
    private String sendType;
    @ApiModelProperty("请求地址")
    private String sendUrl;
    @ApiModelProperty("请求参数")
    private String sendParam;
    @ApiModelProperty("返回信息")
    private String returnInfo;
    @ApiModelProperty("执行时间")
    private Long executeTime;
    @ApiModelProperty("执行状态")
    private String executeStatus;
    @ApiModelProperty("失败次数")
    private String failCount;
    @ApiModelProperty("创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty("最近修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
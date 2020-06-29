package cn.org.dianjiu.security.common.resp;

import java.util.Date;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 定时任务信息表(TTaskResp) Resp
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Data
public class TTaskResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    private Long id;
    @ApiModelProperty("任务编号")
    private String taskNo;
    @ApiModelProperty("任务名称")
    private String taskName;
    @ApiModelProperty("分组名称")
    private String groupName;
    @ApiModelProperty("任务描述")
    private String taskDesc;
    @ApiModelProperty("CRON表达式")
    private String cornRule;
    @ApiModelProperty("请求方式")
    private String sendType;
    @ApiModelProperty("请求地址")
    private String sendUrl;
    @ApiModelProperty("请求参数")
    private String sendParam;
    @ApiModelProperty("任务状态: 0-停用 1-启用")
    private Integer status;
    @ApiModelProperty("删除状态：0-删除 1-存在")
    private Integer deleted;
    @ApiModelProperty("下次执行时间")
    private Long nextExecuteTime;
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
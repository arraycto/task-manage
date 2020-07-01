package cn.org.dianjiu.task.common.req;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 定时任务执行情况记录表(TTaskRecordsReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data
public class TTaskRecordsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
    private Long id;
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
    @ApiModelProperty("任务状态")
    private String taskStatus;

}
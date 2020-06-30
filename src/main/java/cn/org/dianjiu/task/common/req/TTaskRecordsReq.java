package cn.org.dianjiu.task.common.req;

import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
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
    @NotBlank(message = "任务名称不能为空")
            private String taskName;
        @ApiModelProperty("分组名称")
    @NotBlank(message = "分组名称不能为空")
            private String groupName;
        @ApiModelProperty("请求方式")
    @NotBlank(message = "请求方式不能为空")
            private String sendType;
        @ApiModelProperty("请求地址")
    @NotBlank(message = "请求地址不能为空")
            private String sendUrl;
        @ApiModelProperty("请求参数")
    @NotBlank(message = "请求参数不能为空")
            private String sendParam;
        @ApiModelProperty("返回信息")
    @NotBlank(message = "返回信息不能为空")
            private String returnInfo;
        @ApiModelProperty("执行时间")
    @NotBlank(message = "执行时间不能为空")
            private Long executeTime;
        @ApiModelProperty("任务状态")
    @NotBlank(message = "任务状态不能为空")
            private String taskStatus;
        @ApiModelProperty("创建时间")
    @NotBlank(message = "创建时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        @ApiModelProperty("修改时间")
    @NotBlank(message = "修改时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

}
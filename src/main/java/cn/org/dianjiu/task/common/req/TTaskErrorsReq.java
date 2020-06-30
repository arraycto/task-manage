package cn.org.dianjiu.task.common.req;

import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
/**
 * 定时任务出错现场信息表(TTaskErrorsReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TTaskErrorsReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
            private Long id;
        @ApiModelProperty("任务执行记录Id")
    @NotBlank(message = "任务执行记录Id不能为空")
            private String taskexecuterecordid;
        @ApiModelProperty("信息关键字")
    @NotBlank(message = "信息关键字不能为空")
            private String errorkey;
        @ApiModelProperty("信息内容")
    @NotBlank(message = "信息内容不能为空")
            private String errorvalue;
        @ApiModelProperty("创建时间")
    @NotBlank(message = "创建时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        @ApiModelProperty("修改时间")
    @NotBlank(message = "修改时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;

}
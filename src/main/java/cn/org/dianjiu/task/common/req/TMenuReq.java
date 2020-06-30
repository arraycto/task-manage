package cn.org.dianjiu.task.common.req;

import java.util.Date;
import lombok.Data;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
/**
 * (TMenuReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:33
 */
@Data 
public class TMenuReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("菜单ID")
    @NotBlank(message = "菜单ID不能为空")
            private Integer id;
        @ApiModelProperty("菜单名称")
    @NotBlank(message = "菜单名称不能为空")
            private String menuName;
        @ApiModelProperty("Controller路径")
    @NotBlank(message = "Controller路径不能为空")
            private String menuUrl;
        @ApiModelProperty("菜单编码")
    @NotBlank(message = "菜单编码不能为空")
            private String menuCode;
        @ApiModelProperty("父菜单ID")
    @NotBlank(message = "父菜单ID不能为空")
            private Integer parentId;
        @ApiModelProperty("菜单类型：0-菜单1-按钮")
    @NotBlank(message = "菜单类型：0-菜单1-按钮不能为空")
            private Integer menuType;
        @ApiModelProperty("显示序号")
    @NotBlank(message = "显示序号不能为空")
            private Integer orderNum;
        @ApiModelProperty("创建人")
    @NotBlank(message = "创建人不能为空")
            private String creator;
        @ApiModelProperty("创建时间")
    @NotBlank(message = "创建时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        @ApiModelProperty("修改人")
    @NotBlank(message = "修改人不能为空")
            private String updator;
        @ApiModelProperty("修改时间")
    @NotBlank(message = "修改时间不能为空")
            @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date updateTime;
        @ApiModelProperty("删除状态：0-存在1-已删除")
    @NotBlank(message = "删除状态：0-存在1-已删除不能为空")
            private Integer deleted;

}
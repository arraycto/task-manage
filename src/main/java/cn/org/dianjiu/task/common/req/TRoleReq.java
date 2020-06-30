package cn.org.dianjiu.task.common.req;

import lombok.Data;
        /**
 * (TRoleReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TRoleReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
            private Integer id;
        @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
            private String roleName;

}
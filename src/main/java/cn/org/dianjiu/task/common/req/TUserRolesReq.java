package cn.org.dianjiu.task.common.req;

import lombok.Data;
            /**
 * (TUserRolesReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TUserRolesReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("用户角色对照ID")
    @NotBlank(message = "用户角色对照ID不能为空")
            private Integer id;
        @ApiModelProperty("用户ID")
    @NotBlank(message = "用户ID不能为空")
            private Integer userId;
        @ApiModelProperty("角色ID")
    @NotBlank(message = "角色ID不能为空")
            private Integer roleId;

}
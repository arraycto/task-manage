package cn.org.dianjiu.task.common.req;

import lombok.Data;
            /**
 * (TRoleMenusReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TRoleMenusReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("角色菜单id")
    @NotBlank(message = "角色菜单id不能为空")
            private Integer id;
        @ApiModelProperty("角色id")
    @NotBlank(message = "角色id不能为空")
            private Integer roleId;
        @ApiModelProperty("菜单id")
    @NotBlank(message = "菜单id不能为空")
            private Integer menuId;

}
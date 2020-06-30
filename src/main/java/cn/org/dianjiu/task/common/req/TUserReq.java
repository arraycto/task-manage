package cn.org.dianjiu.task.common.req;

import lombok.Data;
            /**
 * (TUserReq) Req
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TUserReq implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("用户id")
    @NotBlank(message = "用户id不能为空")
            private Integer id;
        @ApiModelProperty("用户名")
    @NotBlank(message = "用户名不能为空")
            private String username;
        @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
            private String password;

}
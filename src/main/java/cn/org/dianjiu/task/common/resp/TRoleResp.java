package cn.org.dianjiu.task.common.resp;

import lombok.Data;
        /**
 * (TRoleResp) Resp
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Data 
public class TRoleResp implements Serializable {
    private static final long serialVersionUID = 9155949248117098529L;
        @ApiModelProperty("角色id")
            private Integer id;
        @ApiModelProperty("角色名称")
            private String roleName;

}
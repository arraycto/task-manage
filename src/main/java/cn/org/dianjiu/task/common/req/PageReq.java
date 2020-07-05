package cn.org.dianjiu.task.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Point9
 * @Date: 2020/1/14 21:44
 */
@Getter
@Setter
public class PageReq<T> implements Serializable {

    private static final long serialVersionUID = 2914853172898904181L;

    @ApiModelProperty("当前页")
    private Integer page;
    @ApiModelProperty("页大小")
    private Integer size;
    @ApiModelProperty("响应数据")
    private T date;

    public PageReq() {
    }

    public PageReq(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageReq(Integer page, Integer size, T date) {
        this.page = page;
        this.size = size;
        this.date = date;
    }
}

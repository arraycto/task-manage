package cn.org.dianjiu.task.common.resp;

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
public class PageResp<T> implements Serializable {

    private static final long serialVersionUID = 2914853172898904181L;

    @ApiModelProperty("当前页")
    private Integer page;
    @ApiModelProperty("页大小")
    private Integer size;
    @ApiModelProperty("总条数")
    private Integer total;
    @ApiModelProperty("响应数据")
    private List<T> date;

    public PageResp() {
    }

    public PageResp(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageResp(Integer total, List<T> date) {
        this.total = total;
        this.date = date;
    }

    public PageResp(Integer page, Integer size, List<T> date) {
        this.page = page;
        this.size = size;
        this.date = date;
    }
}

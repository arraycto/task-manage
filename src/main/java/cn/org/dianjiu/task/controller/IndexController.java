package cn.org.dianjiu.task.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用访问拦截匹配
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:32
 */
@Controller
public class IndexController {
	
	/**
	 * 页面跳转
	 * @param url
	 * @return
	 */
	@RequestMapping("{url}.html")
	public String page(@PathVariable("url") String url) {
		return  url;
	}
	/**
	 * 页面跳转(二级目录)
	 * @param module
	 * @param url
	 * @return
	 */
	@RequestMapping("{module}/{url}.html")
	public String page(@PathVariable("module") String module,@PathVariable("url") String url) {
		return module + "/" + url;
	}
	
}

package cn.org.dianjiu.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping("/main")
	public ModelAndView toMainPage() {
		//获取登录的用户名
		Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username=null;
		if(principal instanceof UserDetails) {
			username=((UserDetails)principal).getUsername();
		}else {
			username=principal.toString();
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("username", username);
		return mav;
	}

	/**
	 * 列表页
	 * @return
	 */
	@GetMapping("/index")
	public String toIndex() {
		return "index";
	}

	/**
	 * 执行记录
	 * @return
	 */
	@GetMapping("/taskrecords")
	public String toTaskRecords() {
		return "taskrecords";
	}

	/**
	 * 报错日志
	 * @return
	 */
	@GetMapping("/taskerrors")
	public String toTaskErrors() {
		return "taskerrors";
	}

	/**
	 * 新增任务
	 * @return
	 */
	@GetMapping("/addtask")
	public String toAddTask() {
		return "addtask";
	}

	/**
	 * 编辑任务
	 * @return
	 */
	@GetMapping("/updatetask")
	public String toUpdateTask() {
		return "updatetask";
	}

}

package cn.org.dianjiu.task.config.quartz;

import cn.org.dianjiu.task.common.constants.Constant;
import cn.org.dianjiu.task.common.req.TTaskDetailsReq;
import cn.org.dianjiu.task.common.util.JobUtils;
import cn.org.dianjiu.task.service.TTaskDetailsServiceI;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class TaskRunner implements ApplicationRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(TaskRunner.class);

    @Autowired
    private TTaskDetailsServiceI tTaskDetailsService;

    @Override
    public void run(ApplicationArguments var) throws Exception{
        /**
         * 系统启动的时候会初始化一个任务
         */
        int count = tTaskDetailsService.countAll();
        if(count==0){
            LOGGER.info("初始化测试任务");
            TTaskDetailsReq tTaskDetailsReq = new TTaskDetailsReq();
            tTaskDetailsReq.setTaskNo(JobUtils.getTaskNo());
            tTaskDetailsReq.setTaskName("local-test");
            tTaskDetailsReq.setGroupNo(JobUtils.getGroupNo());
            tTaskDetailsReq.setGroupName("task-manage");
            tTaskDetailsReq.setTaskDesc("获取定时任务信息");
            tTaskDetailsReq.setSendType(Constant.GET);
            tTaskDetailsReq.setSendUrl("127.0.0.1/tTaskDetails/get/1");
            tTaskDetailsReq.setSendParam("");
            tTaskDetailsReq.setCornRule("*/5 * * * * ?");
            tTaskDetailsReq.setStatus("1");
            tTaskDetailsService.insert(tTaskDetailsReq);
        }
    }

}

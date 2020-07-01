package cn.org.dianjiu.task.common.job;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.req.TTaskErrorsReq;
import cn.org.dianjiu.task.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.task.common.util.ExceptionUtils;
import cn.org.dianjiu.task.common.util.HttpClientUtils;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.common.util.SpringUtils;
import cn.org.dianjiu.task.service.impl.TTaskErrorsServiceImpl;
import cn.org.dianjiu.task.service.impl.TTaskRecordsServiceImpl;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现序列化接口、防止重启应用出现quartz Couldn't retrieve job because a required class was not found 的问题
 * Job 的实例要到该执行它们的时候才会实例化出来。每次 Job 被执行，一个新的 Job 实例会被创建。
 * 其中暗含的意思就是你的 Job 不必担心线程安全性，因为同一时刻仅有一个线程去执行给定 Job 类的实例，甚至是并发执行同一 Job 也是如此。
 * @DisallowConcurrentExecution 保证上一个任务执行完后，再去执行下一个任务，这里的任务是同一个任务
 */
@DisallowConcurrentExecution
public class DefaultJob implements  Job,Serializable {

    private static Logger logger = LoggerFactory.getLogger(DefaultJob.class);

    private static final long serialVersionUID =  9155949248117098529L;

    private AtomicInteger atomicInteger;

    @Override
    public void execute(JobExecutionContext context){
        atomicInteger = new AtomicInteger(0);
        JobDetail jobDetail = context.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        Integer id = Integer.valueOf(jobDataMap.getString("id"));
        String taskNo = jobDataMap.getString("taskNo");
        String taskName = jobDataMap.getString("taskName");
        String groupNo = jobDataMap.getString("groupNo");
        String groupName = jobDataMap.getString("groupName");
        String taskDesc = jobDataMap.getString("taskDesc");
        String sendType = jobDataMap.getString("sendType");
        String sendUrl = jobDataMap.getString("sendUrl");
        String sendParam = jobDataMap.getString("sendParam");
        logger.info("定时任务被执行:id={},taskNo={},taskName={},groupNo={},groupName={},taskDesc={},sendType={},sendUrl={},sendParam={}",id, taskNo, taskName, groupNo, groupName, taskDesc, sendType, sendUrl, sendParam);
        TTaskRecordsServiceImpl taskRecordsService = SpringUtils.getBean(TTaskRecordsServiceImpl.class);
        TTaskErrorsServiceImpl taskErrorsService = SpringUtils.getBean(TTaskErrorsServiceImpl.class);
        TTaskRecordsResp records = null;
        String result = "";
        try {
            //保存定时任务的执行记录
            records = taskRecordsService.addTaskRecords(id);
            if (ObjectUtils.checkObjAllFieldsIsNull(records)) {
                logger.info("taskNo={}保存执行记录失败", taskNo);
                new BusinessException("400","【taskNo】"+taskNo+"保存执行记录失败");
            }

            if ("postJson".equals(sendType)) {
                try {
                    result = HttpClientUtils.postJson(sendUrl, sendParam);
                    logger.info("taskNo={},sendtype={}执行结果result{}", taskNo, sendType, result);
                    if (ObjectUtils.isBlank(result)) {
                        throw new RuntimeException("taskNo=" + taskNo + "http方式返回null");
                    }
                } catch (Exception ex) {
                    logger.error("");
                    throw ex;
                }
            }
            if("postFrom".equals(sendType)){
                // TODO
            }
            if("get".equals(sendType)){
                // TODO
            }
        } catch (Exception ex) {
            logger.error("定时任务执行异常:id={},taskNo={},taskName={},groupNo={},groupName={},taskDesc={},sendType={},sendUrl={},sendParam={}",id, taskNo, taskName, groupNo, groupName, taskDesc, sendType, sendUrl, sendParam);
            atomicInteger.incrementAndGet();
            TTaskErrorsReq tTaskErrorsReq = new TTaskErrorsReq();
            tTaskErrorsReq.setTaskexecuterecordid(String.valueOf(records.getId()));
            tTaskErrorsReq.setErrorkey(taskNo + ":" + ex.getMessage());
            tTaskErrorsReq.setErrorvalue(ExceptionUtils.getExceptionDetail(ex));
            taskErrorsService.insert(tTaskErrorsReq);
        }
        // TODO 更新任务详情表的下次执行时间和执行记录表的执行状态和返回值
        //更新执行记录的状态和返回值
        taskRecordsService.updateRecordById(atomicInteger.get(), records.getId(),result);
    }

}
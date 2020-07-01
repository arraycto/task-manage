package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.job.DefaultJob;
import cn.org.dianjiu.task.common.req.TTaskDetailsReq;
import cn.org.dianjiu.task.common.req.TTaskErrorsReq;
import cn.org.dianjiu.task.common.resp.TTaskDetailsResp;
import cn.org.dianjiu.task.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.task.common.util.ExceptionUtils;
import cn.org.dianjiu.task.common.util.HttpClientUtils;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.common.util.SpringUtils;
import cn.org.dianjiu.task.dao.TTaskDetailsDao;
import cn.org.dianjiu.task.service.TTaskDetailsServiceI;
import cn.org.dianjiu.task.entity.TTaskDetails;
import org.quartz.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务信息表(TTaskDetails)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TTaskDetailsServiceImpl implements TTaskDetailsServiceI, InitializingBean {

    @Autowired
    private TTaskDetailsDao tTaskDetailsDao;

    @Autowired
    private SchedulerFactoryBean schedulerBean;

    private AtomicInteger atomicInteger;

    @Override
    public TTaskDetailsResp getById(Integer id) {
        TTaskDetailsResp tTaskDetailsResp = new TTaskDetailsResp();
        TTaskDetails tTaskDetails = tTaskDetailsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetails)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskDetails,tTaskDetailsResp);
        return tTaskDetailsResp;
    }

    @Override
    public TTaskDetailsResp getByEntity(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetailsResp tTaskDetailsResp = new TTaskDetailsResp();
        TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        TTaskDetails tTaskDetails1 = tTaskDetailsDao.getByEntity(tTaskDetails);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetails1)){
            log.error("根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
            new BusinessException("400","根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskDetails1,tTaskDetailsResp);
        return tTaskDetailsResp;
    }

    @Override
    public List<TTaskDetailsResp> listByEntity(TTaskDetailsReq tTaskDetailsReq) {
        List<TTaskDetailsResp> list = new ArrayList<>();
        TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        List<TTaskDetails> tTaskDetailss = tTaskDetailsDao.listByEntity(tTaskDetails);
        if(null == tTaskDetailss || tTaskDetailss.isEmpty()){
            log.error("根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
            new BusinessException("400","根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
        }
        for (TTaskDetails tTaskDetails1 : tTaskDetailss ) {
            TTaskDetailsResp tTaskDetailsResp = new TTaskDetailsResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetails1)){
                log.error("根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
                new BusinessException("400","根据tTaskDetailsReq【"+tTaskDetailsReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tTaskDetails1,tTaskDetailsResp);
            list.add(tTaskDetailsResp);
        }
        return list;
    }

    @Override
    public List<TTaskDetailsResp> listByIds(List<Integer> ids) {
      List<TTaskDetailsResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            new BusinessException("400","id集合不能为空！");
        }
        List<TTaskDetails> tTaskDetailss  = tTaskDetailsDao.listByIds(ids);
        if(null != tTaskDetailss && !tTaskDetailss.isEmpty()){
            for (TTaskDetails tTaskDetails1 : tTaskDetailss ) {
                TTaskDetailsResp tTaskDetailsResp = new TTaskDetailsResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetails1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tTaskDetails1,tTaskDetailsResp);
                list.add(tTaskDetailsResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        //TODO 获取下次执行时间
        Date date = new Date();
        tTaskDetails.setCreateTime(date);
        tTaskDetails.setUpdateTime(date);
        return tTaskDetailsDao.insert(tTaskDetails);
    }

    @Override
    public int insertBatch(List<TTaskDetailsReq> list) {
      List<TTaskDetails> tTaskDetailss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TTaskDetailsReq tTaskDetailsReq : list) {
            TTaskDetails tTaskDetails = new TTaskDetails();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
                log.error("执行批量插入的集合为空！");
                new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
            //TODO 获取下次执行时间
            tTaskDetailss.add(tTaskDetails);
        }
        return tTaskDetailsDao.insertBatch(tTaskDetailss);
    }

    @Override
    public int update(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        //TODO 更新下次执行时间
        tTaskDetails.setUpdateTime(new Date());
        return tTaskDetailsDao.update(tTaskDetails);
    }

    @Override
    public int updateBatch(List<TTaskDetailsReq> list) {
      List<TTaskDetails> tTaskDetailss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TTaskDetailsReq tTaskDetailsReq : list) {
            TTaskDetails tTaskDetails = new TTaskDetails();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
                log.error("执行批量更新的集合为空！");
                new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
            //TODO 更新下次执行时间
            tTaskDetailss.add(tTaskDetails);
        }
        return tTaskDetailsDao.updateBatch(tTaskDetailss);
    }

    @Override
    public int deleteById(Integer id) {
        return tTaskDetailsDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        return tTaskDetailsDao.deleteByEntity(tTaskDetails);
    }

    @Override
    public int deleteByIds(List<Integer> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            new BusinessException("400","id集合不能为空！");
        }
        return tTaskDetailsDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskDetailsDao.countAll();
    }

    @Override
    public int countByEntity(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        return tTaskDetailsDao.countByEntity(tTaskDetails);
    }

    @Override
    public int optionTask(Integer id){
        //根据taskNo查询定时任务
        TTaskDetailsResp taskDetailsResp = getById(id);
        if (ObjectUtils.checkObjAllFieldsIsNull(taskDetailsResp)) {
            new BusinessException("400","根据taskID没有查到信息！");
        }
        TTaskDetailsReq tTaskDetailsReq = new TTaskDetailsReq();
        tTaskDetailsReq.setId(id);
        String status = taskDetailsResp.getStatus();
        Scheduler scheduler = schedulerBean.getScheduler();
        if ("1".equals(status)){//暂停
            tTaskDetailsReq.setStatus("0");
            try {
                scheduler.deleteJob(new JobKey(taskDetailsResp.getTaskNo()));
            } catch (SchedulerException e) {
                new BusinessException("400", ExceptionUtils.getExceptionDetail(e));
            }
            return update(tTaskDetailsReq);
        }
        tTaskDetailsReq.setStatus("1");//启用
        //scheduler.deleteJob(new JobKey(taskDetailsResp.getTaskNo()));
        TTaskDetails tTaskDetails = new TTaskDetails();
        ObjectUtils.copyProperties(taskDetailsResp,tTaskDetails);
        schedule(tTaskDetails, scheduler);
        //查到取出原有状态
        return update(tTaskDetailsReq);
    }

    @Override
    public int runtask(Integer id) {
        atomicInteger = new AtomicInteger(0);
        TTaskDetailsResp taskDetailsResp = getById(id);
        String taskNo = taskDetailsResp.getTaskNo();
        String taskName = taskDetailsResp.getTaskName();
        String groupNo = taskDetailsResp.getGroupNo();
        String groupName = taskDetailsResp.getGroupName();
        String taskDesc = taskDetailsResp.getTaskDesc();
        String sendType = taskDetailsResp.getSendType();
        String sendUrl = taskDetailsResp.getSendUrl();
        String sendParam = taskDetailsResp.getSendParam();
        log.info("定时任务被执行:id={},taskNo={},taskName={},groupNo={},groupName={},taskDesc={},sendType={},sendUrl={},sendParam={}",id, taskNo, taskName, groupNo, groupName, taskDesc, sendType, sendUrl, sendParam);
        TTaskRecordsServiceImpl taskRecordsService = SpringUtils.getBean(TTaskRecordsServiceImpl.class);
        TTaskErrorsServiceImpl taskErrorsService = SpringUtils.getBean(TTaskErrorsServiceImpl.class);
        TTaskRecordsResp records = null;
        String result = "";
        try {
            //保存定时任务的执行记录
            records = taskRecordsService.addTaskRecords(id);
            if (ObjectUtils.checkObjAllFieldsIsNull(records)) {
                log.info("taskNo={}保存执行记录失败", taskNo);
                new BusinessException("400","【taskNo】"+taskNo+"保存执行记录失败");
            }

            if ("postJson".equals(sendType)) {
                try {
                    result = HttpClientUtils.postJson(sendUrl, sendParam);
                    log.info("taskNo={},sendtype={}执行结果result{}", taskNo, sendType, result);
                    if (ObjectUtils.isBlank(result)) {
                        throw new RuntimeException("taskNo=" + taskNo + "http方式返回null");
                    }
                } catch (Exception ex) {
                    log.error(ExceptionUtils.getExceptionDetail(ex));
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
            log.error("定时任务执行异常:id={},taskNo={},taskName={},groupNo={},groupName={},taskDesc={},sendType={},sendUrl={},sendParam={}",id, taskNo, taskName, groupNo, groupName, taskDesc, sendType, sendUrl, sendParam);
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
        return 0;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initLoadOnlineTasks();
    }

    /**
     * 初始化加载定时任务
     *
     * @throws Exception
     */
    @Override
    public void initLoadOnlineTasks() throws SchedulerException {
        TTaskDetailsReq tTaskDetailsReq = new TTaskDetailsReq();
        tTaskDetailsReq.setStatus("1");//初始化时只加载有效状态的定时任务
        List<TTaskDetailsResp> tTaskDetailsResps = listByEntity(tTaskDetailsReq);
        if (ObjectUtils.isEmpty(tTaskDetailsResps)) {
            log.info("没有需要初始化加载的定时任务");
            new BusinessException("400","没有需要初始化加载的定时任务");
        }
        Scheduler scheduler = schedulerBean.getScheduler();
        for (TTaskDetailsResp tTaskDetailsResp : tTaskDetailsResps) {
            TTaskDetails tTaskDetails = new TTaskDetails();
            ObjectUtils.copyProperties(tTaskDetailsResp,tTaskDetails);
            //添加到任务列表
            schedule(tTaskDetails, scheduler);
        }
    }

    private void schedule(TTaskDetails tTaskDetails, Scheduler scheduler){
        TriggerKey triggerKey = TriggerKey.triggerKey(tTaskDetails.getTaskNo(), Scheduler.DEFAULT_GROUP);
        //构建job信息,调用指定方法
        JobDetail jobDetail = JobBuilder.newJob(DefaultJob.class).withIdentity(tTaskDetails.getTaskName(),
                tTaskDetails.getGroupName())
                .withDescription(tTaskDetails.getTaskDesc()).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("id", tTaskDetails.getId().toString());
        jobDataMap.put("taskNo", tTaskDetails.getTaskNo());
        jobDataMap.put("taskName", tTaskDetails.getTaskName());
        jobDataMap.put("groupNo", tTaskDetails.getGroupNo());
        jobDataMap.put("groupName", tTaskDetails.getGroupName());
        jobDataMap.put("taskDesc", tTaskDetails.getTaskDesc());
        jobDataMap.put("sendType", tTaskDetails.getSendType());
        jobDataMap.put("sendUrl", tTaskDetails.getSendUrl());
        jobDataMap.put("sendParam", tTaskDetails.getSendParam());
        jobDataMap.put("status", tTaskDetails.getStatus());
        jobDataMap.put("nextExecuteTime", tTaskDetails.getNextExecuteTime());
        // 触发时间点
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(tTaskDetails.getCornRule());
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger"+tTaskDetails.getTaskName(), tTaskDetails.getGroupName())
                .startNow().withSchedule(cronScheduleBuilder).build();
        //交由Scheduler安排触发
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            new BusinessException("400","TTaskDetailsServiceImpl schedule() Exception "+ExceptionUtils.getExceptionDetail(e));
        }
        log.info("taskNo={},taskName={},scheduleRule={} load to quartz success!", tTaskDetails.getTaskNo(), tTaskDetails.getTaskName(), tTaskDetails.getCornRule());
    }

}
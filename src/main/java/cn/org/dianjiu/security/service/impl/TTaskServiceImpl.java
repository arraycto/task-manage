package cn.org.dianjiu.security.service.impl;

import cn.org.dianjiu.security.common.exception.BusinessException;
import cn.org.dianjiu.security.common.req.TTaskReq;
import cn.org.dianjiu.security.common.resp.TTaskResp;
import cn.org.dianjiu.security.common.util.HttpClientUtil;
import cn.org.dianjiu.security.common.util.ObjectUtils;
import cn.org.dianjiu.security.dao.TTaskDao;
import cn.org.dianjiu.security.dao.TTaskRecordsDao;
import cn.org.dianjiu.security.entity.TTaskRecords;
import cn.org.dianjiu.security.service.TTaskServiceI;
import cn.org.dianjiu.security.entity.TTask;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务信息表(TTask)表服务实现类
 *
 * @author makejava
 * @since 2020-06-17 23:42:10
 */
@Slf4j
@Service
public class TTaskServiceImpl implements TTaskServiceI {

    @Autowired
    private TTaskDao tTaskDao;

    @Autowired
    private TTaskRecordsDao taskRecordsDao;

    private AtomicInteger atomicInteger;

    @Override
    public TTaskResp getById(Long id) {
        TTaskResp tTaskResp = new TTaskResp();
        TTask tTask = tTaskDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTask)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            return null;
        }
        ObjectUtils.copyProperties(tTask,tTaskResp);
        return tTaskResp;
    }

    @Override
    public TTaskResp getByEntity(TTaskReq tTaskReq) {
      TTaskResp tTaskResp = new TTaskResp();
        TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            log.error("入参对象不能为空！");
            return null;
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        TTask tTask1 = tTaskDao.getByEntity(tTask);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTask1)){
            log.error("根据tTaskReq【"+tTaskReq+"】没有查到相关记录！");
            return null;
        }
        ObjectUtils.copyProperties(tTask1,tTaskResp);
        return tTaskResp;
    }

    @Override
    public List<TTaskResp> listByEntity(TTaskReq tTaskReq) {
        List<TTaskResp> list = new ArrayList<>();
        TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            log.error("入参对象不能为空！");
            return list;
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        List<TTask> tTasks = tTaskDao.listByEntity(tTask);
        if(null == tTasks || tTasks.isEmpty()){
            log.error("根据tTaskReq【"+tTaskReq+"】没有查到相关记录！");
            return list;
        }
        for (TTask tTask1 : tTasks ) {
            TTaskResp tTaskResp = new TTaskResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTask1)){
                log.error("根据tTaskReq【"+tTaskReq+"】没有查到相关记录！");
                return list;
            }
            ObjectUtils.copyProperties(tTask1,tTaskResp);
            list.add(tTaskResp);
        }
        return list;
    }

    @Override
    public List<TTaskResp> listByIds(List<Long> ids) {
      List<TTaskResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            return list;
        }
        List<TTask> tTasks  = tTaskDao.listByIds(ids);
        if(null != tTasks && !tTasks.isEmpty()){
            for (TTask tTask1 : tTasks ) {
                TTaskResp tTaskResp = new TTaskResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTask1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    return list;
                }
                ObjectUtils.copyProperties(tTask1,tTaskResp);
                list.add(tTaskResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskReq tTaskReq) {
      TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        Date date = new Date();
        tTask.setCreateTime(date);
        tTask.setUpdateTime(date);
        return tTaskDao.insert(tTask);
    }

    @Override
    public int insertBatch(List<TTaskReq> list) {
      List<TTask> tTasks = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            return 0;
        }
        for (TTaskReq tTaskReq : list) {
            TTask tTask = new TTask();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
                log.error("执行批量插入的集合为空！");
                return 0;
            }
            ObjectUtils.copyProperties(tTaskReq,tTask);
            tTasks.add(tTask);
        }
        return tTaskDao.insertBatch(tTasks);
    }

    @Override
    public int update(TTaskReq tTaskReq) {
      TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        tTask.setUpdateTime(new Date());
        return tTaskDao.update(tTask);
    }

    @Override
    public int updateBatch(List<TTaskReq> list) {
      List<TTask> tTasks = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            return 0;
        }
        for (TTaskReq tTaskReq : list) {
            TTask tTask = new TTask();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
                log.error("执行批量更新的集合为空！");
                return 0;
            }
            ObjectUtils.copyProperties(tTaskReq,tTask);
            tTasks.add(tTask);
        }
        return tTaskDao.updateBatch(tTasks);
    }

    @Override
    public int deleteById(Long id) {
        return tTaskDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskReq tTaskReq) {
      TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        return tTaskDao.deleteByEntity(tTask);
    }
  
    @Override
    public int deleteByIds(List<Long> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            return 0;
        }
        return tTaskDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskDao.countAll();
    }
    
    @Override
    public int countByEntity(TTaskReq tTaskReq) {
      TTask tTask = new TTask();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskReq)){
            new BusinessException("500", "入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskReq,tTask);
        return tTaskDao.countByEntity(tTask);
    }

    /**
     * 立即运行一次定时任务
     *
     * @param taskNo
     * @return
     */
    @Override
    public int runTaskRightNow(String taskNo) {
        atomicInteger = new AtomicInteger(0);
        TTask tTask = new TTask();
        tTask.setTaskNo(taskNo);
        TTask task = tTaskDao.getByEntity(tTask);
        if (ObjectUtils.checkObjAllFieldsIsNull(task)) {
            new BusinessException("500", "根据任务编码没查到任务信息，请联系管理员！");
        }
        Long id = task.getId();
        String taskName = task.getTaskName();
        String groupName = task.getGroupName();
        String taskDesc = task.getTaskDesc();
        String sendType = task.getSendType();
        String url = task.getSendUrl();
        String sendParam = task.getSendParam();
        log.info("定时任务被执行:taskNo={},executorNo={},sendType={},url={},executeParameter={}", taskNo, executorNo, sendType, url, executeParameter);
        QuartzTaskRecords records = null;
        try {
            //保存定时任务的执行记录
            TTaskRecords tTaskRecords = new TTaskRecords();
            tTaskRecords.setTaskNo(taskNo);
            tTaskRecords.setTaskName(taskName);
            tTaskRecords.setGroupName(groupName);
            tTaskRecords.setSendType(sendType);
            tTaskRecords.setSendUrl(url);
            tTaskRecords.setSendParam(sendParam);
            //TODO 执行时间待录入
            int insert = taskRecordsDao.insert(tTaskRecords);
            if (null == records || !ResultEnum.INIT.name().equals(records.getTaskstatus())) {
                logger.info("taskNo={}立即运行失--->>保存执行记录失败", taskNo);
                return ResultUtil.success(ResultEnum.RUN_NOW_FAIL.getCode(), ResultEnum.RUN_NOW_FAIL.getMessage());
            }

            if (ResultEnum.HTTP.getMessage().equals(sendType)) {
                try {
                    HttpClientUtil.doPost(url, "text/json", executeParameter);
                    logger.info("");
                } catch (Exception ex) {
                    logger.error("");
                    atomicInteger.incrementAndGet();
                    throw ex;
                }
            } else if (ResultEnum.KAFKA.getMessage().equals(sendType)) {
                try {
                    String message = new StringBuffer(taskNo).append(":").append(executeParameter).toString();
                    this.sendMessage(message);
                } catch (Exception ex) {
                    logger.error("");
                    atomicInteger.incrementAndGet();
                    throw ex;
                }
            }
        } catch (Exception ex) {
            logger.error("");
            atomicInteger.incrementAndGet();
            this.addTaskErrorRecord(records.getId().toString(), taskNo + ":" + ex.getMessage(), CommonUtil.getExceptionDetail(ex));
        }
        //更改record表的执行状态、最后修改时间、失败个数
        this.updateRecordById(atomicInteger.get(), records.getId());

        //更新taskinfo表的最后修改时间
        QuartzTaskInformations quartzTaskInformation = new QuartzTaskInformations();
        quartzTaskInformation.setId(id);
        quartzTaskInformation.setLastmodifytime(System.currentTimeMillis());
        this.updateTask(quartzTaskInformation);
        return ResultUtil.success(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

}
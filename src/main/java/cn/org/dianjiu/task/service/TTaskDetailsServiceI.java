package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.PageReq;
import cn.org.dianjiu.task.common.req.TTaskDetailsReq;
import cn.org.dianjiu.task.common.resp.PageResp;
import cn.org.dianjiu.task.common.resp.TTaskDetailsResp;
import com.github.pagehelper.Page;
import org.quartz.SchedulerException;

import java.util.List;

/**
 * 定时任务信息表(TTaskDetails)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TTaskDetailsServiceI {
   
    TTaskDetailsResp getById(Integer id);

    TTaskDetailsResp getByEntity(TTaskDetailsReq tTaskDetailsReq);

    List<TTaskDetailsResp> listByEntity(TTaskDetailsReq tTaskDetailsReq);

    List<TTaskDetailsResp> listByIds(List<Integer> ids);

    int insert(TTaskDetailsReq tTaskDetailsReq);

    int insertBatch(List<TTaskDetailsReq> list);

    int update(TTaskDetailsReq tTaskDetailsReq);

    int updateBatch(List<TTaskDetailsReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TTaskDetailsReq tTaskDetailsReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TTaskDetailsReq tTaskDetailsReq);

    int optionTask(Integer id);

    int runtask(Integer id);

    void initLoadOnlineTasks() throws SchedulerException;

    PageResp<TTaskDetailsResp> listByPage(PageReq<TTaskDetailsReq> pageReq);
}
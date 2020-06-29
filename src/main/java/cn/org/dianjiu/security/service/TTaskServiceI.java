package cn.org.dianjiu.security.service;

import cn.org.dianjiu.security.common.req.TTaskReq;
import cn.org.dianjiu.security.common.resp.TTaskResp;
import cn.org.dianjiu.security.dao.TTaskDao;

import java.util.List;

/**
 * 定时任务信息表(TTask)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 23:42:10
 */
public interface TTaskServiceI {

   
    TTaskResp getById(Long id);

    TTaskResp getByEntity(TTaskReq tTaskReq);

    List<TTaskResp> listByEntity(TTaskReq tTaskReq);

    List<TTaskResp> listByIds(List<Long> ids);

    int insert(TTaskReq tTaskReq);

    int insertBatch(List<TTaskReq> list);

    int update(TTaskReq tTaskReq);

    int updateBatch(List<TTaskReq> list);

    int deleteById(Long id);

    int deleteByEntity(TTaskReq tTaskReq);
  
    int deleteByIds(List<Long> list);
    
    int countAll();
    
    int countByEntity(TTaskReq tTaskReq);

    int runTaskRightNow(String taskNo);
}
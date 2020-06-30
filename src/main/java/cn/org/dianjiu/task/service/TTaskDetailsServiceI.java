package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TTaskDetailsReq;
import cn.org.dianjiu.task.common.resp.TTaskDetailsResp;
import cn.org.dianjiu.task.dao.TTaskDetailsDao;

import java.util.List;

/**
 * 定时任务信息表(TTaskDetails)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TTaskDetailsServiceI {
   
    TTaskDetailsResp getById(Long id);

    TTaskDetailsResp getByEntity(TTaskDetailsReq tTaskDetailsReq);

    List<TTaskDetailsResp> listByEntity(TTaskDetailsReq tTaskDetailsReq);

    List<TTaskDetailsResp> listByIds(List<Long> ids);

    int insert(TTaskDetailsReq tTaskDetailsReq);

    int insertBatch(List<TTaskDetailsReq> list);

    int update(TTaskDetailsReq tTaskDetailsReq);

    int updateBatch(List<TTaskDetailsReq> list);

    int deleteById(Long id);

    int deleteByEntity(TTaskDetailsReq tTaskDetailsReq);
  
    int deleteByIds(List<Long> list);
    
    int countAll();
    
    int countByEntity(TTaskDetailsReq tTaskDetailsReq);
}
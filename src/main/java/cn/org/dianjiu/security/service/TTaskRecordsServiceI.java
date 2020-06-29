package cn.org.dianjiu.security.service;

import cn.org.dianjiu.security.common.req.TTaskRecordsReq;
import cn.org.dianjiu.security.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.security.dao.TTaskRecordsDao;

import java.util.List;

/**
 * 定时任务执行情况记录表(TTaskRecords)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
public interface TTaskRecordsServiceI {

    TTaskRecordsResp getById(Long id);

    TTaskRecordsResp getByEntity(TTaskRecordsReq tTaskRecordsReq);

    List<TTaskRecordsResp> listByEntity(TTaskRecordsReq tTaskRecordsReq);

    List<TTaskRecordsResp> listByIds(List<Long> ids);

    int insert(TTaskRecordsReq tTaskRecordsReq);

    int insertBatch(List<TTaskRecordsReq> list);

    int update(TTaskRecordsReq tTaskRecordsReq);

    int updateBatch(List<TTaskRecordsReq> list);

    int deleteById(Long id);

    int deleteByEntity(TTaskRecordsReq tTaskRecordsReq);
  
    int deleteByIds(List<Long> list);
    
    int countAll();
    
    int countByEntity(TTaskRecordsReq tTaskRecordsReq);
}
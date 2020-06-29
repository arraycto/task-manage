package cn.org.dianjiu.security.service;

import cn.org.dianjiu.security.common.req.TTaskErrorsReq;
import cn.org.dianjiu.security.common.resp.TTaskErrorsResp;
import cn.org.dianjiu.security.dao.TTaskErrorsDao;

import java.util.List;

/**
 * 定时任务出错现场信息表(TTaskErrors)表服务接口
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
public interface TTaskErrorsServiceI {

    TTaskErrorsResp getById(Long id);

    TTaskErrorsResp getByEntity(TTaskErrorsReq tTaskErrorsReq);

    List<TTaskErrorsResp> listByEntity(TTaskErrorsReq tTaskErrorsReq);

    List<TTaskErrorsResp> listByIds(List<Long> ids);

    int insert(TTaskErrorsReq tTaskErrorsReq);

    int insertBatch(List<TTaskErrorsReq> list);

    int update(TTaskErrorsReq tTaskErrorsReq);

    int updateBatch(List<TTaskErrorsReq> list);

    int deleteById(Long id);

    int deleteByEntity(TTaskErrorsReq tTaskErrorsReq);
  
    int deleteByIds(List<Long> list);
    
    int countAll();
    
    int countByEntity(TTaskErrorsReq tTaskErrorsReq);
}
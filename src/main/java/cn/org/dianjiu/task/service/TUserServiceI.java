package cn.org.dianjiu.task.service;

import cn.org.dianjiu.task.common.req.TUserReq;
import cn.org.dianjiu.task.common.resp.TUserResp;
import cn.org.dianjiu.task.dao.TUserDao;

import java.util.List;

/**
 * (TUser)表服务接口
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
public interface TUserServiceI {
   
    TUserResp getById(Integer id);

    TUserResp getByEntity(TUserReq tUserReq);

    List<TUserResp> listByEntity(TUserReq tUserReq);

    List<TUserResp> listByIds(List<Integer> ids);

    int insert(TUserReq tUserReq);

    int insertBatch(List<TUserReq> list);

    int update(TUserReq tUserReq);

    int updateBatch(List<TUserReq> list);

    int deleteById(Integer id);

    int deleteByEntity(TUserReq tUserReq);
  
    int deleteByIds(List<Integer> list);
    
    int countAll();
    
    int countByEntity(TUserReq tUserReq);
}
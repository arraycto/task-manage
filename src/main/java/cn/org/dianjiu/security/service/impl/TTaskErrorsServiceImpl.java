package cn.org.dianjiu.security.service.impl;

import cn.org.dianjiu.security.common.req.TTaskErrorsReq;
import cn.org.dianjiu.security.common.resp.TTaskErrorsResp;
import cn.org.dianjiu.security.common.util.ObjectUtils;
import cn.org.dianjiu.security.dao.TTaskErrorsDao;
import cn.org.dianjiu.security.service.TTaskErrorsServiceI;
import cn.org.dianjiu.security.entity.TTaskErrors;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务出错现场信息表(TTaskErrors)表服务实现类
 *
 * @author makejava
 * @since 2020-06-17 23:42:12
 */
@Slf4j
@Service
public class TTaskErrorsServiceImpl implements TTaskErrorsServiceI {

    @Autowired
    private TTaskErrorsDao tTaskErrorsDao;

    @Override
    public TTaskErrorsResp getById(Long id) {
        TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
        TTaskErrors tTaskErrors = tTaskErrorsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            return null;
        }
        ObjectUtils.copyProperties(tTaskErrors,tTaskErrorsResp);
        return tTaskErrorsResp;
    }

    @Override
    public TTaskErrorsResp getByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
        TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return null;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        TTaskErrors tTaskErrors1 = tTaskErrorsDao.getByEntity(tTaskErrors);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
            log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
            return null;
        }
        ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
        return tTaskErrorsResp;
    }

    @Override
    public List<TTaskErrorsResp> listByEntity(TTaskErrorsReq tTaskErrorsReq) {
        List<TTaskErrorsResp> list = new ArrayList<>();
        TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return list;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        List<TTaskErrors> tTaskErrorss = tTaskErrorsDao.listByEntity(tTaskErrors);
        if(null == tTaskErrorss || tTaskErrorss.isEmpty()){
            log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
            return list;
        }
        for (TTaskErrors tTaskErrors1 : tTaskErrorss ) {
            TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
                log.error("根据tTaskErrorsReq【"+tTaskErrorsReq+"】没有查到相关记录！");
                return list;
            }
            ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
            list.add(tTaskErrorsResp);
        }
        return list;
    }

    @Override
    public List<TTaskErrorsResp> listByIds(List<Long> ids) {
      List<TTaskErrorsResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            return list;
        }
        List<TTaskErrors> tTaskErrorss  = tTaskErrorsDao.listByIds(ids);
        if(null != tTaskErrorss && !tTaskErrorss.isEmpty()){
            for (TTaskErrors tTaskErrors1 : tTaskErrorss ) {
                TTaskErrorsResp tTaskErrorsResp = new TTaskErrorsResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrors1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    return list;
                }
                ObjectUtils.copyProperties(tTaskErrors1,tTaskErrorsResp);
                list.add(tTaskErrorsResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        Date date = new Date();
        tTaskErrors.setCreatetime(date);
        tTaskErrors.setUpdateTime(date);
        return tTaskErrorsDao.insert(tTaskErrors);
    }

    @Override
    public int insertBatch(List<TTaskErrorsReq> list) {
      List<TTaskErrors> tTaskErrorss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            return 0;
        }
        for (TTaskErrorsReq tTaskErrorsReq : list) {
            TTaskErrors tTaskErrors = new TTaskErrors();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
                log.error("执行批量插入的集合为空！");
                return 0;
            }
            ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
            tTaskErrorss.add(tTaskErrors);
        }
        return tTaskErrorsDao.insertBatch(tTaskErrorss);
    }

    @Override
    public int update(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        tTaskErrors.setUpdateTime(new Date());
        return tTaskErrorsDao.update(tTaskErrors);
    }

    @Override
    public int updateBatch(List<TTaskErrorsReq> list) {
      List<TTaskErrors> tTaskErrorss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            return 0;
        }
        for (TTaskErrorsReq tTaskErrorsReq : list) {
            TTaskErrors tTaskErrors = new TTaskErrors();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
                log.error("执行批量更新的集合为空！");
                return 0;
            }
            ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
            tTaskErrorss.add(tTaskErrors);
        }
        return tTaskErrorsDao.updateBatch(tTaskErrorss);
    }

    @Override
    public int deleteById(Long id) {
        return tTaskErrorsDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        return tTaskErrorsDao.deleteByEntity(tTaskErrors);
    }
  
    @Override
    public int deleteByIds(List<Long> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            return 0;
        }
        return tTaskErrorsDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskErrorsDao.countAll();
    }
    
    @Override
    public int countByEntity(TTaskErrorsReq tTaskErrorsReq) {
      TTaskErrors tTaskErrors = new TTaskErrors();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskErrorsReq)){
            log.error("入参对象不能为空！");
            return 0;
        }
        ObjectUtils.copyProperties(tTaskErrorsReq,tTaskErrors);
        return tTaskErrorsDao.countByEntity(tTaskErrors);
    }

}
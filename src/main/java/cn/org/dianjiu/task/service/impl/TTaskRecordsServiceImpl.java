package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.common.exception.BusinessException;
import cn.org.dianjiu.task.common.req.TTaskRecordsReq;
import cn.org.dianjiu.task.common.resp.TTaskRecordsResp;
import cn.org.dianjiu.task.common.util.ObjectUtils;
import cn.org.dianjiu.task.dao.TTaskRecordsDao;
import cn.org.dianjiu.task.service.TTaskRecordsServiceI;
import cn.org.dianjiu.task.entity.TTaskRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 定时任务执行情况记录表(TTaskRecords)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TTaskRecordsServiceImpl implements TTaskRecordsServiceI {

    @Autowired
    private TTaskRecordsDao tTaskRecordsDao;

    @Override
    public TTaskRecordsResp getById(Long id) {
        TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
        TTaskRecords tTaskRecords = tTaskRecordsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskRecords,tTaskRecordsResp);
        return tTaskRecordsResp;
    }

    @Override
    public TTaskRecordsResp getByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
        TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        TTaskRecords tTaskRecords1 = tTaskRecordsDao.getByEntity(tTaskRecords);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
            log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
        return tTaskRecordsResp;
    }

    @Override
    public List<TTaskRecordsResp> listByEntity(TTaskRecordsReq tTaskRecordsReq) {
        List<TTaskRecordsResp> list = new ArrayList<>();
        TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        List<TTaskRecords> tTaskRecordss = tTaskRecordsDao.listByEntity(tTaskRecords);
        if(null == tTaskRecordss || tTaskRecordss.isEmpty()){
            log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
        }
        for (TTaskRecords tTaskRecords1 : tTaskRecordss ) {
            TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
                log.error("根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
                new BusinessException("400","根据tTaskRecordsReq【"+tTaskRecordsReq+"】没有查到相关记录！");
            }
            ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
            list.add(tTaskRecordsResp);
        }
        return list;
    }

    @Override
    public List<TTaskRecordsResp> listByIds(List<Long> ids) {
      List<TTaskRecordsResp> list = new ArrayList<>();
        if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            new BusinessException("400","id集合不能为空！");
        }
        List<TTaskRecords> tTaskRecordss  = tTaskRecordsDao.listByIds(ids);
        if(null != tTaskRecordss && !tTaskRecordss.isEmpty()){
            for (TTaskRecords tTaskRecords1 : tTaskRecordss ) {
                TTaskRecordsResp tTaskRecordsResp = new TTaskRecordsResp();
                if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecords1)){
                    log.error("根据ids【"+ids.toString()+"】没有查到相关记录！");
                    new BusinessException("400","根据ids【"+ids.toString()+"】没有查到相关记录！");
                }
                ObjectUtils.copyProperties(tTaskRecords1,tTaskRecordsResp);
                list.add(tTaskRecordsResp);
            }
        }
        return list;
    }

    @Override
    public int insert(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        Date date = new Date();
        tTaskRecords.setCreateTime(date);
        tTaskRecords.setUpdateTime(date);
        return tTaskRecordsDao.insert(tTaskRecords);
    }

    @Override
    public int insertBatch(List<TTaskRecordsReq> list) {
      List<TTaskRecords> tTaskRecordss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量插入的集合为空！");
            new BusinessException("400","执行批量插入的集合为空！");
        }
        for (TTaskRecordsReq tTaskRecordsReq : list) {
            TTaskRecords tTaskRecords = new TTaskRecords();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
                log.error("执行批量插入的集合为空！");
                new BusinessException("400","执行批量插入的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
            tTaskRecordss.add(tTaskRecords);
        }
        return tTaskRecordsDao.insertBatch(tTaskRecordss);
    }

    @Override
    public int update(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        tTaskRecords.setUpdateTime(new Date());
        return tTaskRecordsDao.update(tTaskRecords);
    }

    @Override
    public int updateBatch(List<TTaskRecordsReq> list) {
      List<TTaskRecords> tTaskRecordss = new ArrayList<>();
        if(null == list || list.isEmpty()){
            log.error("执行批量更新的集合为空！");
            new BusinessException("400","执行批量更新的集合为空！");
        }
        for (TTaskRecordsReq tTaskRecordsReq : list) {
            TTaskRecords tTaskRecords = new TTaskRecords();
            if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
                log.error("执行批量更新的集合为空！");
                new BusinessException("400","执行批量更新的集合为空！");
            }
            ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
            tTaskRecordss.add(tTaskRecords);
        }
        return tTaskRecordsDao.updateBatch(tTaskRecordss);
    }

    @Override
    public int deleteById(Long id) {
        return tTaskRecordsDao.deleteById(id);
    }

    @Override
    public int deleteByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        return tTaskRecordsDao.deleteByEntity(tTaskRecords);
    }

    @Override
    public int deleteByIds(List<Long> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            new BusinessException("400","id集合不能为空！");
        }
        return tTaskRecordsDao.deleteByIds(ids);
    }

    @Override
    public int countAll() {
        return tTaskRecordsDao.countAll();
    }

    @Override
    public int countByEntity(TTaskRecordsReq tTaskRecordsReq) {
      TTaskRecords tTaskRecords = new TTaskRecords();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskRecordsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskRecordsReq,tTaskRecords);
        return tTaskRecordsDao.countByEntity(tTaskRecords);
    }

}
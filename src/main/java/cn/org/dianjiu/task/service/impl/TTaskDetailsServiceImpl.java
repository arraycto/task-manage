package cn.org.dianjiu.task.service.impl;

import cn.org.dianjiu.task.dao.TTaskDetailsDao;
import cn.org.dianjiu.task.service.TTaskDetailsServiceI;
import cn.org.dianjiu.task.entity.TTaskDetails;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;

/**
 * 定时任务信息表(TTaskDetails)表服务实现类
 *
 * @author dianjiu
 * @since 2020-07-01 00:07:34
 */
@Slf4j
@Service
public class TTaskDetailsServiceImpl implements TTaskDetailsServiceI {

    @Autowired
    private TTaskDetailsDao tTaskDetailsDao;

    public TTaskDetailsResp getById(Long id) {
        TTaskDetailsResp tTaskDetailsResp = new TTaskDetailsResp();
        TTaskDetails tTaskDetails = tTaskDetailsDao.getById(id);
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetails)){
            log.error("根据id【"+id+"】没有查到相关记录！");
            new BusinessException("400","根据id【"+id+"】没有查到相关记录！");
        }
        ObjectUtils.copyProperties(tTaskDetails,tTaskDetailsResp);
        return tTaskDetailsResp;
    }

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

    public List<TTaskDetailsResp> listByIds(List<Long> ids) {
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

    public int insert(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        Date date = new Date();
        tTaskDetails.setCreateTime(date);
        tTaskDetails.setUpdateTime(date);
        return tTaskDetailsDao.insert(tTaskDetails);
    }

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
            tTaskDetailss.add(tTaskDetails);
        }
        return tTaskDetailsDao.insertBatch(tTaskDetailss);
    }

    public int update(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        tTaskDetails.setUpdateTime(new Date());
        return tTaskDetailsDao.update(tTaskDetails);
    }

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
            tTaskDetailss.add(tTaskDetails);
        }
        return tTaskDetailsDao.updateBatch(tTaskDetailss);
    }

    public int deleteById(Long id) {
        return tTaskDetailsDao.deleteById(id);
    }

    public int deleteByEntity(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        return tTaskDetailsDao.deleteByEntity(tTaskDetails);
    }
  
    public int deleteByIds(List<Long> ids) {
      if(null == ids || ids.isEmpty()){
            log.error("id集合不能为空！");
            new BusinessException("400","id集合不能为空！");
        }
        return tTaskDetailsDao.deleteByIds(ids);
    }

    public int countAll() {
        return tTaskDetailsDao.countAll();
    }
    
    public int countByEntity(TTaskDetailsReq tTaskDetailsReq) {
      TTaskDetails tTaskDetails = new TTaskDetails();
        if(ObjectUtils.checkObjAllFieldsIsNull(tTaskDetailsReq)){
            log.error("入参对象不能为空！");
            new BusinessException("400","入参对象不能为空！");
        }
        ObjectUtils.copyProperties(tTaskDetailsReq,tTaskDetails);
        return tTaskDetailsDao.countByEntity(tTaskDetails);
    }

}
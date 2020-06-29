package cn.org.dianjiu.security.dao;

import cn.org.dianjiu.security.entity.TTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import javax.validation.constraints.*;
import java.util.List;

/**
 * 定时任务信息表(TTask)表数据库访问层
 *
 * @author makejava
 * @since 2020-06-17 23:42:10
 */
@Mapper
public interface TTaskDao {
    
    /**
     * 通过Id查询单个对象
     *
     * @param id 主键
     * @return 实例对象
     */
    TTask getById(@NotNull Long id);
    
    /**
     * 通过实体不为空的属性作为筛选条件查询对象列表
     *
     * @param tTask 实例对象
     * @return 对象列表
     */
    List<TTask> listByEntity(TTask tTask);

    /**
     * 通过实体不为空的属性作为筛选条件查询单个对象
     *
     * @param tTask
     * @return 实例对象
     */
    TTask getByEntity(TTask tTask);

    /**
     * 通过Id列表作为筛选条件查询对象列表，列表长度不为0
     *
     * @param list 实例对象
     * @return 对象列表
     */
    List<TTask> listByIds(@NotEmpty List<Long> list);

    /**
     * 新增实体属性不为null的记录
     *
     * @param tTask 实例对象
     * @return 影响行数
     */
    int insert(@NotNull TTask tTask);

    /**
     * 批量新增所有列，列表长度不能为0，且列表id统一为null或者统一不为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int insertBatch(@NotEmpty List<TTask> list);
    
    /**
     * 通过主键修改实体属性不为null的列
     *
     * @param tTask 实例对象
     * @return 影响行数
     */
    int update(@NotNull TTask tTask);
    
    /**
     * 通过表字段修改实体属性不为null的列
     *
     * @param where 实例对象
     * @param set 实例对象
     * @return 影响行数
     */
    int updateByField(@NotNull @Param("where") TTask where, @NotNull @Param("set") TTask set);
    
    /**
     * 通过主键修改实体列表，列表长度不能为0，注意：当实体属性为null时，对应的列也会别更新为null
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int updateBatch(@NotEmpty List<TTask> list);
    
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@NotNull Long id);
    
    /**
     * 通过实体非空属性删除
     *
     * @param tTask 实例对象
     * @return 影响行数
     */
    int deleteByEntity(@NotNull TTask tTask);
    
    /**
     * 通过主键列表删除，列表长度不能为0
     *
     * @param list 实例对象
     * @return 影响行数
     */
    int deleteByIds(@NotEmpty List<Long> list);
    
    int countAll();
    
    int countByEntity(TTask tTask);
    
}
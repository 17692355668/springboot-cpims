package com.javakc.springbootcpims.common.base.service;

import com.javakc.springbootcpims.common.base.dao.BaseDao;
import com.javakc.springbootcpims.common.base.entity.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @program:springboot-cpims
 * @description:封装逻辑层实现
 * @create:2020-10-23
 */

@Transactional(
        readOnly = true
//        isolation = Isolation.READ_COMMITTED,
//        propagation = Propagation.REQUIRED

)
public abstract class BaseService<D extends BaseDao,T extends Base>  {

    @Autowired
    private D dao;


    /**
     * 单条添加
     * @param entity 实体类
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int insert(T entity)
    {
        return dao.insert(entity);
    }

    /**
     * 批量添加
     * @param list 实体类集合
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int inserts(List<T> list)
    {
        return dao.inserts(list);
    }

    /**
     * 单挑修改
     * @param entity 实体类
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int update(T entity)
    {
        return dao.update(entity);
    }
    /**
     * 根据条件修改
     * @param params 动态条件
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int updateByParams(Map<String,Object> params)
    {
        return dao.updateByParams(params);
    }

    /**
     * 根据主键单条删除
     * @param id 主键
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int delete(java.io.Serializable id)
    {
        return dao.delete(id);
    }

    /**
     * 根据主键批量删除
     * @param ids 主键集合
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int deletes(List<Serializable> ids)
    {
        return dao.deletes(ids);
    }

    /**
     * 根据动态条件删除数据
     * @param params 动态条件
     * @return 条数
     */
    @Transactional(readOnly = false)

    public int deleteByParams(Map<String,Object> params)
    {
        return dao.deleteByParams(params);
    }


    /**
     * 清空当前库
     * @return 条数
     */
    @Deprecated
    @Transactional(readOnly = false)
    public int deleteAll()
    {
        return dao.deleteAll();
    }

    /**
     * 根据主键查询单个对象
     * @param id 主键ID
     * @return 对象
     */
    public T queryById(Serializable id)
    {
        return (T)dao.queryById(id);
    }

    /**
     * 根据查询条件返回单个对象
     * @param params 动态查询条件
     * @return 对象
     */
    public T queryEntityByParams(Map<String,Object> params)
    {
        return (T)dao.queryEntityByParams(params);
    }

    /**
     * 查询全部数据
     * @return 全部数据
     */
    @Deprecated
    public List<T> queryAll()
    {
        return dao.queryAll();
    }

    /**
     * 根据条件查询集合
     * @param params 动态集合
     * @return 结果
     */
    public List<T> queryByParams(Map<String,Object> params)
    {
        return dao.queryByParams(params);
    }

    /**
     * 根据条件查询总数
     * @param params 动态集合
     * @return 结果
     */
    public long queryContByParams(Map<String,Object> params)
    {
        return dao.queryContByParams(params);
    }

}

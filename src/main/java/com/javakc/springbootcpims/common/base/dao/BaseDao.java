package com.javakc.springbootcpims.common.base.dao;

import java.util.List;
import java.util.Map;

/**
 *提供所有模块公共的抽象方法
 * @param <T> 当前模块实体类
 * @param <ID> 当前模块主键类型
 */
public interface BaseDao<T,ID> {

    /**
     * 单条添加
     * @param entity 实体类
     * @return 条数
     */
    int insert(T entity);

    /**
     * 批量添加
     * @param list 实体类集合
     * @return 条数
     */
    int inserts(List<T> list);

    /**
     * 单挑修改
     * @param entity 实体类
     * @return 条数
     */
    int update(T entity);

    /**
     * 根据条件修改
     * @param params 动态条件
     * @return 条数
     */
    int updateByParams(Map<String,Object> params);

    /**
     * 根据主键单条删除
     * @param id 主键
     * @return 条数
     */
    int delete(ID id);

    /**
     * 根据主键批量删除
     * @param ids 主键集合
     * @return 条数
     */
    int deletes(List<ID> ids);

    /**
     * 根据动态条件删除数据
     * @param params 动态条件
     * @return 条数
     */
    int deleteByParams(Map<String,Object> params);

    /**
     * 清空当前库
     * @return 条数
     */
    @Deprecated
    int deleteAll();

    /**
     * 根据主键查询单个对象
     * @param id 主键ID
     * @return 对象
     */
    T queryById(ID id);

    /**
     * 根据查询条件返回单个对象
     * @param params 动态查询条件
     * @return 对象
     */
    T queryEntityByParams(Map<String,Object> params);

    /**
     * 查询全部数据
     * @return 全部数据
     */
    @Deprecated
    List<T> queryAll();

    /**
     * 根据条件产线集合
     * @param params 动态集合
     * @return 结果
     */
    List<T> queryByParams(Map<String,Object> params);

    long queryContByParams(Map<String,Object> params);



}

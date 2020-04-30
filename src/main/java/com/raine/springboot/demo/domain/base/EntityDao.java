package com.raine.springboot.demo.domain.base;

import java.io.Serializable;
import java.util.List;

public interface EntityDao<E extends BaseEntity, PK extends Serializable> {
    /**
     * 查询列表
     *
     * @param objQuery 查询类
     * @return 列表
     */
    List<E> search(Object objQuery);

    /**
     * 新增
     *
     * @param obj 新增参数对象
     * @return 影响条数
     */
    int save(Object obj);

    /**
     * 根据ID修改
     *
     * @param obj 要修改的参数对象
     * @return 影响条数
     */
    int updatePart(Object obj);

    /**
     * 根据条件查询单个
     *
     * @param objQuery 查询实体
     * @return 符合条件的对象
     */
    E searchOne(Object objQuery);

    /***
     * 根据ID获取对象
     * @param id 唯一ID
     * @return 符合条件的对象
     */
    E getById(Integer id);

    /***
     * 根据ID删除
     * @param id 唯一ID
     * @return 影响条数
     */
    int deleteByPk(Integer id);

    /***
     * 批量删除
     * @param ids ID集合
     * @return 影响条数
     */
    int deleteBatchByPks(List<Integer> ids);
}

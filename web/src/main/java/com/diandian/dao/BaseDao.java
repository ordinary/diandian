/**
 * NewHeight.com Inc.
 * Copyright (c) 2008-2012 All Rights Reserved.
 */
package com.diandian.dao;

import com.diandian.domain.BaseEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

/**
 * <pre>
 * baseDaoImpl
 * </pre>
 *
 * @author chengjun
 * @version $Id: BaseDaoImpl.java, v 0.1 2011-12-11 下午07:52:19 chengjun Exp $
 */
public abstract class BaseDao<T extends BaseEntity> extends SqlSessionDaoSupport {

    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * 数据库基本操作，对应*mapper.xml中的id
     */
    private static final String INSERT = ".insert";
    private static final String DELETE = ".delete";
    private static final String UPDATE = ".update";
    private static final String GET = ".get";
    private static final String EXISTS = ".exists";

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
       super.setSqlSessionFactory(sqlSessionFactory);
    }


    public T insert(T object) {
        this.getSqlSession().insert(getIbatisMapperNameSpace() + INSERT, object);
        return object;
    }


    public void deleteById(Long id) {
        this.getSqlSession().update(getIbatisMapperNameSpace() + DELETE, id);
    }


    public void update(T dto) {
        getSqlSession().update(getIbatisMapperNameSpace() + UPDATE, dto);
    }


    @SuppressWarnings("unchecked")
    public T get(Long id) {
        return (T) getSqlSession().selectOne(getIbatisMapperNameSpace() + GET, id);
    }


    public boolean exists(Long id) {
        return get(id) != null ? true : false;
    }

    public boolean exists(Map<String, Object> map) {
        Object count = getSqlSession().selectOne(getIbatisMapperNameSpace() + EXISTS, map);
        int totalCount = Integer.parseInt(count.toString());
        return totalCount > 0 ? true : false;
    }


    /**
     * <pre>
     * 获取执行mapperNameSpace
     * </pre>
     *
     * @return
     */

    public String getIbatisMapperNameSpace() {
        Class<T> clazz = getEntityClass();
        return clazz.getName()+"Mapper";
    }


    @SuppressWarnings("unchecked")
    public Class<T> getEntityClass() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) pt.getActualTypeArguments()[0];
    }

}

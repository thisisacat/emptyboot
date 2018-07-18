package cn.notacat.cat.biz.base.tkmybatis;


import cn.notacat.cat.dal.base.tkmybatis.Page;

import java.util.List;

/****************************************
 * @@CREATE : 2018-02-02 上午12:10
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :
 * @@VERSION :
 *
 *****************************************/
public interface IService<T> {

    T selectByPrimaryKey(Object key);

    List<T> selectEntity(T entity);

    List<T> selectByExample(Object example);

    List<T> selectAll();

    int selectCountByExample(Object example);

    int selectCount(T entity);

    T selectOne(T entity);

    int save(T entity);

    int saveList(List<T> entityList);

    int saveNotNull(T entity);

    int delete(T entity);

    int deleteByPrimaryKey(Object key);

    int deleteByExample(Object example);

    int updateByPrimaryKey(T entity);

    int updateNotNull(T entity);

    int updateByExample(T entity,Object example);

    int updateByExampleNotNull(T entity,Object example);

    Page<T> selectPageByObject(Object example, int start, int pageSize, String orderBy);

    Page<T> selectPageByExample(Object example, int start,int pageSize,String orderBy);

}

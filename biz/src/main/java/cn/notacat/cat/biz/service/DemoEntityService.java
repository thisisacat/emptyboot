package cn.notacat.cat.biz.service;

import cn.notacat.cat.biz.base.tkmybatis.IService;
import cn.notacat.cat.dal.dao.entity.demo.DemoEntity;

/****************************************
 * @@CREATE : 2018-07-18 下午12:04
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
public interface DemoEntityService extends IService<DemoEntity> {

    DemoEntity queryDemoById(Long id);

}

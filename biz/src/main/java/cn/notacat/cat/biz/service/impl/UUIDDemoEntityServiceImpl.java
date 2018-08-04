package cn.notacat.cat.biz.service.impl;

import cn.notacat.cat.biz.base.tkmybatis.IServiceImpl;
import cn.notacat.cat.biz.service.UUIDDemoEntityService;
import cn.notacat.cat.dal.dao.entity.demo.UUIDDemoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/****************************************
 * @@CREATE : 2018-08-04 下午7:32
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Service("uuIDDemoEntityService")
public class UUIDDemoEntityServiceImpl extends IServiceImpl<UUIDDemoEntity> implements UUIDDemoEntityService {
    private final static Logger logger = LoggerFactory.getLogger(UUIDDemoEntityServiceImpl.class);


}

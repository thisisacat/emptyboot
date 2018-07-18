package cn.notacat.cat.biz.service.impl;

import cn.notacat.cat.biz.base.tkmybatis.IServiceImpl;
import cn.notacat.cat.biz.service.DemoEntityService;
import cn.notacat.cat.dal.dao.entity.demo.DemoEntity;
import cn.notacat.cat.dal.dao.mapper.demo.DemoEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/****************************************
 * @@CREATE : 2018-07-18 下午12:04
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Service("demoService")
public class DemoEntityServiceImpl extends IServiceImpl<DemoEntity> implements DemoEntityService {
    private final static Logger logger = LoggerFactory.getLogger(DemoEntityServiceImpl.class);

    @Autowired
    private DemoEntityMapper demoEntityMapper;

    @Transactional
    @Override
    public DemoEntity queryDemoById(Long id){
        DemoEntity query = new DemoEntity();
        query.setId(1L);
        DemoEntity entity = demoEntityMapper.selectOne(query);
        return entity;
    }
}

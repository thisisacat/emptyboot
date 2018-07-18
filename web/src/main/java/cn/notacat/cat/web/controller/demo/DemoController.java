package cn.notacat.cat.web.controller.demo;

import cn.notacat.cat.biz.constant.PageConstant;
import cn.notacat.cat.biz.service.DemoEntityService;
import cn.notacat.cat.dal.base.tkmybatis.Page;
import cn.notacat.cat.dal.dao.entity.demo.DemoEntity;
import cn.notacat.cat.web.controller.BaseController;
import cn.notacat.cat.web.controller.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/****************************************
 * @@CREATE : 2018-07-17 下午12:19
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private DemoEntityService demoEntityService;

    /**
     * selectEntity
     *
     * @return
     */
    @RequestMapping("selectEntity")
    @ResponseBody
    public Response<DemoEntity> selectEntity()  {
        DemoEntity entity1 = demoEntityService.queryDemoById(1L);
        DemoEntity query = new DemoEntity();
        query.setId(1L);
        DemoEntity entity2 = demoEntityService.selectOne(query);
        return Response.success(entity1);
    }

    /**
     * selectByExample
     *
     * @return
     */
    @RequestMapping("selectByExample")
    @ResponseBody
    public Response<DemoEntity> selectByExample() {
        // 或则写在service层用mapper操作
        Example example = new Example(DemoEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id", "1");
        List<DemoEntity> list = demoEntityService.selectByExample(example);
        return Response.success(list.get(0));
    }


    /**
     * selectPage
     *
     * @return
     */
    @RequestMapping("selectPage")
    @ResponseBody
    public Response<Page<DemoEntity>> selectPageByObject(@RequestParam(required = false, defaultValue = PageConstant.PAGE_NO_ONE_STR) Integer pageNo,
                                                         @RequestParam(required = false, defaultValue = PageConstant.PAGE_DEFAULT_SIZE_STR) Integer pageSize) {
        // 或则写在service层用mapper操作
        Page<DemoEntity> page1 = demoEntityService.selectPageByObject(new DemoEntity(), pageNo, pageSize, "");
        Example example = new Example(DemoEntity.class);
        example.orderBy("id").asc();

        Page<DemoEntity> page2 = demoEntityService.selectPageByExample(example,pageNo, pageSize,"");
        return Response.success(page1);
    }


    
}

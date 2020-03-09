package cn.notacat.cat.web.controller.demo;

import cn.notacat.cat.biz.constant.PageConstant;
import cn.notacat.cat.biz.service.DemoEntityService;
import cn.notacat.cat.biz.service.UUIDDemoEntityService;
import cn.notacat.cat.dal.base.tkmybatis.Page;
import cn.notacat.cat.dal.dao.entity.demo.DemoEntity;
import cn.notacat.cat.dal.dao.entity.demo.UUIDDemoEntity;
import cn.notacat.cat.web.controller.BaseController;
import cn.notacat.cat.web.controller.Response;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
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
@Api(value = "demoAPI")
public class DemoController extends BaseController{
    private final static Logger logger = LoggerFactory.getLogger(DemoController.class);


    @Autowired
    private DemoEntityService demoEntityService;
    @Autowired
    private UUIDDemoEntityService uuIDDemoEntityService;

    /**
     * selectEntity
     *
     * @return
     */
    @ApiOperation(value = "selectEntity")
    @ResponseBody
    @GetMapping("selectEntity")
    public Response<DemoEntity> selectEntity(@RequestParam String id)  {
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
    @ApiOperation(value = "selectByExample")
    @ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "id", paramType = "query",required = true) })
    @ResponseBody
    @GetMapping("selectByExample")
    public Response<DemoEntity> selectByExample(String id) {
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
    @ApiOperation(value = "selectPageByObject")
    @GetMapping("selectPage")
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




    @PostMapping("test1")
    @ResponseBody
    public Response<DemoEntity> test1() {
        /***********************查询*****************/
        PageHelper.startPage(-1,10);
        List<DemoEntity> list = demoEntityService.selectAll();
        DemoEntity demo1 = demoEntityService.selectByPrimaryKey(1L);

        DemoEntity query = new DemoEntity();
        query.setId(1L);
        List<DemoEntity> list2 = demoEntityService.selectEntity(query);

        DemoEntity query2 = new DemoEntity();
        query2.setId(1L);
        DemoEntity demo2 = demoEntityService.selectOne(query2);

        DemoEntity query3 = new DemoEntity();
        query3.setId(1L);
        int count = demoEntityService.selectCount(query3);

        Example example = new Example(DemoEntity.class);
        example.createCriteria().andEqualTo("id",1L);
        List<DemoEntity> list3 = demoEntityService.selectByExample(example);

        demoEntityService.selectCountByExample(example);

        Page<DemoEntity> page1 = demoEntityService.selectPageByObject(new DemoEntity(),0,2,"id desc");

        Example exampl2 = new Example(DemoEntity.class);
        exampl2.createCriteria().andEqualTo("name","name1");
        Page<DemoEntity> page2 = demoEntityService.selectPageByExample(exampl2,-1,0,"id desc");

        return null;
    }


    @PostMapping("test2")
    @ResponseBody
    public String test2() {
        /***********************新增*****************/
        DemoEntity demo1 = new DemoEntity();
        demo1.setName("demo1");
        demoEntityService.save(demo1);

        DemoEntity demo2 = new DemoEntity();
        demo2.setName("demo2");
        demoEntityService.saveNotNull(demo2);


        List<DemoEntity> list = new ArrayList<>();
        DemoEntity demo3 = new DemoEntity();
        demo3.setName("demo3");
        DemoEntity demo4 = new DemoEntity();
        demo4.setName("demo4");
        list.add(demo3);
        list.add(demo4);

        demoEntityService.saveList(list);

        return null;
    }

    @PostMapping("test3")
    @ResponseBody
    public String test3() {
        /***********************修改*****************/
        DemoEntity demo1 = new DemoEntity();
        demo1.setId(1L);
        demo1.setName("demo1");
        demoEntityService.updateByPrimaryKey(demo1);


        demoEntityService.updateNotNull(demo1);

        Example example = new Example(DemoEntity.class);
        example.createCriteria().andEqualTo("id",1L);

        demoEntityService.updateByExample(demo1,example);

        demoEntityService.updateByExampleNotNull(demo1,example);

        return null;
    }

    @PostMapping("test4")
    @ResponseBody
    public String test4() {
        /***********************删除*****************/
        demoEntityService.deleteByPrimaryKey(1L);

        DemoEntity demo1 =  new DemoEntity();
        demo1.setId(1L);

        demoEntityService.delete(demo1);

        Example example = new Example(DemoEntity.class);
        example.createCriteria().andEqualTo("id",2L);

        demoEntityService.deleteByExample(example);

        return null;
    }



    @PostMapping("uuidtest2")
    @ResponseBody
    public String uuidtest2() {
        /***********************uuid新增*****************/
        UUIDDemoEntity demo1 = new UUIDDemoEntity();
        demo1.setName("demo1");
        uuIDDemoEntityService.save(demo1);
        uuIDDemoEntityService.save(demo1);

        UUIDDemoEntity demo2 = new UUIDDemoEntity();
        demo2.setName("demo2");
        uuIDDemoEntityService.saveNotNull(demo2);

        return null;
    }

    
}

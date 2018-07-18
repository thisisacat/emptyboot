package cn.notacat.cat.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/****************************************
 * @@CREATE : 2018-02-01 下午2:19
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 后期扩展使用
 * @@VERSION :
 *
 *****************************************/
public abstract class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);



    /**
     * 基本的参数封装和验证
     */
    @ModelAttribute
    public void buildParam(ModelMap model, HttpServletRequest req) {

        //todo 通用操作

    }
}

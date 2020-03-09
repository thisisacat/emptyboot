package cn.notacat.cat.web.controller;

import cn.notacat.cat.biz.exception.status.StatusCode;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/****************************************
 * @@CREATE : 2018-03-13 下午5:22
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 应用层未捕获异常处理 {@link org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController}
 * @@VERSION :
 *
 *****************************************/
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorController.class);
    private final ErrorAttributes errorAttributes;
    private final ErrorProperties errorProperties;
    private final ServerProperties serverProperties;

    public GlobalErrorController(ServerProperties serverProperties,ErrorAttributes errorAttributes) {
        this.serverProperties = serverProperties;
        this.errorAttributes = errorAttributes;
        this.errorProperties = serverProperties.getError();
    }


    @RequestMapping
    @ResponseBody
    public Response<String> error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        response.reset();
        // 设置状态码
        response.setStatus(status);
        response.setHeader("Cache-Control", "no-cache");
        Response<String> res = new Response<String>();
        res.setStatus(StatusCode.OTHER_ERROR.getStatus());
        res.setMsg(StatusCode.OTHER_ERROR.getMsg());
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        logger.error("请求{}异常信息:{}",request.getRequestURL(), JSON.toJSONString(body));
        return res;
    }


    protected Map<String, Object> getErrorAttributes(HttpServletRequest request,boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

    protected boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return !"false".equalsIgnoreCase(parameter);
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    @Override
    public String getErrorPath() {
        return errorProperties.getPath();
    }
}

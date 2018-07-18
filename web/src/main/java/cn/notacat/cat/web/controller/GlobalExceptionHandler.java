package cn.notacat.cat.web.controller;


import cn.notacat.cat.biz.exception.BusException;
import cn.notacat.cat.biz.exception.SystemException;
import cn.notacat.cat.biz.exception.status.EnumStatus;
import cn.notacat.cat.biz.exception.status.StatusCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/****************************************
 * @@CREATE : 2018-03-13 下午5:22
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 应用层异常处理
 * @@VERSION :
 *
 *****************************************/
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response<String> defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws Exception {
        response.reset();
        // 设置状态码
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setHeader("Cache-Control", "no-cache");
        Response<String> res = new Response<String>();
        if(ex instanceof BusException) {
            BusException busException = (BusException)ex;
            EnumStatus enumStatus = busException.getStatus();
            res.setStatus(enumStatus.getStatus());
            res.setMsg(StringUtils.isNotBlank(busException.getMsg())?busException.getMsg():enumStatus.getMsg());
            logger.warn("请求{}异常信息", request.getRequestURL(), ex);
        }else if(ex instanceof SystemException){
            SystemException systemException = (SystemException)ex;
            EnumStatus enumStatus = systemException.getStatus();
            res.setStatus(enumStatus.getStatus());
            res.setMsg(StringUtils.isNotBlank(systemException.getMsg())?systemException.getMsg():enumStatus.getMsg());
            logger.error("请求{}异常信息",request.getRequestURL(),ex);
        }else if(ex instanceof MissingServletRequestParameterException){
            res.setStatus(StatusCode.PARAM_ERROR.getStatus());
            res.setMsg(StatusCode.PARAM_ERROR.getMsg());
            logger.warn("请求{}异常信息",request.getRequestURL(),ex);
        }else if(ex instanceof ServletRequestBindingException){
            res.setStatus(StatusCode.PARAM_ERROR.getStatus());
            res.setMsg(StatusCode.PARAM_ERROR.getMsg());
            logger.warn("请求{}异常信息",request.getRequestURL(),ex);
        }else if(ex instanceof IllegalArgumentException){
            res.setStatus(StatusCode.PARAM_ERROR.getStatus());
            res.setMsg(StatusCode.PARAM_ERROR.getMsg());
            logger.warn("请求{}异常信息",request.getRequestURL(),ex);
        } else if(ex instanceof MaxUploadSizeExceededException){
            res.setStatus(StatusCode.MAXIMUM_UPLOAD_SIZE_ERROR.getStatus());
            res.setMsg(StatusCode.MAXIMUM_UPLOAD_SIZE_ERROR.getMsg());
            logger.warn("请求{}异常信息",request.getRequestURL(),ex);
        }else{
            res.setStatus(StatusCode.UNKNOW_ERROR.getStatus());
            res.setMsg(StatusCode.UNKNOW_ERROR.getMsg());
            logger.error("请求{}异常信息",request.getRequestURL(),ex);
        }
        return res;
    }
}

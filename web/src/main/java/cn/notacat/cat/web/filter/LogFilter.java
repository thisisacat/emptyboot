package cn.notacat.cat.web.filter;

import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.PatternMatcher;
import com.alibaba.druid.util.ServletPathMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/****************************************
 * @@CREATE : 2018-03-09 上午10:50
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :  日志拦截器
 * @@VERSION :
 *
 *****************************************/
public class LogFilter implements Filter {

    private final static Logger logger = LoggerFactory.getLogger(LogFilter.class);

    public static final String PARAM_NAME_EXCLUSIONS = "exclusions";
    public static final String SLOW_REQ_MILLIS = "slowReqMillis";

    public int slowReqMillis;

    protected PatternMatcher pathMatcher = new ServletPathMatcher();

    private Set<String> excludesPattern;



    @Override
    public void init(FilterConfig config) throws ServletException {
        String exclusions = config.getInitParameter(PARAM_NAME_EXCLUSIONS);
        if (exclusions != null && exclusions.trim().length() != 0) {
            excludesPattern = new HashSet<>(Arrays.asList(exclusions.split("\\s*,\\s*")));
        }
        //慢请求记录
        slowReqMillis = Integer.valueOf(config.getInitParameter(SLOW_REQ_MILLIS)).intValue();
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI = request.getRequestURI();
        if (isExclusion(requestURI)) {
            chain.doFilter(request, response);
            return;
        }
        long start = System.currentTimeMillis();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        String requestInfo = getRequestInfo(request);
        chain.doFilter(request, response);
        long duration = System.currentTimeMillis() - start;
        logger.info("请求url{};执行时间:{}ms",requestInfo,duration);
        if(duration >= slowReqMillis){
            logger.warn("show slow req {} millis.{}",duration,requestInfo);
        }
    }


    /**
     *  仿照{@link WebStatFilter}写法，修改了部分逻辑，不是很清楚他里面的逻辑判断
     * @param requestURI
     * @return
     */
    public boolean isExclusion(String requestURI) {
        if (null == excludesPattern|| null == requestURI) {
            return false;
        }
        for (String pattern : excludesPattern) {
            if (pathMatcher.matches(pattern, requestURI)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private String getRequestInfo(HttpServletRequest req) {
        String method = req.getMethod();
        StringBuilder bufMsg = new StringBuilder();
        bufMsg.append("method:").append(method).append(";").append("urlStr:").append(req.getRequestURI())
                .append(",params:{");
        Map paraMap = req.getParameterMap();
        Iterator it = paraMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = (String) entry.getKey();
            String[] values = (String[]) entry.getValue();
            String value = "[";
            for (String v : values) {
                value += v + ",";
            }
            value = (value.length() > 1 ? value.substring(0, value.length() - 1) : value) + "]";
            bufMsg.append(key).append(":").append(value).append(",");
        }
        return (paraMap.size() > 0 ? bufMsg.substring(0, bufMsg.length() - 1) : bufMsg) + "}";
    }



    @Override
    public void destroy() {
        //do nothing
    }





}

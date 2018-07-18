package cn.notacat.cat.web.support;

import cn.notacat.cat.web.filter.LogFilter;
import cn.notacat.cat.web.interceptor.AppInterceptor;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/****************************************
 * @@CREATE : 2018-06-19 下午4:26
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :
 * @@VERSION :
 *
 *****************************************/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${filter.slow.reqmillis:3000}")
    private String slowReqMillis;


    @Bean
    public AppInterceptor appInterceptor(){
        return new AppInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(appInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HttpMessageConverters customConverters(){
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON,new MediaType("application", "*+json")));
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        return new HttpMessageConverters(false, Arrays.asList(fastJsonHttpMessageConverter,stringHttpMessageConverter));
    }


    /**
     * {@link StatViewServlet}
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        reg.addInitParameter("resetEnable", "false");
        reg.addInitParameter("loginUsername", "demo");
        reg.addInitParameter("loginPassword", "demoAdmin");
        return reg;
    }

    /**
     *  {@link WebStatFilter}
     * @return
     */
    @Bean
    public FilterRegistrationBean druidWebStatFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "/druid/*,*.ico,/error");
        return filterRegistrationBean;
    }


    /**
     * {@link LogFilter}
     * @return
     */
    @Bean
    public FilterRegistrationBean logFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "/druid/*,*.ico,/error");
        filterRegistrationBean.addInitParameter("slowReqMillis",slowReqMillis);
        return filterRegistrationBean;
    }


}

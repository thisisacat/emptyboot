package cn.notacat.cat.biz.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/****************************************
 * @@CREATE : 2018-02-01 下午10:46
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 通用组件注入
 * @@VERSION :
 *
 *****************************************/
@Configuration
public class SupportConfig {

    @Bean(name = "springContextSupport")
    public SpringContextSupport SpringContextSupport() {
        SpringContextSupport springContextSupport = new SpringContextSupport();
        return springContextSupport;
    }





}

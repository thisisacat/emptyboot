package cn.notacat.cat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/****************************************
 * @@CREATE : 2018-06-19 下午4:26
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :1
 * @@VERSION :
 *
 *****************************************/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppMain extends SpringBootServletInitializer {



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppMain.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
    }


}
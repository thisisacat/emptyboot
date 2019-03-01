package cn.notacat.cat;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/****************************************
 * @@CREATE : 2018-06-19 下午4:26
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :
 * @@VERSION :aa
 *
 *****************************************/
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AppMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppMain.class);
    }


}
package cn.notacat.cat.dal.support;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/****************************************
 * @@CREATE : 2018-01-30 下午2:49
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION :
 * @@VERSION :
 *
 *****************************************/
@Configuration
public class DataSourceConfig {


    @Bean(name = "dataSource", initMethod = "init",destroyMethod="close")
    @ConfigurationProperties(prefix="spring.datasource.druid.primary")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }


}

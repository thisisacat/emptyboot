package cn.notacat.cat.dal.support;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/****************************************
 * @@CREATE : 2018-01-30 下午2:49
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Configuration
@MapperScan(basePackages={"cn.notacat.cat.dal.dao.mapper"}, sqlSessionFactoryRef="sqlSessionFactory")
public class MybatisConfig{

    @Autowired
    private DataSource dataSource;

    @Value("${primary.mybatis.config}")
    private String mybatisConfig;
    @Value("${primary.mybatis.mapper.path}")
    private String[] mapperPath;
    @Value("${primary.mybatis.type.aliaspackage}")
    private String typeAliasPackage;


    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        /** 设置datasource */
        sqlSessionFactoryBean.setDataSource(dataSource);
        /** 设置mybatis configuration 扫描路径 */
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfig));
        /** 添加mapper 扫描路径 */
        PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();
        List<Resource> resources = new ArrayList<Resource>();
        for (String mapperLocation : this.mapperPath) {
            Resource[] mappers = pathMatchingResourcePatternResolver.getResources(mapperLocation);
            resources.addAll(Arrays.asList(mappers));
        }
        sqlSessionFactoryBean.setMapperLocations(resources.toArray(new Resource[resources.size()]));
        /** 设置typeAlias 包扫描路径 */
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasPackage);
        return sqlSessionFactoryBean;
    }


    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() {
        try {
            return createSqlSessionFactoryBean().getObject();
        } catch (Exception e) {
            throw  new RuntimeException(e);
        }
    }


    @Bean("sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(){
        SqlSessionTemplate sqlSessionTemplate= new SqlSessionTemplate(sqlSessionFactory());
        return sqlSessionTemplate;
    }


    @Bean("txManager")
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }


}

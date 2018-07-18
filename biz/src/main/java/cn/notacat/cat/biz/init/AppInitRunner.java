package cn.notacat.cat.biz.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/****************************************
 * @@CREATE : 2018-05-22 下午2:29
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
@Component("appInitRunner")
public class AppInitRunner implements CommandLineRunner {

    private final static Logger logger = LoggerFactory.getLogger(AppInitRunner.class);






    @Override
    public void run(String... args) throws Exception {
        //todo 系统初始化。。。。。。。。。。
    }


}

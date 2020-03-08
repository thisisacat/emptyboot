package cn.notacat.cat.web.controller.dws;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/****************************************
 * @@CREATE : 2019-06-29 下午2:28
 * @@AUTH : NOT A CAT【NOTACAT@CAT.ORZ】
 * @@DESCRIPTION : 
 * @@VERSION :
 *
 *****************************************/
public class ThreadTest {

    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("queue-kfk-thread-pool-%d").build();
    int cores = Runtime.getRuntime().availableProcessors();
    int threadCount = cores * 2 + cores / 2;
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(threadCount, threadCount, 60L, TimeUnit.SECONDS,
            new LimitedQueue<Runnable>(2000),namedThreadFactory,
            (Runnable r, ThreadPoolExecutor e)->{
                //logger.error("异步调用写入kfk线程池任务数量过多溢出");
            });

}

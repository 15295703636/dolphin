package org.cs.dp.sonar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName ThreadAsyncConfig
 * @Description 线程池
 * @Author Liujt
 * @Date 2019/11/27 16:11
 **/
@Component
public class ThreadAsyncConfig implements AsyncConfigurer {

    @Value("${thread.corePoolSize}")
    private int corePoolSize;

    @Value("${thread.maxPoolSize}")
    private int maxPoolSize;

    @Value("${thread.queueCapacity}")
    private int queueCapacity;

    @Value("${thread.waitFTTCOS}")
    private boolean waitForTasksToCompleteOnShutdown;

    @Value("${thread.awaitTerminationSeconds}")
    private int awaitTerminationSeconds;

    @Value("${thread.threadNamePrefix}")
    private String threadNamePrefix;

    @Bean
    public AsyncTaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //设置核心线程数
        threadPool.setCorePoolSize(corePoolSize);
        //设置最大线程数
        threadPool.setMaxPoolSize(maxPoolSize);
        //线程池所使用的缓冲队列
        threadPool.setQueueCapacity(queueCapacity);
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(awaitTerminationSeconds);
        //  线程名称前缀
        threadPool.setThreadNamePrefix(threadNamePrefix);
        //拒接策略 丢弃任务并抛出RejectedExecutionException异常。
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化线程
        threadPool.initialize();
        return threadPool;
    }

}

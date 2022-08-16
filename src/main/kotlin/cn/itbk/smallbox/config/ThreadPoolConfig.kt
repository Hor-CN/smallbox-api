package cn.itbk.smallbox.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.ThreadPoolExecutor

/**
 * 线程池
 *@author Hor
 *@create 2022/4/17 9:20
 */
@Configuration
@EnableAsync//开启异步
class ThreadPoolConfig {

    @Bean("threadPool")
    fun taskExecutor() : TaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        // 设置核心线程数
        executor.corePoolSize = 5
        // 设置最大线程数
        executor.maxPoolSize = 10
        // 设置队列容量
        executor.setQueueCapacity(100)
        // 设置线程活跃时间（秒）
        executor.keepAliveSeconds = 60
        // 设置默认线程名称
        executor.setThreadNamePrefix("Thread-")
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true)
        return executor
    }


}
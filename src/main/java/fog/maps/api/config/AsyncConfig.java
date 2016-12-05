package fog.maps.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * Created by nakulsharma on 30-11-2016.
 * Configuration class for asynchronous calls
 */

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    private Logger LOG = LoggerFactory.getLogger(AsyncConfig.class);

    @Override
    public Executor getAsyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(7);
        executor.setMaxPoolSize(42);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("FogServiceExecutor-");
        executor.initialize();
        return new HandlingAsyncTaskExecutor(executor);
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, objects) -> LOG.error("Error executing async method {} with params {}.", method.getName(), Arrays.asList(objects), ex);
    }
}
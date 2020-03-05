package com.yzd.prometheus.demo.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.ToDoubleFunction;

@Service
public class MetricsService {
    private Counter counter;

    private MeterRegistry registry;

    public MetricsService(MeterRegistry registry) {
        this.registry=registry;
        //gn_beat_counter_total
        //this.counter = registry.counter("http.gn.beat.counter","xxx11","xxx");
        this.counter = registry.counter("http.gn.beat.counter", "xxx11", "xxx");
        //添加git代码的提交id,这样就可以知道当前运行的代码的版本
        //registry.gauge("git", Tags.of("short_commit_id",gitInfo.getShortCommitId()),Integer.valueOf(1));
    }

    /**
     * 推荐使用此方法
     * @param path
     * @param method
     * @param status
     */
    public void responseCounter(String path, String method, String status){
        registry.counter("http.requests","path",path,"method",method,"status",status).increment();
        //registry.summary("http.requests","path",path,"method",method,"status",status);
    }
    public void gitGauge(Tags tags){
        registry.gauge("git",tags,Integer.valueOf(1));
    }

    @Autowired
    PrometheusMeterRegistry prometheusMeterRegistry;

    public void sendSuccessIncrement() {
        counter.increment();
    }

    public void createByPrometheusMeterRegistry() {
        Counter counter = Counter.builder("goods_rank_sync_count")
                .tags("status", "success")
                .description("Number of successful goods rank sync")
                .register(registry);

    }

}
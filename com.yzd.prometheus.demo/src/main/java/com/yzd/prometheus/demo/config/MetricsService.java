package com.yzd.prometheus.demo.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private Counter counter;

    private MeterRegistry registry;
    public MetricsService(MeterRegistry registry) {
        this.registry=registry;
        //gn_beat_counter_total
        //this.counter = registry.counter("http.gn.beat.counter","xxx11","xxx");
        this.counter = registry.counter("http.gn.beat.counter", "xxx11", "xxx");
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
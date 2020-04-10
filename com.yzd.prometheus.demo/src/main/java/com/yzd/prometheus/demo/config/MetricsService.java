package com.yzd.prometheus.demo.config;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.search.Search;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {
    private final static Logger logger = LoggerFactory.getLogger(MetricsService.class);
    private Counter counter;

    private MeterRegistry registry;

    public MetricsService(MeterRegistry registry) {
        this.registry = registry;
        //gn_beat_counter_total
        //this.counter = registry.counter("http.gn.beat.counter","xxx11","xxx");
        this.counter = registry.counter("http.gn.beat.counter", "xxx11", "xxx");
        //添加git代码的提交id,这样就可以知道当前运行的代码的版本
        //registry.gauge("git", Tags.of("short_commit_id",gitInfo.getShortCommitId()),Integer.valueOf(1));
    }

    /**
     * 推荐使用此方法
     *
     * @param path
     * @param method
     * @param status
     */
    public void responseCounter(String path, String method, String status) {
        registry.counter("http.requests", "path", path, "method", method, "status", status).increment();
        //registry.summary("http.requests","path",path,"method",method,"status",status);
    }

    public void gitGauge(Tags tags) {
        registry.gauge("git", tags, Integer.valueOf(1));
    }

    public void getMetrics() {
        for (Meter meter : registry.getMeters()) {
            logger.info(meter.getId().toString());
        }
    }

    Counter test;

    public void removeMetrics(String name) {
        //查找对应的指标信息
        Search search = registry.find(name);
        logger.info("search.meters().size()={}", search.meters().size());
        for (Meter meter : registry.getMeters()) {
            if (name.equals(meter.getId().getName())) {
                //读取指标相关的tag
                logger.info("Tag={}", meter.getId().getTag("path"));
                //读取指标ID的全部信息
                logger.info("ID={}", meter.getId().toString());
                //移除指标
                registry.remove(meter.getId());
            }
        }
        //增加指标
        registry.counter("addMetrics").increment();
        //下面这样写是错误的
        //test = Counter.builder("T2345").tags("addMetrics").register(registry);
        //test.increment();
        //test.increment();
    }

    Counter testCounterMetrics = Metrics.counter("test-counter");

    public void registryMetrics() {

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
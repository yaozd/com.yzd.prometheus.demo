package com.yzd.prometheus.demo.controller.api;

import com.yzd.prometheus.demo.config.MetricsService;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllerApi {
    @Autowired
    MetricsService metricsService;

    @RequestMapping("/")
    public String index() {
        metricsService.sendSuccessIncrement();
        return "this is prometheus demo";
    }

    @RequestMapping("/greeting")
    public String greeting() {
        return "greeting";
    }
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
    @Autowired
    PrometheusMeterRegistry prometheusMeterRegistry;
    @RequestMapping("/scrape")
    public String scrape() {
        return prometheusMeterRegistry.scrape();
    }
}

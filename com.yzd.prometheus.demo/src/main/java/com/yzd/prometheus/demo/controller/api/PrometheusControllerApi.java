package com.yzd.prometheus.demo.controller.api;

import com.yzd.prometheus.demo.config.MetricsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("prometheus")
public class PrometheusControllerApi {
    private final static Logger logger = LoggerFactory.getLogger(PrometheusControllerApi.class);
    @Autowired
    MetricsService metricsService;

    @GetMapping("getMetrics")
    public String getMetrics() {
        metricsService.getMetrics();
        return "getMetrics";
    }

    @GetMapping("addMetrics")
    public String addMetrics() {
        return "addMetrics";
    }

    @GetMapping("removeMetrics")
    public String removeMetrics() {
        metricsService.removeMetrics("http.requests");
        metricsService.removeMetrics("git");
        return "removeMetrics";
    }

}

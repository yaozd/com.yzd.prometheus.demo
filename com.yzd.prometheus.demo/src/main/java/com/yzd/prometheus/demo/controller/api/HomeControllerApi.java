package com.yzd.prometheus.demo.controller.api;

import com.yzd.prometheus.demo.config.GitInfo;
import com.yzd.prometheus.demo.config.MetricsService;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class HomeControllerApi {
    private final static Logger logger = LoggerFactory.getLogger(HomeControllerApi.class);
    @Autowired
    MetricsService metricsService;

    /**
     * 匹配所有路由
     * @param request
     * @return
     */
    @RequestMapping("/**")
    public String index(HttpServletRequest request) {
        String msg = "request uri:" + request.getRequestURI();
        logger.info(msg);
        return msg;
    }

    @RequestMapping("/greeting")
    public String greeting() {
        metricsService.sendSuccessIncrement();
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

    @Autowired
    private GitInfo gitInfo;

    @GetMapping("getGitShortCommitId")
    public String getGitShortCommitId() {
        return gitInfo.getShortCommitId();
    }
}

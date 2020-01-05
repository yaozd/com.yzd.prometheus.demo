package com.yzd.prometheus.demo.config;

import io.prometheus.client.Counter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringBoot2.x整合HandlerInterceptor拦截器（1-定义拦截器）
 * https://www.jianshu.com/p/8c7d60e2b21c
 */
@Component
public class PrometheusMetricsInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    MetricsService metricsService;
    static final Counter requestCounter = Counter.build()
            .name("io_namespace_http_requests_tota").labelNames("path", "method", "code")
            .help("Total requests.").register();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        int status = response.getStatus();
        metricsService.sendSuccessIncrement();
        metricsService.responseCounter(requestURI,method,String.valueOf(status));
        requestCounter.labels(requestURI, method, String.valueOf(status)).inc();
        super.afterCompletion(request, response, handler, ex);
    }
}

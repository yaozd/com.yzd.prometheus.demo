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
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;


@RestController
public class HomeControllerApi {
    private final static Logger logger = LoggerFactory.getLogger(HomeControllerApi.class);
    @Autowired
    MetricsService metricsService;

    /**
     * 匹配所有路由
     *
     * @param request
     * @return
     */
    @RequestMapping("/**")
    public String index(HttpServletRequest request) {
        String bodyMsg = "body:" + readBody(request);
        String uriMsg = "request uri:" + request.getRequestURI();
        logger.info("\r\n{} ; {}",uriMsg,bodyMsg);
        return uriMsg;
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

    /**
     * 302 重定向
     * 302状态的请求是通过response headers中的location进行二次跳转。
     * 二次跳转理论是由浏览器发起的。
     * 浏览器发起请求此时已经是hp.hualala.com，
     * 而hp.hualala.com这个服务目前不过ROUTER
     *
     * @param response
     * @throws Exception
     */
    @GetMapping("redirectURL")
    public void redirectURL(HttpServletResponse response) throws Exception {
        //response.sendRedirect("https://www.baidu.com");
        response.sendRedirect("/");
    }

    public static String readBody(HttpServletRequest request) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

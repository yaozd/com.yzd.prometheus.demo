package com.yzd.prometheus.demo.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 */
@RestController
@RequestMapping("k8s")
public class K8SControllerApi {
    @GetMapping("api")
    public String apiUri() {
        return "api";
    }

    @GetMapping("watch-uri")
    public String watchUri() throws InterruptedException {
        int next = ThreadLocalRandom.current().nextInt(10, 100);
        System.err.println("sleep time :"+next+"s");
        Thread.sleep(next*1000);
        return "watch-uri";
    }

    @GetMapping("read-all-uri")
    public String readAllUri() {
        return "read-all-uri";
    }
}

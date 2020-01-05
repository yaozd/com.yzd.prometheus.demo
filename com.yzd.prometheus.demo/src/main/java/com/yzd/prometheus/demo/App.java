package com.yzd.prometheus.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    /**
     * http://localhost:8090/actuator/prometheus
     * http://localhost:8090/actuator
     * http://localhost:8090/
     * http://localhost:8090/greeting
     *
     * @param args
     */

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}

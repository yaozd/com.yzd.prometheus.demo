package com.yzd.prometheus.demo;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    /**
     * 为每个指标增加一个共同的标签：application
     * @param applicationName
     * @return
     */
    /*@Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }*/
}

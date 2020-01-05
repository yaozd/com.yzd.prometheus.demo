## 参考：
- [Prometheus for Spring Boot](https://www.jianshu.com/p/f1c9f1868bd5)
- [micrometer](http://micrometer.io/docs/concepts#_meter_filters)

## Meter filters
- [指标过滤](http://micrometer.io/docs/concepts#_meter_filters)

##　Grafana模板导入与导出
- [Export and Import](https://grafana.com/docs/grafana/latest/reference/export_import/)
- [https://grafana.com/grafana/dashboards](https://grafana.com/grafana/dashboards)

##　Grafana
- [JVM (Micrometer)by mweirauch](https://grafana.com/grafana/dashboards/4701)
    ```
    Instead of using the job tag to distinct different applications, this dashboard makes use of a common tag called application applied to every metric.
    
    In a Spring Boot setting, this could look like this:
    
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
        @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }
    Or since Micrometer 1.1.0 with a property:
    
    management.metrics.tags.application=${spring.application.name}
    Compatibility
    micrometer:1.1.0+
    micrometer-jvm-extras:0.1.2+

    ```
- [Spring Boot 2.1 Statisticsby gadams00](https://grafana.com/grafana/dashboards/10280)
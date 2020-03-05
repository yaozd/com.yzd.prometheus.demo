package com.yzd.prometheus.demo.config;

import io.micrometer.core.instrument.Tags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class GitInfo {
    @Autowired(required = false)
    private GitProperties git;
    @Autowired
    MetricsService metricsService;
    public String getShortCommitId(){
        if(git==null){
            return "unknown";
        }
        return git.getShortCommitId();
    }
    @PostConstruct
    void init(){
        //添加git代码的提交id到prometheus,这样就可以知道当前运行的代码的版本
        metricsService.gitGauge(Tags.of("short_commit_id",getShortCommitId()));
    }
}

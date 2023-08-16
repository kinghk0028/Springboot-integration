package com.kinghk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Data
@ConfigurationProperties(prefix = "hbase")
public class HbaseProperties {
    private Map<String, String> config;


//    public Map<String, String> getConfig() {
//        return config;
//    }
//
//    public void setConfig(Map<String, String> config) {
//        this.config = config;
//    }
}

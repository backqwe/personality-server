package org.olympos.cloud.client.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ApiConfig
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-05-22 11:52
 */
@PropertySource(value = {"classpath:ApiConfig.yml", "classpath:ApiConfig.yaml", "classpath:ApiConfig.properties"},
        ignoreResourceNotFound = true,
        encoding = "utf-8",
        factory = ConfigurationFactory.class)
@ConfigurationProperties(prefix = "api",
        ignoreInvalidFields = true)
@Component
@Data
public class ApiConfig implements Cloneable {

    private ConcurrentHashMap<String,String> enhances;
}

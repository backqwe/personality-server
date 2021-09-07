package org.olympos.cloud.server.configuration;

import lombok.Data;
import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ApiConfig
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-05-22 11:52
 */
@Component
@Data
public class ApiConfig implements Cloneable {

    // 接口增强配置容器
    private ConcurrentHashMap<String, Set<String>> apiEnhanceConfigs = new ConcurrentHashMap<>();
    // 接口增强处理容器
    private CopyOnWriteArrayList<ApiFaceEnhanceHandler> apiEnhanceHandles = new CopyOnWriteArrayList<>();

    public ApiConfig clone() throws CloneNotSupportedException {
        return (ApiConfig)super.clone();
    }
}

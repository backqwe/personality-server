package org.olympos.cloud.server.handler.enhanceHandler;

import org.olympos.cloud.server.handler.paramHandler.PostProcessorParam;
import org.olympos.cloud.server.handler.paramHandler.PreProcessorParam;
import org.springframework.web.server.ServerWebExchange;

/**
 * ApiFaceEnhanceHandler
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-07-24 13:42
 */
public interface ApiFaceEnhanceHandler {

    // todo 外面可以套一个模板类，模板类可以提供一些常用方法，比如传递参数到 exchange 中的 Attribute 里面

    default void beforeHandle(PreProcessorParam param, ServerWebExchange exchange) {}

    default boolean beforeSkip() {
        return false;
    }

    default void afterHandle(PostProcessorParam param, ServerWebExchange exchange) {}

    default boolean afterSkip() {
        return false;
    }

    default int getOrder() {
        return 99999;
    }
}

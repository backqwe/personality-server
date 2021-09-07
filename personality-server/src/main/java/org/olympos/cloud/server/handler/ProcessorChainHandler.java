package org.olympos.cloud.server.handler;

import org.olympos.cloud.server.configuration.ApiConfig;
import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.olympos.cloud.server.handler.paramHandler.BaseParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ProcessorChainHandle
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-07-23 19:49
 */
@Component
public class ProcessorChainHandler {

    @Autowired
    private ApiConfig apiConfig;

    // todo
    public void execute(ApiFaceEnhanceHandler enhanceHandler, ServerWebExchange exchange, BaseParam param) {

    }

/*    public Mono<Void> execute(ServerWebExchange exchange, ParamHandler paramHandler) {

        paramHandler.getParam( exchange);

        return Mono.defer(() -> {
            // todo 未写逻辑
            return Mono.empty();
        });
    }*/
}

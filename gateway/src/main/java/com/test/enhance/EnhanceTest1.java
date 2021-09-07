package com.test.enhance;

import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.olympos.cloud.server.handler.paramHandler.PreProcessorParam;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * EnhanceTest1
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-08-13 17:32
 */
@Component
public class EnhanceTest1 implements ApiFaceEnhanceHandler {

    public void beforeHandle(PreProcessorParam param, ServerWebExchange exchange) {
        param.setCustomParam( "test", "TEST");
        System.out.println("####################### before test start ######################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### " + param.getParamTypes() + " ##########################");
        System.out.println("####################### " + param.getParams() + " ##########################");
        param.ifEmptySetParam( "id", "888");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### " + param.getParamTypes() + " ##########################");
        System.out.println("####################### " + param.getParams() + " ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test end ########################");
    }

    public boolean beforeSkip() {
        return false;
    }

    public int getOrder() {
        return 1;
    }
}

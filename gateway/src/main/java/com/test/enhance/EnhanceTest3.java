package com.test.enhance;

import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.olympos.cloud.server.handler.paramHandler.PostProcessorParam;
import org.olympos.cloud.server.handler.paramHandler.PreProcessorParam;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

/**
 * EnhanceTest3
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-08-13 17:32
 */
@Component
public class EnhanceTest3 implements ApiFaceEnhanceHandler {

    public void beforeHandle(PreProcessorParam param, ServerWebExchange exchange) {
        param.getParams().put("test", "test");
        System.out.println("####################### before test start ######################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### " + param.getParamTypes() + " ##########################");
        System.out.println("####################### " + param.getParams().entrySet() + " ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test ! ##########################");
        System.out.println("####################### before test end ########################");
    }

    public boolean beforeSkip() {
        return false;
    }

    public void afterHandle(PostProcessorParam param, ServerWebExchange exchange) {
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test start %%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getResults() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getResultType() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test end %%%%%%%%%%%%%%%%%%%%%%%%");
    }

    public boolean afterSkip() {
        return false;
    }

    public int getOrder() {
        return 3;
    }
}

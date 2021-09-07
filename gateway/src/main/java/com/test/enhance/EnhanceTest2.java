package com.test.enhance;

import org.olympos.cloud.server.handler.enhanceHandler.ApiFaceEnhanceHandler;
import org.olympos.cloud.server.handler.paramHandler.PostProcessorParam;
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
public class EnhanceTest2 implements ApiFaceEnhanceHandler {

    public void afterHandle(PostProcessorParam param, ServerWebExchange exchange) {

        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test start %%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getResults() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getResultType() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getParams() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% " + param.getCustomParams() + " %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test ! %%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%% after test end %%%%%%%%%%%%%%%%%%%%%%%%");
    }

    public boolean afterSkip() {
        return false;
    }

    public int getOrder() {
        return 2;
    }
}

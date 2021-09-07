package org.olympos.cloud.server.pluginConfigHandler;

import org.dromara.soul.common.dto.PluginData;
import org.dromara.soul.common.dto.RuleData;
import org.dromara.soul.common.dto.SelectorData;
import org.dromara.soul.plugin.base.handler.PluginDataHandler;
import org.springframework.stereotype.Component;

/**
 * BeforePluginHandler
 *
 * @Describe: 接口前置处理的插件处理
 * @Author Chanse Hao
 * @Date 2021-07-15 20:51
 */
@Component
public class BeforePluginHandler implements PluginDataHandler {

    public void handlerPlugin(PluginData pluginData) {

    }

    public void removePlugin(PluginData pluginData) {

    }

    public void handlerSelector(SelectorData selectorData) {

    }

    public void removeSelector(SelectorData selectorData) {

    }

    public void handlerRule(RuleData ruleData) {

    }

    public void removeRule(RuleData ruleData) {

    }

    public String pluginNamed() {
        return "BeforePlugin";
    }
}

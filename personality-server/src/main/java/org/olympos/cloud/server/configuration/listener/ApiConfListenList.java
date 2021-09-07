package org.olympos.cloud.server.configuration.listener;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * ApiConfListenList
 *
 * @Describe:
 * @Author Chanse Hao
 * @Date 2021-06-16 03:22
 */
@Component
@Data
public class ApiConfListenList implements Cloneable {

    private ConcurrentSkipListSet<String> listenList = new ConcurrentSkipListSet<>();

    public ApiConfListenList clone() throws CloneNotSupportedException {
        return (ApiConfListenList)super.clone();
    }
}

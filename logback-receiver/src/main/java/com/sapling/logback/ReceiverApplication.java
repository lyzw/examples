package com.sapling.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/1
 * @since v1.0
 */
public class ReceiverApplication {

    private static Logger logger = LoggerFactory.getLogger(ReceiverApplication.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("ReceiverApplication is started");
        while (true){
            Thread.sleep(10000);
        }
    }
}

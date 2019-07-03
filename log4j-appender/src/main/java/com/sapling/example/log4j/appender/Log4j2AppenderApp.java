package com.sapling.example.log4j.appender;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/14
 * @since v1.0
 */
public class Log4j2AppenderApp {


    private static Logger logger = LoggerFactory.getLogger(Log4j2AppenderApp.class);

    public static void main(String[] args) throws InterruptedException, ParseException {
        long start = System.nanoTime();
        int index = 0;
        while (index < 1000000) {
            try {
                logger.info("Appender logger info index :{}", index);
                if ((index % 3) == 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                logger.error("index:" + index, e);
            }finally {
                index++;
//                Thread.sleep(1000);
            }
            if (index % 100000 == 0){
                System.out.println(index);
            }
        }
        System.out.println(System.nanoTime()-start);

    }
}

package com.sapling.logback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/1
 * @since v1.0
 */
public class AppenderApplication {

    private static Logger logger = LoggerFactory.getLogger(AppenderApplication.class);

    public static void main(String[] args) throws InterruptedException, ParseException {
        long start = System.nanoTime();
        int index = 0;
        while (index < 100000) {
            try {
                logger.info("Appender logger info index :{}", index);
                if ((index % 3) == 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                logger.error("index:" + index, e);
            } finally {
                index++;
                //                Thread.sleep(1000);
            }
            if (index % 1000 == 0) {
                System.out.println(index);
            }
        }
        System.out.println(System.nanoTime() - start);


    }
}

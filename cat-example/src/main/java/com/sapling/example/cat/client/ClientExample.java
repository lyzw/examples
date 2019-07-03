package com.sapling.example.cat.client;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/12
 * @since v1.0
 */
public class ClientExample {

    public static void main(String[] args) throws InterruptedException {

        int index = 0;
        while (true) {
            Transaction t = Cat.newTransaction("URL", "pageName");

            index ++;
            try {
                Cat.logEvent("URL.Server", "serverIp", Event.SUCCESS, "ip=${serverIp}");
                Cat.logMetricForCount("metric.key");
                Cat.logMetricForDuration("metric.key", 5);
                if (index % 2 ==0){
                    throw new Exception( "exception happen:" + index);
                }
                t.setStatus(Transaction.SUCCESS);
            } catch (Exception e) {
                t.setStatus(e);
                Cat.logError(e);
            } finally {
                t.complete();
            }
            Thread.sleep(1000);
        }
    }
}

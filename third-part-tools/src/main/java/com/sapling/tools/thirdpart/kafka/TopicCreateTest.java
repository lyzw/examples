package com.sapling.tools.thirdpart.kafka;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/8
 * @since v1.0
 */
public class TopicCreateTest {


    public static void main(String[] args) {

        String zk = "10.221.123.80:2181,10.221.123.81:2181,10.221.123.82:2181";
        List<String> topics = Arrays.asList(
                new String[]{"agent_hangxin15tlog11_fc20190103173400993",
                        "agent_hangxin15tlog31_fc20190103173838594",
                        "agent_hangxin15tlog41_fc20190103173943026",
                        "agent_hangxin15tlog45_fc20190103174005352",
                        "agent_hangxin15tlog71_fc20190103174032271",
                        "agent_hangxin15tlog81_fc20190103174102835",
                        "agent_hangxin15t_log21_fc20190107165446771",
                        "agent_hangxin15t_log32_fc20190108121659412"});
        Properties properties = new Properties();
        properties.setProperty("max.message.bytes", "1000012");
        for(String item: topics) {
            try{
                KafkaZkTopicUtil.createTopic(zk, new TopicInfo(item, 40, 1, properties));
//                System.out.println(item +" exists -> " + KafkaZkTopicUtil.isTopicExists(new ZookeeperConfig(zk),item));
                System.out.println("topic" + item + "创建成功！");
            }catch (TopicExistsException e){
                System.out.println(item + " exists");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
            //            System.out.println("item->" + item);
//        KafkaZkTopicUtil.getAllTopics(new ZookeeperConfig(zk)).forEach(item ->{
//            System.out.println(item);
//        });


    }
}

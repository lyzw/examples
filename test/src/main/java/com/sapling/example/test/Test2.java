package com.sapling.example.test;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/11/16
 * @since v1.0
 */
public class Test2 {

    private static Logger logger = LoggerFactory.getLogger(Test2.class);

    static class Model {
        String hostname;

        String ip;

        int order;

        public Model(String hostname, String ip, int order) {
            this.hostname = hostname;
            this.ip = ip;
            this.order = order;
        }

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

    }

    public static void main(String[] args) {


        List<Model> models = new ArrayList<>();

        models.add(new Model("vm_11_11", "192.168.1.0", 1));
        models.add(new Model("vm_11_11", "192.168.1.1", 2));
        models.add(new Model("vm_11_11", "192.168.1.2", 3));
        models.add(new Model("vm_11_11", "192.168.1.3", 4));
        models.add(new Model("vm_11_11", "192.168.1.2", 5));
        models.add(new Model("vm_11_12", "192.168.1.3", 6));
        models.add(new Model("vm_11_12", "192.168.1.6", 7));
        models.add(new Model("vm_11_12", "192.168.1.7", 8));
        models.add(new Model("vm_11_12", "192.168.1.8", 9));
        models.add(new Model("vm_11_12", "192.168.1.9", 10));
        models.add(new Model("vm_11_13", "192.168.1.10", 11));
        models.add(new Model("vm_11_13", "192.168.1.11", 12));
        models.add(new Model("vm_11_13", "192.168.1.11", 13));
        models.add(new Model("vm_11_13", "192.168.1.12", 14));
        models.add(new Model("vm_11_13", "192.168.1.11", 15));
        models.add(new Model("vm_11_13", "192.168.1.15", 16));
        models.add(new Model("vm_11_16", "192.168.1.16", 17));
        models.add(new Model("vm_11_16", "192.168.1.17", 18));
        models.add(new Model("vm_11_16", "192.168.1.18", 19));
        models.add(new Model("vm_11_16", "192.168.1.19", 20));
        models.add(new Model("vm_11_16", "192.168.1.20", 21));
        models.add(new Model("vm_11_16", "192.168.1.21", 22));
        models.add(new Model("vm_11_16", "192.168.1.22", 23));


        Map<String, List<Model>> listMap = models.stream()
                .collect(Collectors.groupingBy(Model::getHostname));

        Collection<List<Model>> values = listMap.values();
        for (List<Model> list : values) {
            Map<String, List<Model>> map = list.stream()
                    .collect(Collectors.groupingBy(Model::getIp));

            Collection<List<Model>> lists = map.values();
            for (List<Model> item : lists) {
                Optional<Model> model = item.stream().collect(Collectors.minBy(new Comparator<Model>() {
                    @Override
                    public int compare(Model o1, Model o2) {
                        return o1.getOrder() - o2.getOrder();
                    }
                }));

                model.ifPresent(x -> logger.info("order:{},{},{}", x.getHostname(),x.getIp(),x.getOrder()));
            }
        }
    }


}

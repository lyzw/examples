package com.sapling.example.base.collection.list;

import java.util.*;

public class ListExample {

    public static void main(String[] args) {
        Example[] examples = new Example[2];
        examples[0] = new Example("a");
        examples[1] = new Example("b");
        final Example[] examples1 = examples;
        examples[0] = new Example("c");
        Arrays.stream(examples1).forEach(f-> System.out.println(f));

        //新增的初始化方法
        List<String> list =new  ArrayList<String>();
        initFromOtherCollectionWithChange();
        Collections.synchronizedList(list);
//        addAll();
    }

    public static void addAll(){
        List list = new ArrayList();
        System.out.println("add all for empty list");
        System.out.println(list.addAll(Collections.emptyList()));
    }
    public static void initFromOtherCollectionWithChange(){
        Map map = new HashMap<String,Example>();
        Example a = new Example("a");
        Example aa = new Example("aa");
        Example aaa = new Example("aaa");
        Example aaaa = new Example("aaaa");
        map.put("1",a);
        map.put("2",aa);
        map.put("3",aaa);
        map.put("4",aaaa);
        Collection cl = map.values();
        List list = new ArrayList(cl);
        System.out.println(list);
        aaa.setValue("ccc");
        System.out.println(list);
    }

    static class Example{
        String value;

        public Example() {
        }

        public Example(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Example{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}

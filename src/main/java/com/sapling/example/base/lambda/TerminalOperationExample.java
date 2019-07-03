package com.sapling.example.base.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/25
 * @since v1.0
 */
public class TerminalOperationExample {

    private static List<String> list = Arrays.asList(new String[]{"Tom", "Toms", "lily", "张三", "李四"});
    private static List<Integer> integerList = Arrays.asList(new Integer[]{1,2,3});

    public static void main(String[] args) {
        testStreaming();
    }

    public static void testMethod(){
        System.out.println(list.stream().filter(it -> it.length()>3).collect(Collectors.toList()));
        System.out.println(list.stream().map(it -> it + "'s").collect(Collectors.toList()));
        List<String> list1 = integerList.stream().map(it -> it + "").collect(Collectors.toList());
        System.out.println(list1.stream().mapToInt(Integer::parseInt).sum());
        System.out.println(list.stream().flatMap(it -> Arrays.stream(it.split(""))).collect(Collectors.toList()));
        System.out.println(list.stream().distinct().collect(Collectors.toList()));
        List<String> list2 = list.stream().peek(it -> {it = it +"s" ;System.out.println(it);}).collect(Collectors.toList());
        System.out.println(list2);
        list.stream().forEach(System.out::println);
        list.stream().forEachOrdered(System.out::println);
        System.out.println(integerList.stream().reduce((t1,t2)->t1*1 + t2*2));
        System.out.println(list.stream().reduce((t1,t2)-> t1 + "," + t2));
        System.out.println(list.stream().count());
        System.out.println(integerList.stream().min(Comparator.comparingInt(Integer::intValue)));
        System.out.println(integerList.stream().max(Comparator.comparingInt(Integer::intValue)));
        System.out.println(list.stream().allMatch(t-> t.length() > 1));
        System.out.println(list.stream().anyMatch(t-> t.equals("Tom")));
        System.out.println(list.stream().noneMatch(t-> t.equals("s")));
        System.out.println(list.stream().findFirst());
        System.out.println(list.stream().findAny());
    }

    public static void testStreaming(){
        System.out.println(list.stream().map(it -> {
            System.out.println(it);
           return it + "s";
        }).map(it -> {
            System.out.println(it);
            return it + "_2s";
        }).map(it -> {
            System.out.println(it);
            return it + "_3s";
        }).collect(Collectors.toList()));
    }
}

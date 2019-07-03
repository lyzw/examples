//package com.sapling.example.base.classloader;
//
///**
// * @author weizhou
// * @version v1.0
// * @date 2018/12/2
// * @since v1.0
// */
//public class ClassLoderDemo1 {
//    static boolean flag = false;
//
//    static {
//        System.out.println("ClassLoderDemo1.flag before static init  -> " + flag);
//        flag = true;
//        System.out.println("ClassLoderDemo1.flag after static init -> " + flag);
//
//    }
//
//    public ClassLoderDemo1(){
//        System.out.println("init function ClassLoderDemo1");
//    }
//
//    public  void testFlag(){
//        System.out.println(flag);
//        flag = false;
//        System.out.println(flag);
//    }
//
//}
//

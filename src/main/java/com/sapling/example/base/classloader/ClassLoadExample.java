package com.sapling.example.base.classloader;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/2
 * @since v1.0
 */
public class ClassLoadExample {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
//        getClassLoders(ClassLoadExample.class);
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println(System.getProperty("java.class.path"));
//        tryToLoadCustomString("java.lang.String");
//        tryToLoadCustomString("com.sapling.example.base.classloader.ClassLoderDemo1");
        printClassLoaderOfBasicClass();
//        printClassLoaderOfCustomerClass();
        tryClassLoaderGetResourceFunction(".");
        tryClassLoaderGetResourceFunction("/");
        tryClassLoaderGetResourceFunction("test.txt");
        tryClassLoaderGetResourceFunction("/test.txt");
        tryClassGetResource("/test.txt");
    }

    public static void tryToLoadCustomString(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = ClassLoadExample.class.getClassLoader();
        Class clazz = classLoader.loadClass(name);
        clazz.newInstance();

    }

    public static void tryClassLoaderGetResourceFunction(String name) throws IOException {
//        System.out.println();
//        System.out.println("======================tryClassLoaderGetResourceFunction========================");
//        System.out.println("---------public static getResource-----------");
//        System.out.println("ClassLoader.getSystemResource(\"" + name + "\") -> " + ClassLoader.getSystemResource(name));
//        Enumeration<URL> urls =  ClassLoader.getSystemResources(name);
//        System.out.println("ClassLoader.getSystemResources(\"" + name + "\") -> ");
//        while (urls.hasMoreElements()){
//            System.out.println("\t" + urls.nextElement());
//        }
//        System.out.println("---------public getResource-----------");
        ClassLoader classLoader = ClassLoadExample.class.getClassLoader();
        System.out.println("classLoader.getResource(\"" + name + "\") -> " + classLoader.getResource(name));
//        urls =  classLoader.getResources(name);
//        System.out.println("ClassLoader.getResource(\"/\") -> ");
//        while (urls.hasMoreElements()){
//            System.out.println("\t" + urls.nextElement());
//        }
    }

    public static void tryClassGetResource(String name){
        System.out.println();
        System.out.println("======================tryClassLoaderGetResourceFunction========================");
        System.out.println("---------public class getResource-----------");
        System.out.println(ClassLoadExample.class.getResource(name));
    }

    public static void printClassLoaderOfCustomerClass(){
        System.out.println("--------------- printClassLoaderOfCustomerClass ------------------");
        System.out.println("JSONObject.class.getClassLoader()-> " + JSONObject.class.getClassLoader());
        System.out.println();
    }

    public static void printClassLoaderOfBasicClass(){
        System.out.println("--------------- printClassLoaderOfBasicClass ------------------");

        System.out.println("ClassLoadExample.class.getClassLoader()-> " + ClassLoadExample.class.getClassLoader());
        System.out.println("String.class.getClassLoader()-> " + String.class.getClassLoader());
        System.out.println("Integer.class.getClassLoader()-> " + Integer.class.getClassLoader());
        System.out.println("Double.class.getClassLoader()-> " + Double.class.getClassLoader());
        System.out.println("Character.class.getClassLoader()-> " + Character.class.getClassLoader());
        System.out.println("Long.class.getClassLoader()-> " + Long.class.getClassLoader());
        System.out.println("Short.class.getClassLoader()-> " + Short.class.getClassLoader());
        System.out.println("Math.class.getClassLoader()-> " + Math.class.getClassLoader());
        System.out.println();

    }

    public static void getClassLoders(Class clazz){
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);
        while (classLoader!=null){
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}



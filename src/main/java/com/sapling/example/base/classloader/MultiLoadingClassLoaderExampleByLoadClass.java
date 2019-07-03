package com.sapling.example.base.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 普通使用覆盖loadClass方式重写破坏双亲委派模型
 */
public class MultiLoadingClassLoaderExampleByLoadClass {

    private static String defaultClassPath = "d:/java/classloader/test/";

    private static class CustomerClassLoader1 extends ClassLoader {


        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = new byte[0];
                bytes = getClassBytes(defaultClassPath, name);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                return super.loadClass(name);
            }

        }
    }

    private static class CustomerClassLoader2 extends ClassLoader {

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                byte[] bytes = new byte[0];
                bytes = getClassBytes(defaultClassPath, name);
                return defineClass(name, bytes, 0, bytes.length);
            } catch (IOException e) {
                return super.loadClass(name);
            }
        }
    }


    private static byte[] getClassBytes(String path, String name) throws ClassNotFoundException, IOException {
        String myPath = defaultClassPath + name.replace(".", "/") + ".class";
        System.out.println(myPath);
        byte[] classBytes = null;
        File file = new File(myPath);
        try (FileInputStream in = new FileInputStream(file);) {
            classBytes = new byte[(int) file.length()];
            in.read(classBytes);
        }
        return classBytes;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class clazz1 = new CustomerClassLoader1().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");
        clazz1.newInstance();

        Class clazz2 =  new CustomerClassLoader2().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");

        clazz2.newInstance();
//        Thread thread = new Thread();
//        thread.setContextClassLoader(new CustomerClassLoader1());
//        Class clazz = thread.getContextClassLoader().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");
//        clazz.newInstance();
//
//        Thread thread2 = new Thread();
//        thread2.setContextClassLoader(new CustomerClassLoader2());
//        Class clazz2 = thread2.getContextClassLoader().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");
//        clazz2.newInstance();
    }
}

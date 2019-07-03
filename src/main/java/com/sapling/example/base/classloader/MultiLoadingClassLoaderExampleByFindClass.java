package com.sapling.example.base.classloader;

import com.sapling.common.tools.common.ReflectUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author wei.zhou
 * @date 2019/4/4
 * @since 1.0
 */
public class MultiLoadingClassLoaderExampleByFindClass {


    private static String defaultClassPath = "d:/java/classloader/test/";

    private static class CustomerClassLoader1 extends ClassLoader {


        @Override
        public Class<?> findClass(String name) throws ClassNotFoundException {
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
        public Class<?> findClass(String name) throws ClassNotFoundException {
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

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz1 = new CustomerClassLoader1().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");
        Class clazz2 =  new CustomerClassLoader2().loadClass("com.sapling.example.base.classloader.ClassLoderDemo1");

        ReflectUtil.invokeMethod(clazz1.newInstance(),"testFlag",null,null);
        ReflectUtil.invokeMethod(clazz2.newInstance(),"testFlag",null);


    }


}

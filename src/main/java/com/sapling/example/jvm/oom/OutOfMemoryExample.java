package com.sapling.example.jvm.oom;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/26
 * @since v1.0
 */
public class OutOfMemoryExample {
    static final int M = 2 << 20;

    /**
     * 测试大数据内存溢出，启动参数为-Xmx4m -Xms4m
     */
    public static void testOutOfMemoryWithBigData() {
        File file = new File("/Users/weizhou/workspace/examples/logs/testFile.log");
        try (InputStream inputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[8 * M];
            inputStream.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * -Xmx5m -Xms5m -Xmn3M -XX:NewRatio=3 -XX:+PrintGCDetails
     */
    public static void testOutOfMemory() {
        Map map = new HashMap<>();
        for(int index = 0 ;index < 10;index ++){
            System.out.println(index);
            byte[] bytes = new byte[1*M];
            map.put(index,bytes);
        }
    }




    public static void main(String[] args) {
        String[] table = {"a","b","c"};
        for (String[] tab = table;;){
            System.out.println(tab);
        }
    }
}

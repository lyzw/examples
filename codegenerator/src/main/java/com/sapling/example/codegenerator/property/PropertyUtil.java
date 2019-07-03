package com.sapling.example.codegenerator.property;

import java.io.*;
import java.util.Properties;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/29 14:53
 */
public class PropertyUtil {

    private static final String defaultPropertyFilePath = "code-generator.properties";

    static Properties properties = new Properties();

    static {
        String filePath = PropertyUtil.class.getClassLoader().getResource(defaultPropertyFilePath).getPath();
        try(InputStream inputStream = new FileInputStream(new File(filePath))) {
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println(properties);
    }

}

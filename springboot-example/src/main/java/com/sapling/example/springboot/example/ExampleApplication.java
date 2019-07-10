package com.sapling.example.springboot.example;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/3/26
 * @since v1.0
 */
@SpringBootApplication
public class ExampleApplication {

    public static void main(String[] args) throws IOException {
//        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        Enumeration<URL> urls = classLoader ==null ?
//                ClassLoader.getSystemResources("META-INF/spring.factories")
//                : classLoader.getResources("META-INF/spring.factories");
//        while (urls.hasMoreElements()){
//            try {
//                UrlResource resource = new UrlResource(urls.nextElement().getPath());
////                System.out.println(IoStreamUtil.toString(resource.getInputStream()));
//                System.out.println(PropertiesLoaderUtils.loadProperties(resource));
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
        SpringApplication.run(ExampleApplication.class, args);
    }
}

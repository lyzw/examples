package com.sapling.common.tools.stream;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author wei.zhou
 * @date 2019/7/10
 * @since 1.0
 */
public class StreamUtil {

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public static void main(String[] args) {
        List<Demo> list = Arrays.asList(new Demo("1","a",new Date()),
                new Demo("1","b",new Date()),
                new Demo("2","b",new Date()),
                new Demo("3","c",new Date())
                );
        list.stream().filter(distinctByKey(f-> f.getA() + new SimpleDateFormat("yyyy-MM-dd").format(f.getC()))).forEach(System.out::println);
    }

    static class Demo{
        private String a;
        private String b;
        private Date c;

        public Demo(String a, String b, Date c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public Date getC() {
            return c;
        }

        public void setC(Date c) {
            this.c = c;
        }

        @Override
        public String toString(){
            return a + b + c;
        }
    }
}

package com.sapling.example.base.lambda;

import java.util.function.*;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/21
 * @since v1.0
 */
public class LambdaExample {




    public static void explicitLambdaExpress() {
        System.out.println("==================Normal lambda interfaces(Explicit lambda interfaces) =============================");
        Integer value = 100;
        System.out.println("lambda Consumer(return void )");
        Consumer<Integer> c = (x)-> System.out.println("execute println x -> x= " + value + " => " +x);
        c.accept(1000);

        System.out.println("lambda function(return some value)");
        Function<Integer,String> intValue = (x) -> "value[" + x +"]";
        System.out.println("execute x= "  + value +  " -> (value[x]) => " + intValue.apply(value));

        System.out.println("lambda Predicate(return boolean )");
        Predicate<Integer> predicate = (x)-> x >100;
        System.out.println("execute x= " + value +  " -> (x >100)? => "+predicate.test(value));

        System.out.println("lambda Supplier(like factory)");
        Supplier<Integer> supplier = ()-> value;
        System.out.println("execute （return " + value +  ") => " + supplier.get());

        System.out.println("lambda UnaryOperator(return the same type of value)");
        UnaryOperator<Integer> unaryOperator = x -> x +100;
        System.out.println("execute （return " + value +  " + 100) => " + unaryOperator.apply(value));

        System.out.println("lambda Supplier(accept two same type param then return the same type result)");
        BinaryOperator<Integer> binaryOperator = (x,y) -> x * y;
        System.out.println("execute （return " + value +  " *"   + value +  ") => " + binaryOperator.apply(value,value));

        System.out.println("lambda Supplier(accept x with type u and y with type v then return the result with type x )");
        BiFunction<Float,Integer,String > biFunction = (x,y) -> "float value -> " + x + ", and  int value -> " + y ;
        System.out.println("execute （return \"float value -> " + 100.1f + ", and  int value ->"  + value + "\") => " + biFunction.apply(100.1f,value));

    }



    /**
     * lambda 匿名类实现
     */
    public static void lambdaAnonymousClassDemo() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("this is normal Anonymous class ");
            }
        });
        t.start();
        Thread t2 = new Thread(() -> {
            System.out.println("this is lambda Anonymous class ");
        });
        t2.start();
    }

    public static void main(String[] args) {
        LambdaExample.lambdaAnonymousClassDemo();
        LambdaExample.explicitLambdaExpress();
    }

    public static class Test{
        static int x = 100;

        int y = 101;

        public Test(){

        }

        public static String testInfo(){
            return "Test[" + x +"]";
        }

        public int getY(){
            return y;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "y=" + y +
                    '}';
        }
    }
}


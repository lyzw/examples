package com.sapling.example.base.lambda;

import java.io.UnsupportedEncodingException;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/12/26
 * @since v1.0
 */
public class LambdaTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String dd = "sfs防守打法";
        byte[] targets = new byte[4];
        System.out.println(new String(targets));
        System.out.println(new String(dd.getBytes("gbk"),"gbk"));


    }
}

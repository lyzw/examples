package com.sapling.http.common.constant;

/**
 * description:
 *
 * @author wei.zhou
 * @date 2019/4/28 18:13
 */
public class BaseRetCode {
    public static final String RET_CODE_PATTERN = "%5s_%6s";

    public static final String RET_CODE_SUCCESS = "000000";

    public static final String RET_CODE_UNKNOWN_ERROR = "999999";


    public static void main(String[] args) {
        String v = String.format(RET_CODE_PATTERN,"aaaaa","000000");
        System.out.println("[" + v + "]");
    }
}

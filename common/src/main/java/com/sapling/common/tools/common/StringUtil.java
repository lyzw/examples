package com.sapling.common.tools.common;

import com.sapling.common.tools.collection.ArrayUtil;
import javafx.beans.property.Property;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * @author zhouwei
 * @version v1.0
 * @date 2018/6/6
 * @since v1.0
 */
public class StringUtil {

    private static final String UNDER_LINE = "_";


    /**
     * 判断字符串是否为空或者“”，判断是否为“”的时候会对字符串进行trim处理
     *
     * @param value 待检测字符串
     * @return 如果为空或者“”，返回true，否则返回false
     */
    public static boolean isEmpty(String value) {
        if (isNull(value)) {
            return true;
        } else if ("".equals(value.trim())) {
            return true;
        }
        return false;
    }


    /**
     * 判断字符串是否为空或者“”，判断是否为“”的时候会对字符串进行trim处理
     *
     * @param value   待检测字符串
     * @param trimOpr 是否进行trim操作
     * @return 如果为空或者“”，返回true，否则返回false
     */
    public static boolean isEmpty(String value, boolean trimOpr) {
        if (isNull(value)) {
            return true;
        }
        if (trimOpr) {
            return isEmpty(value);
        } else if ("".equals(value)) {
            return true;
        }
        return false;
    }


    /**
     * 检测字符串是否为空
     *
     * @param value 待检测的字符串
     * @return 如果字符串为空则
     */
    public static boolean isNull(String value) {
        return value == null ? true : false;
    }


    /**
     * 首字母大写
     *
     * @param value 给定的字符串
     * @return 返回首字母大写的字符串
     */
    public static String upperFirstLetter(String value) {
        if (isEmpty(value)) {
            return value;
        }
        if (value.length() == 1) {
            return value.toUpperCase();
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    /**
     * 字符串转驼峰：将带下划线的字符串转换为驼峰形式的字符串
     *
     * @param value 带下划线的字符串
     * @return 返回转换为驼峰形式的字符串
     */
    public static String toCamel(String value) {
        if (isEmpty(value)) {
            return value;
        }
        if (!value.contains(UNDER_LINE)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        String[] arrays = value.split(UNDER_LINE);
        sb.append(arrays[0]);
        Arrays.asList(arrays).subList(1, arrays.length).forEach(item -> sb.append(upperFirstLetter(item)));
        return sb.toString();
    }


    /**
     * 驼峰字符串转换为下划线
     *
     * @param value 驼峰字符串转换为下划线
     * @return 返回转换为下划线形式的字符串
     */
    public static String toUnderLine(String value) {
        if (isEmpty(value)) {
            return value;
        }
        StringBuilder sb = new StringBuilder();

        char[] chars = value.toCharArray();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * 重复字符串操作，注意为1的时候，则表示原字符串
     *
     * @param value 待重复的字符串
     * @param times 重复次数
     * @return 返回重复后的字符串
     */
    public static String repeat(String value, int times) {
        if (isEmpty(value, false)) {
            return value;
        } else if (times == 0) {
            return value;
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < times; i++) {
                sb.append(value);
            }
            return sb.toString();
        }
    }

    /**
     * 按照位置拆分字符串：如果位置大于等于当前的字符串长度，则直接返回前一为当前字符串，后一为null的字符串数组
     *
     * @param source 源字符串
     * @param index  拆分的位置
     * @return 拆分后的字符串数组
     */
    public static String[] splitOnIndex(String source, int index) {
        // 如果长度大于等于当前的字符串，则直接返回，否则进行截断处理
        if (index >= source.length()) {
            return new String[]{source, null};
        }
        String[] ret = new String[2];
        ret[0] = source.substring(0, index);
        ret[1] = source.substring(index);
        return ret;
    }

    public static String[] splitOnIndexes(String source, Integer... index) {
        if (index == null || index.length <= 0) {
            return new String[]{source};
        }
        List<Integer> indexes = Arrays.asList(index);
        Collections.sort(indexes);
        String[] ret = new String[index.length + 1];
        for (int i = 0; i < indexes.size(); i++) {
            ret[i] = (i == 0) ? source.substring(0, indexes.get(i)) : source.substring(indexes.get(i - 1), indexes.get(i));
        }
        ret[index.length] = source.substring(indexes.get(indexes.size() - 1));
        return ret;
    }


    /**
     *
     * @param value
     * @param defaultValue
     * @return
     */
    public static String getOrDefault(String value, String defaultValue) {
        if (isNull(value)) {
            return defaultValue;
        }
        return value;
    }

    public static void main(String[] args) {
        System.out.println(repeat("1234", 3));
        System.out.println(ArrayUtil.join(
                Arrays.asList(splitOnIndexes("12345678901234567890asdfghjkoiuytrexchuytfbfsafbsdfbqwusudasufabsfasfasbfasfasuf", 1, 2, 3, 4, 5, 6, 7)
                ), ","));
    }


}

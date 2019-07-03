package com.sapling.common.tools.collection;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/2
 * @since v1.0
 */
public class ArrayUtil {

    public static String join(List<Object> arrays, String join) {
        return join(arrays, "", join, "");
    }

    public static String join(List<Object> arrays, String prefix, String join, String suffix) {
        if (arrays == null) {
            return "";
        }
        if (join == null) {
            throw new IllegalArgumentException("Illegal Argument: value is null!");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        arrays.forEach(item -> sb.append(
                item == null ? "" : String.valueOf(item)).append(join));
        return sb.substring(0, sb.length() - join.length()) + suffix;
    }

    public static byte[] concat(byte[]... args) {
        if (args.length == 0) {
            return null;
        }
        Integer sumLength = Arrays.stream(args).collect(Collectors.summingInt(f -> f.length));
        byte[] result = new byte[sumLength];
        Integer index = 0;
        for (byte[] src : args) {
            System.arraycopy(src, 0, result, index, src.length);
            index += src.length;
        }
        return result;
    }


}

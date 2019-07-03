package com.sapling.common.tools.common;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/9/20
 * @since v1.0
 */
public class NullUtil {

    public static Object getOrElseDefault(Object value, Object defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value;
    }

    public static Map nullToEmptyMap(Map value) {
        return (Map) getOrElseDefault(value, Collections.emptyMap());
    }

    public static <T> List nullToEmptyList(List value) {
        return (List<T>) getOrElseDefault(value, Collections.emptyList());
    }
}

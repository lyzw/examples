package com.sapling.common.tools.text;

import java.util.Map;

/**
 * @author wei.zhou02
 * @date 2019/4/28 14:40
 * @since v1.0
 */
public class PlaceHolderUtil {

    public static final String PLACE_HOLDER_PREFIX = "${";
    public static final String PLACE_HOLDER_SUFFIX = "}";

    public static String calculatePlaceHolder(Map<String, String> properties, String value) {
        String ret = value;
        for (Map.Entry item : properties.entrySet()) {
            ret = ret.replace(PLACE_HOLDER_PREFIX + item.getKey() + PLACE_HOLDER_SUFFIX, (String) item.getValue());
        }
        return ret;
    }

    
}

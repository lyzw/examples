package com.sapling.common.tools.file;

import java.io.UnsupportedEncodingException;

import com.sapling.common.tools.collection.ArrayUtil;
import com.sapling.common.tools.common.ByteUtil;

/**
 * @author weizhou
 * @version v1.0
 * @date 2019/1/24
 * @since v1.0
 */
public class CsvFileHelper {


    public static byte[] createCsvBytes(String content, BomEnum item, String charset) throws UnsupportedEncodingException {
        if (charset == null) {
            charset = "UTF-8";
        }
        if (item == null) {
            return content.getBytes();
        }
        return ArrayUtil.concat(item.getBomBytes(), content.getBytes(charset));
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = createCsvBytes("中文,anc,asf", null, null);
        FileUtil.createFile(
                "/Users/weizhou/workspace/examples/1.csv", bytes
                , true);
    }

}

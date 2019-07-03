package com.sapling.common.tools.file;

import java.util.Arrays;
import java.util.Optional;

/**
 * Byte Order Mark Enum
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/1/24
 * @since v1.0
 */
public enum BomEnum {

    /**
     * the Byte Order Mark Enum of utf-8
     */
    BOM_UTF_8(BomHelper.BOM_CHARSET_UTF_8, BomHelper.BOM_UTF_8),

    /**
     * the Byte Order Mark Enum of utf_16 with big endian
     */
    BOM_UTF_16_BIG_ENDIAN(BomHelper.BOM_CHARSET_UTF_16_BIG_ENDIAN, BomHelper.BOM_UTF_16_BIG_ENDIAN),

    /**
     * the Byte Order Mark Enum of utf_16 with little endian
     */
    BOM_UTF_16_LITTLE_ENDIAN(BomHelper.BOM_CHARSET_UTF_16_LITTLE_ENDIAN, BomHelper.BOM_UTF_16_LITTLE_ENDIAN),

    /**
     * the Byte Order Mark Enum of utf_32 with big endian
     */
    BOM_UTF_32_BIG_ENDIAN(BomHelper.BOM_CHARSET_UTF_32_BIG_ENDIAN, BomHelper.BOM_UTF_32_BIG_ENDIAN),

    /**
     * the Byte Order Mark Enum of utf_32 with little endian
     */
    BOM_UTF_32_LITTLE_ENDIAN(BomHelper.BOM_CHARSET_UTF_32_LITTLE_ENDIAN, BomHelper.BOM_UTF_32_LITTLE_ENDIAN),

    /**
     * the Byte Order Mark Enum of gb18030
     */
    BOM_GB_18030(BomHelper.BOM_CHARSET_GB_18030, BomHelper.BOM_GB_18030),
    ;

    private byte[] bomBytes;
    private String charset;

    BomEnum(String charset, byte[] bomBytes) {
        this.bomBytes = bomBytes;
        this.charset = charset;

    }

    public static BomEnum parse(String name) {
        Optional<BomEnum> item = Arrays.stream(BomEnum.values())
                .filter(f -> name.toUpperCase().equals(f.name()))
                .findFirst();
        if (item.isPresent()) {
            return item.get();
        } else {
            return null;
        }
    }

    public static BomEnum getByCharset(String charset) {
        Optional<BomEnum> item = Arrays.stream(BomEnum.values())
                .filter(f -> charset.toLowerCase().equals(f.getCharset()))
                .findFirst();
        if (item.isPresent()) {
            return item.get();
        } else {
            return null;
        }
    }

    public byte[] getBomBytes() {
        return bomBytes;
    }

    public String getCharset() {
        return charset;
    }
}

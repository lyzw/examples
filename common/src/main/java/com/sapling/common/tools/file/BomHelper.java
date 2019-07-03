package com.sapling.common.tools.file;

/**
 * Byte Order Mark
 *
 * @author weizhou
 * @version v1.0
 * @date 2019/1/24
 * @since v1.0
 */
public class BomHelper {

    public static final byte[] BOM_UTF_8 = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    public static final byte[] BOM_UTF_16_BIG_ENDIAN = new byte[]{(byte) 0xFE, (byte) 0xFF};

    public static final byte[] BOM_UTF_16_LITTLE_ENDIAN = new byte[]{(byte) 0xFF, (byte) 0xFE};

    public static final byte[] BOM_UTF_32_BIG_ENDIAN = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF};

    public static final byte[] BOM_UTF_32_LITTLE_ENDIAN = new byte[]{(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00};

    public static final byte[] BOM_GB_18030 = new byte[]{(byte) 0x84, (byte) 0x31, (byte) 0x95, (byte) 0x33};

    public static final String BOM_CHARSET_UTF_8 = "BOM_CHARSET_UTF_8";

    public static final String BOM_CHARSET_UTF_16_BIG_ENDIAN = "BOM_CHARSET_UTF_16_BIG_ENDIAN";

    public static final String BOM_CHARSET_UTF_16_LITTLE_ENDIAN = "BOM_CHARSET_UTF_16_LITTLE_ENDIAN";

    public static final String BOM_CHARSET_UTF_32_BIG_ENDIAN = "BOM_CHARSET_UTF_32_BIG_ENDIAN";

    public static final String BOM_CHARSET_UTF_32_LITTLE_ENDIAN = "BOM_CHARSET_UTF_32_LITTLE_ENDIAN";

    public static final String BOM_CHARSET_GB_18030 = "BOM_CHARSET_GB_18030";


}

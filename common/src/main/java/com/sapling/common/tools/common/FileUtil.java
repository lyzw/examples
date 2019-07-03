package com.sapling.common.tools.common;

import java.io.*;
import java.rmi.AccessException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sapling.common.tools.system.SystemInfoUtil;

import sun.plugin2.util.SystemUtil;

/**
 * @author weizhou
 * @version v1.0
 * @date 2018/7/10
 * @since v1.0
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static final Integer K = 1024;
    public static final Integer M = 1024 * K;
    public static final Integer G = 1024 * M;


    public static final Integer maxByteSize = 10 * M;


    public static byte[] toBytes(File file) throws IOException {
        if (file == null || !file.exists() || !file.isFile()) {
            return null;
        }
        if (!file.canRead()) {
            throw new AccessException("access permission error! ");
        }
        if (file.getTotalSpace() > maxByteSize) {
            return null;
        } else {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return bytes;
        }
    }

    public static File writeTempFile(byte[] bytes) throws IOException {
        String tempPath = System.getProperty("java.io.tmpdir");
        if (!tempPath.endsWith(File.separator)) {
            tempPath = tempPath + File.pathSeparator;
        }
        String fileName = tempPath + UUID.randomUUID().toString();
        File tempFile = new File(fileName);
        BufferedOutputStream bufferedInputStream = null;
        try {
            bufferedInputStream = new BufferedOutputStream(new FileOutputStream(tempFile));
            bufferedInputStream.write(bytes);
            bufferedInputStream.flush();
        } finally {
            try {
                bufferedInputStream.close();
            } catch (Exception e) {}
        }
        return tempFile;
    }

    public static void createParentDirectory(File file) {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
    }

    public static void main(String[] args) throws IOException {
        String regexx = "(\\S*//)?(?<ip>\\S*):(?<port>\\d*).*";
        Pattern pattern = Pattern.compile(regexx);
        Matcher matcher = pattern.matcher("hdfs://192.168.31.95:9000/");
        System.out.println(matcher.matches());
        System.out.println(matcher.group("ip"));
        System.out.println(matcher.group("port"));
    }
}

package com.sapling.common.tools.system;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网络相关信息
 *
 * @author zhouwei
 * @version v1.0
 * @createdate 2017/6/9.
 * @since v1.0
 */
public class NetworkInfoUtil {

    private static Logger logger = LoggerFactory.getLogger(NetworkInfoUtil.class);

    public static String getHostIp() throws UnknownHostException {
        String ip = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while(networkInterfaces.hasMoreElements()){
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()){
                    InetAddress address = addresses.nextElement();
                    logger.debug(address.getHostAddress());
                    if (address != null && !address.isAnyLocalAddress()
                            && !address.isLoopbackAddress() && address instanceof Inet4Address){
                        ip = address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static String getHostName() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        return inetAddress.getHostName();
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(System.getProperties());
        System.out.println(System.getenv());
        System.out.println(getHostName());
        System.out.println(getHostIp());
        System.out.println((extractIndex(" index=transferanalysis_jyfx_259194030297088* | fields @timestamp as timestamp, duration as duration, trans_count as trans_count, trans_successful as trans_successful")));
        System.out.println((extractIndex("index =ssss_ssss")));
        System.out.println((extractIndex("index =sss-ssss")));
        System.out.println((extractIndex("index =ssss\\ssss")));
        System.out.println((extractIndex("index =     ssssssss")));
    }



    public static String extractIndex(String spl) {
        if(spl == null || "".equals(spl)){
            return null;
        }
        String regex = ".*index\\s*=\\s*(?<index>\\S*)\\s*.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(spl);
        if(matcher.matches()){
            return matcher.group("index");
        }
        //        String substring = spl.substring(0, spl.indexOf("|"));
        //        if (StringUtils.isNotEmpty(substring)) {
        //            String[] split = substring.split("=");
        //            return StringUtils.isNotEmpty(split[1]) ? split[1].trim() : null;
        //        }
        return null;
    }



}

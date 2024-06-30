package com.ruoyi.web.controller.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IPAddressUtil {

    public static String getLocalIPAddress() {
        try {
            // 尝试获取本地主机名和 IP 地址
            InetAddress localHost = InetAddress.getLocalHost();
            String localHostAddress = localHost.getHostAddress();

            // 验证 IP 地址是否为 127.0.0.1（即 localhost），如果是，进一步获取网卡 IP 地址
            if (localHostAddress.equals("127.0.0.1")) {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = networkInterfaces.nextElement();
                    Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress inetAddress = inetAddresses.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress.isSiteLocalAddress()) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            } else {
                return localHostAddress;
            }
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String ipAddress = getLocalIPAddress();
        if (ipAddress != null) {
            System.out.println("本机 IP 地址: " + ipAddress);
        } else {
            System.out.println("无法获取本机 IP 地址");
        }
    }
}

package com.lsh.birthday.utils.ip;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * IP帮助工具
 * 
 */
public class IPHelper {
	/**
	 * 得到用户的真实地址,如果有多个就取第一个
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		String[] ips = ip.split(",");
		return ips[0].trim();
	}
	
	/** 
	 * Tony
     * 多IP处理，可以得到最终ip 
     * @return 
     */  
    public static String getIp() {  
        String localip = null;// 本地IP，如果没有配置外网IP则返回它  
        String netip = null;// 外网IP  
        try {  
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface  
                    .getNetworkInterfaces();  
            InetAddress ip = null;  
            boolean finded = false;// 是否找到外网IP  
            while (netInterfaces.hasMoreElements() && !finded) {  
                NetworkInterface ni = netInterfaces.nextElement();  
                Enumeration<InetAddress> address = ni.getInetAddresses();  
                while (address.hasMoreElements()) {  
                    ip = address.nextElement();  
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()  
                            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP  
                        netip = ip.getHostAddress();  
                        finded = true;  
                        break;  
                    } else if (ip.isSiteLocalAddress()  
                            && !ip.isLoopbackAddress()  
                            && ip.getHostAddress().indexOf(":") == -1) {// 内网IP  
                        localip = ip.getHostAddress();  
                    }  
                }  
            }  
        } catch (SocketException e) {  
            e.printStackTrace();  
        }  
        if (netip != null && !"".equals(netip)) {  
            return netip;  
        } else {  
            return localip;  
        }  
    }  
    
	
	/**
	 * 根据IP地址获取所在区域
	 * @param ipAddress
	 * @return
	 */
	public static String getIpAddress(String ipAddress) {
		return  IPSeeker.getInstance().getAddress(ipAddress);
	}

	public static void main(String[] args) {
		String ip = getIp();
		System.out.println(ip);
		String ipAddress = getIpAddress(ip);
		System.out.println(ipAddress);
	}

}

package com.lsh.birthday.utils.ip;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * IP帮助工具
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
     *
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
     * 调用接口获取ip地址
     *
     * @param ip
     */
    public static String getAddressByIp(String ip) {
        //查询Ip信息的接口，返回json 61.153.252.254
        String url = "http://ip.geo.iqiyi.com/cityjson?format=json&ip=" + ip;
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
//		{"code":"A00000","data":{"country":"中国","province":"浙江","city":"金华","country_id":48,"province_id":10,"city_id":10007,"location_id":26010007,"isp_id":26,"isp":"电信","longitude":0,"latitude":0,"ip":"61.153.252.254"}}
        //得到的json数据
        /*System.out.println(result);
        //解析,
        JSONObject jsonObj = JSON.parseObject(result);
        JSONArray jarr = jsonObj.getJSONArray("data");
        JSONObject j0 = (JSONObject) jarr.get(0);
        //输出该ip对应的地理位置
        System.out.println(j0.get("location"));*/
		return result;
    }

    /**
     * 根据IP地址获取所在区域
     *
     * @param ipAddress
     * @return
     */
    public static String getIpAddress(String ipAddress) {
        return IPSeeker.getInstance().getAddress(ipAddress);
    }

    public static void main(String[] args) {
        /*String ip = getIp();
        System.out.println(ip);
        String ipAddress = getIpAddress(ip);
        System.out.println(ipAddress);*/
		String addressByIp = getAddressByIp("61.153.252.254");
		System.out.println(addressByIp);
	}

}

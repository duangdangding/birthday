package com.lsh.birthday.utils.ip;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

public class IpUtils {
    public IpUtils() {
    }

    public static byte[] getIpByteArrayFromString(String ip) {
        if (AppUtils.isBlank(ip)) {
            return null;
        } else {
            byte[] ret = new byte[4];
            StringTokenizer st = new StringTokenizer(ip, ".");

            try {
                ret[0] = (byte)(Integer.parseInt(st.nextToken()) & 255);
                ret[1] = (byte)(Integer.parseInt(st.nextToken()) & 255);
                ret[2] = (byte)(Integer.parseInt(st.nextToken()) & 255);
                ret[3] = (byte)(Integer.parseInt(st.nextToken()) & 255);
            } catch (Exception var4) {
            }

            return ret;
        }
    }

    public static String getString(String s, String srcEncoding, String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException var4) {
            return s;
        }
    }

    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException var3) {
            return new String(b);
        }
    }

    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException var5) {
            return new String(b, offset, len);
        }
    }

    public static String getIpStringFromBytes(byte[] ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(ip[0] & 255);
        sb.append('.');
        sb.append(ip[1] & 255);
        sb.append('.');
        sb.append(ip[2] & 255);
        sb.append('.');
        sb.append(ip[3] & 255);
        return sb.toString();
    }
}

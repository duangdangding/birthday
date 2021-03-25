package com.lsh.birthday.utils.ip;

import org.apache.oro.text.regex.MalformedPatternException;

import java.io.*;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.CRC32;

public class AppUtils {
//    private static final Logger logger = LoggerFactory.getLogger(AppUtils.class);

    public AppUtils() {
    }

    public static String getDisplayDate(Calendar pCalendar) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return pCalendar != null ? format.format(pCalendar.getTime()) : "";
    }

    public static Calendar str2Calendar(String str) {
        Calendar cal = null;
        if (isNotBlank(str)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                Date d = sdf.parse(str);
                cal = Calendar.getInstance();
                cal.setTime(d);
            } catch (ParseException var4) {
            }
        }

        return cal;
    }

    public static String getCurrentDate() {
        return getDisplayDate(GregorianCalendar.getInstance());
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() <= 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isBlank(Object[] objs) {
        return objs == null || objs.length <= 0;
    }

    public static boolean isNotBlank(Object[] objs) {
        return !isBlank(objs);
    }

    public static boolean isBlank(Object objs) {
        return objs == null || "".equals(objs);
    }

    public static boolean isNotBlank(Object objs) {
        return !isBlank(objs);
    }

    public static boolean isBlank(Collection obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Collection obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Set obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Set obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Serializable obj) {
        return obj == null;
    }

    public static boolean isNotBlank(Serializable obj) {
        return !isBlank(obj);
    }

    public static boolean isBlank(Map obj) {
        return obj == null || obj.size() <= 0;
    }

    public static boolean isNotBlank(Map obj) {
        return !isBlank(obj);
    }

    public static String[] list2Strings(List<String> list) {
        String[] value = null;

        try {
            if (list == null) {
                return null;
            }

            value = new String[list.size()];

            for(int i = 0; i < list.size(); ++i) {
                value[i] = (String)list.get(i);
            }
        } catch (Exception var3) {
            System.err.println("list is null: " + var3);
        }

        return value;
    }

    public static String list2String(List<Object> list) {
        if (isBlank((Collection)list)) {
            return "";
        } else {
            StringBuffer sbuf = new StringBuffer();
            sbuf.append(list.get(0));

            for(int idx = 1; idx < list.size(); ++idx) {
                sbuf.append(",");
                sbuf.append(list.get(idx));
            }

            return sbuf.toString();
        }
    }

    public static List<String> Strings2List(String[] args) {
        ArrayList list = new ArrayList();

        try {
            if (args == null) {
                return null;
            }

            for(int i = 0; i < args.length; ++i) {
                list.add(args[i]);
            }
        } catch (Exception var3) {
            System.err.println("list is null: " + var3);
        }

        return list;
    }

    public static String[] getStrings(String str) {
        List<String> values = getStringCollection(str);
        return values.size() == 0 ? null : (String[])values.toArray(new String[values.size()]);
    }

    public static List<String> getStringCollection(String str) {
        List<String> values = new ArrayList();
        if (str == null) {
            return values;
        } else {
            StringTokenizer tokenizer = new StringTokenizer(str, ",");
            values = new ArrayList();

            while(tokenizer.hasMoreTokens()) {
                values.add(tokenizer.nextToken());
            }

            return values;
        }
    }

    public static Long getCRC32(String value) {
        CRC32 crc32 = new CRC32();
        crc32.update(value.getBytes());
        return crc32.getValue();
    }

    public static String convertTemplate(String fileName, String pattern, Map values) throws MalformedPatternException {
        String record = null;
        StringBuffer sb = new StringBuffer();

        try {
            File inFile = new File(fileName);
            if (!inFile.exists()) {
                return sb.toString();
            }

            FileInputStream fileInputStream = new FileInputStream(inFile);
            BufferedReader inBufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
            new String();

            while((record = inBufferedReader.readLine()) != null) {
                sb.append(StringUtil.convert(record, pattern, values) + "\n");
            }

            inBufferedReader.close();
            fileInputStream.close();
        } catch (IOException var8) {
            System.out.println("got an IOException error!");
            var8.printStackTrace();
        }

        return sb.toString();
    }

    public static String convertTemplate(String fileName, Map values) throws MalformedPatternException {
        return convertTemplate(fileName, "\\#[a-zA-Z]+\\#", values);
    }

    public static String arrayToString(String[] strs) {
        if (strs.length == 0) {
            return "";
        } else {
            StringBuffer sbuf = new StringBuffer();
            sbuf.append(strs[0]);

            for(int idx = 1; idx < strs.length; ++idx) {
                sbuf.append(",");
                sbuf.append(strs[idx]);
            }

            return sbuf.toString();
        }
    }

    public static String getDefaultValue(String value, String defaultValue) {
        return isNotBlank(value) ? value : defaultValue;
    }

    public static String encodePassword(String password, String algorithm) {
        byte[] unencodedPassword = password.getBytes();
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (Exception var7) {
            var7.printStackTrace();
            return password;
        }

        md.reset();
        md.update(unencodedPassword);
        byte[] encodedPassword = md.digest();
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < encodedPassword.length; ++i) {
            if ((encodedPassword[i] & 255) < 16) {
                buf.append("0");
            }

            buf.append(Long.toString((long)(encodedPassword[i] & 255), 16));
        }

        return buf.toString();
    }

    public static String md5(String password) {
        return encodePassword(password, "md5");
    }

    public static String sha1(String password) {
        return encodePassword(password, "sha1");
    }

    public static boolean isTheSame(Object a, Object b) {
        if (a == null && b == null) {
            return true;
        } else {
            return a != null && a.equals(b);
        }
    }

    public static String removeYanText(String nickName) {
        if (isBlank(nickName)) {
            return "";
        } else {
            byte[] b_text = nickName.getBytes();

            for(int i = 0; i < b_text.length; ++i) {
                if ((b_text[i] & 248) == 240) {
                    for(int j = 0; j < 4; ++j) {
                        b_text[i + j] = 0;
                    }

                    i += 3;
                }
            }

            return new String(b_text);
        }
    }
}

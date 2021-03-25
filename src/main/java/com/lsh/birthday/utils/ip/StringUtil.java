package com.lsh.birthday.utils.ip;

import org.apache.oro.text.regex.*;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class StringUtil {
    public StringUtil() {
    }

    public static int intToString(String string) {
        return Integer.parseInt(string);
    }

    public static String stringToInt(int i) {
        return String.valueOf(i);
    }

    public static String stringToInt(Integer I) {
        return String.valueOf(I);
    }

    public static String arrayToString(String[] s, String delim) {
        StringBuffer buf = new StringBuffer();

        for(int i = 0; i < s.length; ++i) {
            buf.append(s[i]).append(delim);
        }

        return buf.toString().substring(0, buf.length() - 1);
    }

    public static String[] stringtoArray(String source, String delim) {
        String[] wordLists;
        if (source == null) {
            wordLists = new String[]{source};
            return wordLists;
        } else {
            if (delim == null) {
                delim = ",";
            }

            StringTokenizer st = new StringTokenizer(source, delim);
            int total = st.countTokens();
            wordLists = new String[total];

            for(int i = 0; i < total; ++i) {
                wordLists[i] = st.nextToken();
            }

            return wordLists;
        }
    }

    public static String[] stringtoArray(String source, char delim) {
        return stringtoArray(source, String.valueOf(delim));
    }

    public static String[] stringtoArray(String source) {
        return stringtoArray(source, ",");
    }

    public static void printStrings(String[] strings, String delim, OutputStream out) {
        try {
            if (strings == null) {
                out.write("null".getBytes());
            } else {
                int length = strings.length - 1;
                int i = 0;

                while(true) {
                    if (i >= length) {
                        if (strings[length] != null) {
                            if (strings[length].indexOf(delim) > -1) {
                                out.write(("\"" + strings[length] + "\"").getBytes());
                            } else {
                                out.write(strings[length].getBytes());
                            }
                        } else {
                            out.write("null".getBytes());
                        }
                        break;
                    }

                    if (strings[i] != null) {
                        if (strings[i].indexOf(delim) > -1) {
                            out.write(("\"" + strings[i] + "\"" + delim).getBytes());
                        } else {
                            out.write((strings[i] + delim).getBytes());
                        }
                    } else {
                        out.write("null".getBytes());
                    }

                    ++i;
                }
            }

            out.write("\n".getBytes());
        } catch (IOException var5) {
        }

    }

    public static void printStrings(String[] strings, String delim) {
        printStrings(strings, delim, System.out);
    }

    public static void printStrings(String[] strings, OutputStream out) {
        printStrings(strings, ",", out);
    }

    public static void printStrings(String[] strings) {
        printStrings(strings, ",", System.out);
    }

    public static String getReplaceString(String prefix, String source, String[] values) {
        String result = source;
        if (source != null && values != null && values.length >= 1) {
            if (prefix == null) {
                prefix = "%";
            }

            for(int i = 0; i < values.length; ++i) {
                String argument = prefix + Integer.toString(i + 1);
                int index = result.indexOf(argument);
                if (index != -1) {
                    String temp = result.substring(0, index);
                    if (i < values.length) {
                        temp = temp + values[i];
                    } else {
                        temp = temp + values[values.length - 1];
                    }

                    temp = temp + result.substring(index + 2);
                    result = temp;
                }
            }

            return result;
        } else {
            return source;
        }
    }

    public static String getReplaceString(String source, String[] values) {
        return getReplaceString("%", source, values);
    }

    public static boolean contains(String[] strings, String string, boolean caseSensitive) {
        for(int i = 0; i < strings.length; ++i) {
            if (caseSensitive) {
                if (strings[i].equals(string)) {
                    return true;
                }
            } else if (strings[i].equalsIgnoreCase(string)) {
                return true;
            }
        }

        return false;
    }

    public static boolean contains(String[] strings, String string) {
        return contains(strings, string, true);
    }

    public static boolean containsIgnoreCase(String[] strings, String string) {
        return contains(strings, string, false);
    }

    public static String combineStringArray(String[] array, String delim) {
        int length = array.length - 1;
        if (delim == null) {
            delim = "";
        }

        StringBuffer result = new StringBuffer(length * 8);

        for(int i = 0; i < length; ++i) {
            result.append(array[i]);
            result.append(delim);
        }

        result.append(array[length]);
        return result.toString();
    }

    public static String fillString(char c, int length) {
        String ret = "";

        for(int i = 0; i < length; ++i) {
            ret = ret + c;
        }

        return ret;
    }

    public static String trimLeft(String value) {
        String result = value;
        if (value == null) {
            return value;
        } else {
            char[] ch = value.toCharArray();
            int index = -1;

            for(int i = 0; i < ch.length && Character.isWhitespace(ch[i]); index = i++) {
            }

            if (index != -1) {
                result = value.substring(index + 1);
            }

            return result;
        }
    }

    public static String trimRight(String value) {
        String result = value;
        if (value == null) {
            return value;
        } else {
            char[] ch = value.toCharArray();
            int endIndex = -1;

            for(int i = ch.length - 1; i > -1 && Character.isWhitespace(ch[i]); endIndex = i--) {
            }

            if (endIndex != -1) {
                result = value.substring(0, endIndex);
            }

            return result;
        }
    }

    public static String escapeCharacter(String source, HashMap escapeCharMap) {
        if (source != null && source.length() != 0) {
            if (escapeCharMap.size() == 0) {
                return source;
            } else {
                StringBuffer sb = new StringBuffer();
                StringCharacterIterator sci = new StringCharacterIterator(source);

                for(char c = sci.first(); c != '\uffff'; c = sci.next()) {
                    String character = String.valueOf(c);
                    if (escapeCharMap.containsKey(character)) {
                        character = (String)escapeCharMap.get(character);
                    }

                    sb.append(character);
                }

                return sb.toString();
            }
        } else {
            return source;
        }
    }

    public static int getByteLength(String source) {
        int len = 0;

        for(int i = 0; i < source.length(); ++i) {
            char c = source.charAt(i);
            int highByte = c >>> 8;
            len += highByte == 0 ? 1 : 2;
        }

        return len;
    }

    public static int getSubtringCount(String source, String sub) {
        if (source != null && source.length() != 0) {
            int count = 0;

            for(int index = source.indexOf(sub); index >= 0; index = source.indexOf(sub, index + 1)) {
                ++count;
            }

            return count;
        } else {
            return 0;
        }
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

    public static String decodeString(String str) {
        return new String(Base64.decodeBase64(str));
    }

    public static String convert(String text, String patternString, Map map) throws MalformedPatternException {
        StringBuffer textBuf = new StringBuffer(text);
        PatternCompiler compiler = new Perl5Compiler();
        Pattern pattern = compiler.compile(patternString);
        PatternMatcher matcher = new Perl5Matcher();
        PatternMatcherInput input = new PatternMatcherInput(text.toString());
        int offset = 0;
        String key = null;

        try {
            while(matcher.contains(input, pattern)) {
                MatchResult result = matcher.getMatch();
                if (AppUtils.isNotBlank(result)) {
                    int len = result.length();
                    key = result.toString();
                    String value = (String)map.get(key);
                    textBuf.replace(result.beginOffset(0) + offset, result.endOffset(0) + offset, value);
                    offset += value.length() - len;
                }
            }
        } catch (Exception var13) {
            throw new RuntimeException("can not replace key  " + key, var13);
        }

        return textBuf.toString();
    }

    public static String convert(String text, String[] values) {
        StringBuffer textBuf = new StringBuffer(text);
        int offset = 0;

        while(textBuf.indexOf("?") != -1) {
            textBuf.replace(textBuf.indexOf("?"), textBuf.indexOf("?") + 1, values[offset]);
            ++offset;
            if (offset > values.length - 1) {
                break;
            }
        }

        return textBuf.toString();
    }
}

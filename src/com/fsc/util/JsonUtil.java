package com.fsc.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * <p>Title: 系统框架</p>
 * <p>Description:JSON工具类</p>
 * <p>创建日期:2010-11-18</p>
 * @author thh
 * @version 1.0
 * <p>湖南家校圈科技有限公司</p>
 * <p>http://www.139910.com/</p>
 * <p>http://old.139910.com:8080/</p>
 */
public class JsonUtil {
    static String string2Json(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 20);
        sb.append('\"');

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            switch (c) {
            case '\"':
                sb.append("\\\"");

                break;

            case '\\':
                sb.append("\\\\");

                break;

            case '/':
                sb.append("\\/");

                break;

            case '\b':
                sb.append("\\b");

                break;

            case '\f':
                sb.append("\\f");

                break;

            case '\n':
                sb.append("\\n");

                break;

            case '\r':
                sb.append("\\r");

                break;

            case '\t':
                sb.append("\\t");

                break;

            default:
                sb.append(c);
            }
        }

        sb.append('\"');

        return sb.toString();
    }

    static String number2Json(Number number) {
        return number.toString();
    }

    static String boolean2Json(Boolean bool) {
        return bool.toString();
    }

    static String array2Json(Object[] array) {
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder(array.length << 4);
        sb.append('[');

        for (Object o : array) {
            sb.append(toJson(o));
            sb.append(',');
        }

        // 将最后添加的 ',' 变为 ']': 
        sb.setCharAt(sb.length() - 1, ']');

        return sb.toString();
    }

    static String map2Json(Map<String, Object> map) {
        if (map.isEmpty()) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder(map.size() << 4);
        sb.append('{');

        Set<String> keys = map.keySet();

        for (String key : keys) {
            Object value = map.get(key);
            sb.append('\"');
            sb.append(key);
            sb.append('\"');
            sb.append(':');
            sb.append(toJson(value));
            sb.append(',');
        }

        // 将最后的 ',' 变为 '}': 
        sb.setCharAt(sb.length() - 1, '}');

        return sb.toString();
    }

    public static String toJson(Object o) {
        if (o == null) {
            return "null";
        }

        if (o instanceof String) {
            return string2Json((String) o);
        }

        if (o instanceof Boolean) {
            return boolean2Json((Boolean) o);
        }

        if (o instanceof Number) {
            return number2Json((Number) o);
        }

        if (o instanceof Map) {
            return map2Json((Map<String, Object>) o);
        }

        if (o instanceof Object[]) {
            return array2Json((Object[]) o);
        }

        throw new RuntimeException("Unsupported type: " +
            o.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
        map.put("user", "thh");
        System.out.println(JsonUtil.toJson(map));
    }
}

package com.fsc.util;

import javax.servlet.http.Cookie;


public class CookieManager {
    public static String getCookieValue(Cookie[] cookies, String key) {
        String value = "";

        if (cookies == null) {
            return value;
        }

        for (Cookie c : cookies) {
            if (key.equals(c.getName())) {
                value = c.getValue();
            }
        }

        return value;
    }
}

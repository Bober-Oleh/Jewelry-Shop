package com.epam.jewelry_shop.util;

import javax.servlet.http.Cookie;

public class GetterFromCookie {

	public static Cookie getCookie(Cookie[] cookies, String cookieName) {
		Cookie cookie = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (cookieName.equals(c.getName())) {
					cookie = c;
					break;
				}
			}
		}

		return cookie;
	}
}

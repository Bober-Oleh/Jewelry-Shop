package com.epam.jewelry_shop.filter;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.util.GetterFromCookie;

/**
 * Servlet Filter implementation class LocaleFilter
 */
@WebFilter("/LocaleFilter")
public class LocaleFilter implements Filter {
	private String locales;
	private String defaultLocale;
	private String typeStore;

	/*
	 * lacale* Default constructor.
	 */
	public LocaleFilter() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		String chooseLocale = null;
		HttpSession session = request.getSession();
		Object store = null;
		if (typeStore.equals("session")) {
			store = session.getAttribute("locale");

		}
		if (typeStore.equals("mycookie")) {
			store = GetterFromCookie.getCookie(request.getCookies(), "userlocale");
			if (store != null) {
				chooseLocale = GetterFromCookie.getCookie(request.getCookies(), "userlocale").getValue();
			}
		}

		Enumeration locales = request.getLocales();
		String[] arLocales = this.locales.split(",");
		session.setAttribute("locales", arLocales);

		if (request.getParameter("lang") != null) {

			String currentURL = request.getRequestURL().toString();
			chooseLocale = request.getParameter("lang");

		}

		else if (store == null) {

			while (locales.hasMoreElements()) {
				String localeFromBrowser = locales.nextElement().toString();
				for (String locale : arLocales) {

					if (locale.equals(localeFromBrowser)) {
						chooseLocale = locale;
						break;

					}
					if (chooseLocale != null) {
						break;
					}
				}
			}
			if (chooseLocale == null) {
				chooseLocale = this.defaultLocale;

			}

		} else {
			chain.doFilter(request, response);
			return;
		}

		if (chooseLocale != null) {
			if (typeStore.equals("session") && chooseLocale != null) {
				session.setAttribute("locale", chooseLocale);
			} else if (typeStore.equals("mycookie")) {
				if (store == null) {
				} else {
					((Cookie) store).setValue(chooseLocale);

				}
				response.addCookie(new Cookie("userlocale", chooseLocale));

			}
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		locales = fConfig.getInitParameter("locales");
		defaultLocale = fConfig.getInitParameter("defaultLocale");
		typeStore = fConfig.getInitParameter("typeStore");
	}
}

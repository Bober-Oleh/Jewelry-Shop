package com.epam.jewelry_shop.filter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.epam.jewelry_shop.model.User;
import com.epam.jewelry_shop.util.CheckForUrlsFilter;
import com.epam.jewelry_shop.util.WebConstants;
import com.epam.jewelry_shop.util.XmlSecurityReader;

public class RoleFilter implements Filter {
	String securityURL;
	HashMap<String, List<String>> rolesForUrls;
	CheckForUrlsFilter check = new CheckForUrlsFilter();

	public void init(FilterConfig config) throws ServletException {
		securityURL = config.getInitParameter("securityURL");
		File file = new File(securityURL);
		try {
			rolesForUrls = new XmlSecurityReader().XmlPropertiesReader(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		String role;
		String currentUrl = httpRequest.getRequestURI();

		if (check.filterPageAccess(rolesForUrls, currentUrl)) {
			chain.doFilter(request, response);
		} else if (session.getAttribute("registeredUser") == null) {
			session.setAttribute("DesiredURL", currentUrl);
			httpResponse.sendRedirect(WebConstants.URL + "/login");
		} else if (check.filterRoleAccess(rolesForUrls, currentUrl,
				((User) session.getAttribute("registeredUser")).getRole())) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect("accessDenied.jsp");
		}

	}
}

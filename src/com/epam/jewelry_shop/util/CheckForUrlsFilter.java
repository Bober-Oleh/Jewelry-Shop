package com.epam.jewelry_shop.util;

import java.util.HashMap;
import java.util.List;

public class CheckForUrlsFilter {
	public boolean filterPageAccess(HashMap<String, List<String>> rolesForUrls, String currentUrl) {
		for (String key : rolesForUrls.keySet()) {
			if (currentUrl.equals(WebConstants.URL + key)) {
				return false;
			}
		}
		return true;
	}

	public boolean filterRoleAccess(HashMap<String, List<String>> rolesForUrls, String currentUrl, String currentRole) {
		String key = currentUrl.substring(currentUrl.lastIndexOf("/"));
		for (String role : rolesForUrls.get(key)) {
			if (role.equals(currentRole)) {
				return true;
			}
		}
		return false;
	}
}

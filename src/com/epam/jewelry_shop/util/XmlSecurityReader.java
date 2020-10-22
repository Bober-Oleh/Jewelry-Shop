package com.epam.jewelry_shop.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XmlSecurityReader {

	public HashMap XmlPropertiesReader(File xmlFile) throws InvalidPropertiesFormatException, IOException {
		Map<String, List<String>> security = new HashMap();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(xmlFile);
			document.getDocumentElement().normalize();

			NodeList nodeList = document.getElementsByTagName("constraint");

			for (int i = 0; i < nodeList.getLength(); i++) {
				NodeList sec = nodeList.item(i).getChildNodes();
				String key = null;
				List<String> value = new ArrayList();
				for (int j = 0; j < sec.getLength(); j++) {
					if (sec.item(j).getNodeName().equals("url-pattern")) {
						key = sec.item(j).getTextContent();
					} else if (sec.item(j).getNodeName().equals("role")) {
						value.add(sec.item(j).getTextContent());

					}

				}
				security.put(key, value);

			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return (HashMap) security;
	}
}
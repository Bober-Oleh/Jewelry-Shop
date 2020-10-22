package com.epam.jewelry_shop.util;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.Part;

public class WriteFileOnServer {
	public String writeFile(String path, Collection<Part> parts) throws IOException {
		for (Part part : parts) {

			String filePartName = extractFileName(part);
			if (filePartName != null && filePartName.length() > 0) {
				String filePath = path + "\\" + filePartName;
				String fileName = filePartName;
				part.write(filePath);
				return fileName;
			}
		}
		return null;
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {

				String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
				clientFileName = clientFileName.replace("\\", "/");
				int i = clientFileName.lastIndexOf('/');

				return clientFileName.substring(i + 1);
			}
		}
		return null;
	}

}
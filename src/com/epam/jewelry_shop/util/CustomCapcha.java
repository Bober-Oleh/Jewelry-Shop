package com.epam.jewelry_shop.util;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * A class used to generate custom tag content.
 * 
 * @autor Oleh Bober
 */
public class CustomCapcha extends SimpleTagSupport {
	/**
	 * method for generating custom tag
	 */
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		
		String output = "<div><img src=\"capcha\"></div>" + "<div class=\"form-group\">\r\n"
				+ "            <label for=\"captcha\">Enter password from image</label>\r\n"
				+ "            <input type=\"text\" class=\"form-control\" id=\"captcha\" name=\"capcha\">\r\n" + "\r\n"
				+ "        </div>";

		out.println(output);

	}
}

package com.epam.jewelry_shop.util;

import java.util.Random;

/**
 * A class used to generate a random number used as a captcha.
 * 
 * @autor Oleh Bober
 */
public class CapchaFactory {
	/**
	 * random number generation method
	 *
	 * @param longCapcha - captcha length in string form
	 * @return returns a random number
	 */
	public static int CreateCapcha(String longCapcha) {
		String min = "1";
		String max = "9";
		for (int i = 0; i < Integer.parseInt(longCapcha) - 1; i++) {
			min += "0";
			max += "9";
		}
		Random random = new Random();
		return Integer.parseInt(min) + random.nextInt(Integer.parseInt(max) - Integer.parseInt(min));
	}
}

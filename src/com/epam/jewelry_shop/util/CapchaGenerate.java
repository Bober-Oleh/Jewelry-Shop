package com.epam.jewelry_shop.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

/**
 * Class used to generate captcha images.
 * 
 * @autor Oleh Bober
 */
public class CapchaGenerate {
	/**
	 * image generation method
	 * 
	 * @param randomNumber - the number that should be placed in the image
	 * @return returns captcha image
	 */

	public static BufferedImage drowCapcha(int randomNumber) {
		Random random = new Random();

		int widthNumber = Integer.toString(randomNumber).length();
		int Height = 40;
		int Width = widthNumber * 25;

		Font fntStyle1 = new Font("Arial", Font.BOLD, 30);
		int iTotalChars = Integer.toString(randomNumber).length();
		BufferedImage captchaImage = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2dImage = (Graphics2D) captchaImage.getGraphics();
		int iCircle = 15;
		for (int i = 0; i < iCircle; i++) {
			g2dImage.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			int iRadius = (int) (Math.random() * Height / 2.0);
			int iX = (int) (Math.random() * Width - iRadius);
			int iY = (int) (Math.random() * Height - iRadius);
		}
		g2dImage.setFont(fntStyle1);
		for (int i = 0; i < iTotalChars; i++) {
			g2dImage.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			if (i % 2 == 0) {
				g2dImage.drawString(Integer.toString(randomNumber).substring(i, i + 1), 25 * i, 24);
			} else {
				g2dImage.drawString(Integer.toString(randomNumber).substring(i, i + 1), 25 * i, 35);
			}
		}
		return captchaImage;
	}
}
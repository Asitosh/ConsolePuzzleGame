package com.auto1.game.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
/**
 * Class is used to print text in enlarged format with given chars
 * @author pathak
 *
 */
public class AsciiTextPrinter {

	/**
	 * Method to print ASCII text
	 * @param textToBePrinted actual text to be printed
	 * @param backGroundChar character to be used in background
	 * @param actualTextChar character to be used to print text
	 */
	public static void print(String textToBePrinted, char backGroundChar, char actualTextChar) {
		BufferedImage image = new BufferedImage(72, 16, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		g.setFont(new Font("Dialog", Font.BOLD, 12));
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics.drawString(textToBePrinted, 3, 12);

		for (int y = 0; y < 16; y++) {
		    StringBuilder sb = new StringBuilder();
		    for (int x = 0; x < 72; x++)
		        sb.append(image.getRGB(x, y) == -16777216 ? backGroundChar : actualTextChar);
		    if (sb.toString().trim().isEmpty()) continue;
		    System.out.println(sb);
		}
		
	}

}

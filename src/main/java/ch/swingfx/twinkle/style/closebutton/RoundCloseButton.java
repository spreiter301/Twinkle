package ch.swingfx.twinkle.style.closebutton;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A simple round close button.
 * @author Heinrich Spreiter
 *
 */
public class RoundCloseButton implements ICloseButton {
	private static final int DEFAULT_Y = 9;
	private static final int DEFAULT_X = 9;
	private static final int SIZE = 23;
	public static final int STROKE_WIDTH = 3;
	public static final int TOP_LEFT = 8;
	public static final int BOTTOM_RIGHT = 14;

	private Ellipse2D.Double fButtonShape;
	private BasicStroke fStroke;
	private Color fButtonBackgroundColor;
	private Color fButtonBorderColor;
	private BufferedImage fDropShadow;
	private int fX;
	private int fY;
	
	/**
	 * Create a RoundCloseButton at default x and y
	 * @param buttonBackgroundColor the background color of the button
	 * @param buttonBorderColor the border and X color of the button
	 */
	public RoundCloseButton(Color buttonBackgroundColor, Color buttonBorderColor) {
		this(buttonBackgroundColor, buttonBorderColor, DEFAULT_X, DEFAULT_Y);
	}
	
	/**
	 * Create a RoundCloseButton at x and y
	 * @param buttonBackgroundColor the background color of the button
	 * @param buttonBorderColor the border and X color of the button
	 * @param x x position
	 * @param y y position
	 */
	public RoundCloseButton(Color buttonBackgroundColor, Color buttonBorderColor, int x, int y) {
		withPosition(x, y);
		fStroke = new BasicStroke(STROKE_WIDTH);
		fButtonBackgroundColor = buttonBackgroundColor;
		fButtonBorderColor = buttonBorderColor;
		try {
			fDropShadow = ImageIO.read(RoundCloseButton.class.getResourceAsStream("/ch/swingfx/twinkle/style/closebutton/resources/roundCloseButtonDropShadow.png"));
		} catch (IOException e) {
			// don't show the drop shadow
		}
	}
	
	public void paintCloseButton(Graphics g) {
		final Graphics2D copy = (Graphics2D) g.create();
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(fDropShadow != null) {
			copy.drawImage(fDropShadow, fX, fY, null);
		}
		// draw the background
		copy.setColor(fButtonBackgroundColor);
		copy.fill(fButtonShape);

		// draw the border around the button
		copy.setStroke(fStroke);
		copy.setColor(fButtonBorderColor);
		copy.draw(fButtonShape);

		// draw the X
		copy.drawLine(fX + TOP_LEFT, fY + TOP_LEFT, fX + BOTTOM_RIGHT, fY + BOTTOM_RIGHT);
		copy.drawLine(fX + BOTTOM_RIGHT, fY + TOP_LEFT, fX + TOP_LEFT, fY + BOTTOM_RIGHT);

		copy.dispose();
	}

	public boolean isMouseOverCloseButton(Point mousePosition) {
		if(mousePosition == null || fButtonShape == null) {
			return false;
		}
		return fButtonShape.contains(mousePosition);
	}

	public ICloseButton withPosition(int x, int y) {
		fX = x;
		fY = y;
		fButtonShape = new Ellipse2D.Double(fX, fY, SIZE, SIZE);
		return this;
	}

}

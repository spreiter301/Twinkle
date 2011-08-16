/*
 * This library is dual-licensed: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3 as
 * published by the Free Software Foundation. For the terms of this
 * license, see licenses/gpl_v3.txt or <http://www.gnu.org/licenses/>.
 *
 * You are free to use this library under the terms of the GNU General
 * Public License, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * Alternatively, you can license this library under a commercial
 * license, as set out in licenses/commercial.txt.
 */

package ch.swingfx.twinkle.style.closebutton;


import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * A simple rectangle close button.
 * @author Heinrich Spreiter
 *
 */
public class RectangleCloseButton implements ICloseButton {
	private static final int DEFAULT_Y = 9;
	private static final int DEFAULT_X = 9;
	private static final int SIZE = 16;
	public static final int TOP_LEFT = 5;
	public static final int BOTTOM_RIGHT = 11;
	public static final int STROKE_WIDTH = 1;

	private Rectangle fButtonShape;
	private int fX;
	private int fY;
	private final BasicStroke fStroke;
	private final Color fButtonBorderColor;
	private final Color fButtonBackgroundColor;

	public RectangleCloseButton(Color buttonBackgroundColor, Color buttonBorderColor) {
		this(buttonBackgroundColor, buttonBorderColor, DEFAULT_X, DEFAULT_Y);
	}

	public RectangleCloseButton(Color buttonBackgroundColor, Color buttonBorderColor, int x, int y) {
		withPosition(x, y);
		fStroke = new BasicStroke(STROKE_WIDTH);
		fButtonBackgroundColor = buttonBackgroundColor;
		fButtonBorderColor = buttonBorderColor;
	}

	public void paintCloseButton(Graphics g) {
		final Graphics2D copy = (Graphics2D) g.create();
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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
		fButtonShape = new Rectangle(fX, fY, SIZE, SIZE);
		return this;
	}
}

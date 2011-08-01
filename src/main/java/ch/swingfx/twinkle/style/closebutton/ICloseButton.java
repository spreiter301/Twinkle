package ch.swingfx.twinkle.style.closebutton;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Interface for creating close buttons on the notification
 * @author Heinrich Spreiter
 *
 */
public interface ICloseButton {
	/**
	 * Paint the close button
	 * @param g {@link Graphics} to paint on
	 */
	public void paintCloseButton(Graphics g);
	/**
	 * Test if the mouse is over the close button
	 * @param mousePosition position of the mouse
	 * @return true if mouse if over the button
	 */
	public boolean isMouseOverCloseButton(Point mousePosition);
	/**
	 * Set the position of the close button
	 * @param x x position
	 * @param y y position
	 * @return this {@link ICloseButton}
	 */
	public ICloseButton withPosition(int x, int y);
}

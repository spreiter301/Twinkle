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

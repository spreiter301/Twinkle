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

package ch.swingfx.twinkle.style.background;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;

import ch.swingfx.twinkle.NotificationBuilder;

/**
 * Interface for creating backgrounds for the notification
 * @author Heinrich Spreiter
 *
 */
public interface IBackground {
	/**
	 * Paint the background
	 * @param g graphics to paint on
	 * @param isMouseOver true if the mouse if over the notification
	 * @param cornerRadius corner radius of the window
	 */
	public void paintBackground(Graphics g, boolean isMouseOver, int cornerRadius);
	/**
	 * Set the alpha of this component. Usual you don't call this
	 * method. Instead use {@link NotificationBuilder}.withAlpha(float)<br />
	 * Note this only works if the {@link GraphicsConfiguration} supports per-pixel translucency.
	 * @param alpha value between 0.0f (completely transparent) and 1.0f (opaque)
	 */
	public void setAlpha(float alpha);
}

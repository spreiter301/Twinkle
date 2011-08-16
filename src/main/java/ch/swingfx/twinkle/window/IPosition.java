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

package ch.swingfx.twinkle.window;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JWindow;

import ch.swingfx.twinkle.style.INotificationStyle;

/**
 * Interface for positioning the notification.
 * @author Heinrich Spreiter
 *
 */
public interface IPosition {
	/**
	 * Calculate the position for <code>window</code>
	 * @param screenSize The screen size including the task/menu bar
	 * @param screenInsets Insets (task/menu bar) of the screen
	 * @param window The window we want to set the position for
	 * @param style The style of the notification.
	 * @return The position for the window
	 */
	public Point getPosition(Dimension screenSize, Insets screenInsets, JWindow window, INotificationStyle style);
}

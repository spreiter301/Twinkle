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

import java.awt.GraphicsConfiguration;

import javax.swing.Icon;
import javax.swing.JWindow;

import ch.swingfx.twinkle.style.AbstractNotificationStyle;
import ch.swingfx.twinkle.style.INotificationStyle;

/**
 * Use this interface to create your own notification windows. Use:<br />
 * <code>
 * {@link AbstractNotificationStyle}.withNotificationWindowCreator()
 * </code>
 * @author Heinrich Spreiter
 *
 */
public interface ICreateNotificationWindow {
	/**
	 * Creates a new notification window
	 * @param icon The icon of the notification
	 * @param title The title of the notification
	 * @param message The message of the notification
	 * @param style The {@link INotificationStyle} of the notification
	 * @param graphicsConfiguration THe {@link GraphicsConfiguration} for the window
	 * @return a new {@link JWindow}
	 */
	public JWindow createNotificationWindow(Icon icon, String title, String message, INotificationStyle style, GraphicsConfiguration graphicsConfiguration);
}

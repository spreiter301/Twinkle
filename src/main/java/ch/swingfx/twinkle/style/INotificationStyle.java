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

package ch.swingfx.twinkle.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.Map;

import ch.swingfx.twinkle.style.background.IBackground;
import ch.swingfx.twinkle.style.closebutton.ICloseButton;
import ch.swingfx.twinkle.style.overlay.IOverlay;
import ch.swingfx.twinkle.window.ICreateNotificationWindow;

/**
 * Provides styling for the notification
 * @author Heinrich Spreiter
 *
 */
public interface INotificationStyle {	
	/**
	 * The width of the notification
	 * @return The width of the notification 
	 */
	public int getWidth();
	
	/**
	 * Get the radius of the window corner
	 * @return the radius of the window corner
	 */
	public int getWindowCornerRadius();
	/**
	 * Get the title font color of the notification
	 * @return the font color of the notification
	 */
	public Color getTitleFontColor();
	
	/**
	 * Get the font for the title
	 * @return the font for the title
	 */
	public Font getTitleFont();
	
	/**
	 * Get the message font color of the notification
	 * @return the message font color of the notification
	 */
	public Color getMessageFontColor();
	
	/**
	 * Get the font for the message
	 * @return the font for the message
	 */
	public Font getMessageFont();
	
	/**
	 * Get the insets of the notification window
	 * @return the insets of the notification window
	 */
	public Insets getWindowInsets();
	
	
	/**
	 * Get the alpha that should be applied to a notification
	 * where the mouse is not over
	 * @return the alpha of the notification window
	 */
	public float getAlpha();
	
	/**
	 * Get the {@link IBackground} that should be applied
	 * to the notification
	 * @return the {@link IBackground} of the notification
	 */
	public IBackground getBackground();
	
	/**
	 * Get the {@link IOverlay} that should be applied
	 * to the notification
	 * @return The {@link IOverlay} of the notification
	 */
	public IOverlay getOverlay();
	
	/**
	 * Get the {@link ICloseButton} that should be applied
	 * to the notification
	 * @return The {@link ICloseButton} of the notification
	 */
	public ICloseButton getCloseButton();
	
	/**
	 * Get the notification window creator
	 * @return the notification window creator
	 */
	public ICreateNotificationWindow getNotificationWindowCreator();

	/**
	 * Get a map of custom parameters
	 * @return a map of custom parameters
	 */
	public Map<String, Object> getCustomParams();
}

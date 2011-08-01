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

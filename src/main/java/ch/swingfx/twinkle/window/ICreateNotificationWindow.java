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

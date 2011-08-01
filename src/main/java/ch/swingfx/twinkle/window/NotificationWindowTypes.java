package ch.swingfx.twinkle.window;

import java.awt.GraphicsConfiguration;

import javax.swing.Icon;
import javax.swing.JWindow;

import ch.swingfx.twinkle.style.INotificationStyle;

/**
 * Available notifications windows. 
 * @author Heinrich Spreiter
 *
 */
public enum NotificationWindowTypes implements ICreateNotificationWindow {
	/**
	 * The default notification window.
	 */
	DEFAULT  {
		public JWindow createNotificationWindow(Icon icon, String title, String message, INotificationStyle style, GraphicsConfiguration graphicsConfiguration) {
			return new DefaultNotificationWindow(icon, title, message, style, graphicsConfiguration);
		}
		
	}

}

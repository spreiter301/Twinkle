package ch.swingfx.twinkle.manager;

import javax.swing.JWindow;

/**
 * Interface to use if we want to create a new {@link INotificationManager}
 * @author Heinrich Spreiter
 *
 */
public interface INotificationManager {
	/**
	 * Show the notification
	 * @param window window that represents the notification
	 */
	public void showNotification(JWindow window);
}

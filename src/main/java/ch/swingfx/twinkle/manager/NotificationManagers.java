package ch.swingfx.twinkle.manager;

import javax.swing.JWindow;

/**
 * Provides different types of {@link INotificationManager}
 * @author Heinrich Spreiter
 *
 */
public enum NotificationManagers implements INotificationManager {
	/** Shows the notifications one after one */
	SEQUENTIAL {
		public void showNotification(JWindow window) {
			SequentialNotificationManager.showNotification(window);
		}
		
	}
}

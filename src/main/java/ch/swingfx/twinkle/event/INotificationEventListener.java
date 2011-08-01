package ch.swingfx.twinkle.event;


/**
 * Implement this listener if you want to listen for events from the notification.
 * @see NotificationEventAdapter
 * @author Heinrich Spreiter
 *
 */
public interface INotificationEventListener {
	
	/**
	 * Called if the notification is opened.
	 * @param event Details of the event
	 */
	public void opened(NotificationEvent event);
	
	/**
	 * Called if somebody clicked on the notification.
	 * @param event Details of the event
	 */
	public void clicked(NotificationEvent event);
	
	/**
	 * Called if the mouse is over the notification.
	 * @param event Details of the event
	 */
	public void mouseOver(NotificationEvent event);
	
	/**
	 * Called if the mouse leaves the notification.
	 * @param event Details of the event
	 */
	public void mouseOut(NotificationEvent event);
	
	/**
	 * Called if the notification is closed.
	 * @param event Details of the event
	 */
	public void closed(NotificationEvent event);
	
}

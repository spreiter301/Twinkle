package ch.swingfx.twinkle.event;


/**
 * An abstract adapter class for receiving notification events.
 * The methods in this class are empty. This class exists as convenience for creating listener objects. 
 * @see INotificationEventListener
 * @author Heinrich Spreiter
 *
 */
public abstract class NotificationEventAdapter implements INotificationEventListener{
	
	public void opened(NotificationEvent event) {
		
	}
	
	public void clicked(NotificationEvent event) {
		
	}
	
	public void mouseOver(NotificationEvent event) {
		
	}
	
	public void mouseOut(NotificationEvent event) {
		
	}
	
	public void closed(NotificationEvent event) {
		
	}
}

package ch.swingfx.twinkle.event;

import java.util.EventObject;
import java.util.UUID;

import ch.swingfx.twinkle.NotificationBuilder;

/**
 * Event Object used by the notification to send events
 * @author Heinrich Spreiter
 *
 */
public class NotificationEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	private final UUID fId;
	/**
	 * Create a new NotificationEvent
	 * @param source source of the event. Usual the {@link NotificationBuilder}
	 * @param id the id of the notification. This id is returned from {@link NotificationBuilder}.showNotification()
	 */
	public NotificationEvent(Object source, UUID id) {
		super(source);
		this.fId = id;
	}
	
	/**
	 * Get the id of the notification
	 * @return the id of the notification
	 */
	public UUID getId() {
		return fId;
	}

}

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

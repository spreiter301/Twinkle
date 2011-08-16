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

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

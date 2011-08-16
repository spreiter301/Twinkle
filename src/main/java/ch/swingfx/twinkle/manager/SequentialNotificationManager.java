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

package ch.swingfx.twinkle.manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JWindow;
import javax.swing.Timer;

/**
 * I manage the notification windows and show the one after one
 * @author Heinrich Spreiter
 *
 */
public class SequentialNotificationManager {

	private SequentialNotificationManager() {
		//
	}
	
	/** Delay before we show the notification */
	private static final int DELAY = 250;
	/** our lock for synchronization */
	private static final Lock sLock;
	/** list of all windows we want to display. guarded by lock */
	private static final LinkedList<JWindow> sWindows;
	/** true if a window is open. guarded by lock */
	private static boolean sWindowOpen = false;

	static {
		sLock = new ReentrantLock(true);
		sWindows = new LinkedList<JWindow>();
	}


	/**
	 * Shows the notification
	 * @param window window to show
	 */
	protected static void showNotification(final JWindow window) {
		try {
			sLock.lock();
			sWindows.addLast(window);
			window.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					window.removeWindowListener(this);
					sWindowOpen = false;
					nextWindow();
				}
			});
			nextWindow();
		} finally {
			sLock.unlock();
		}
	}

	/**
	 * shows the next window on the stack
	 */
	private static void nextWindow() {
		try {
			sLock.lock();
			if(!sWindowOpen && sWindows.size() > 0) {
				sWindowOpen = true;
				final JWindow window = sWindows.removeFirst();
				Timer delayVisibleTimer = new Timer(DELAY, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final Timer t = (Timer) e.getSource();
						t.stop();
						window.setVisible(true);
						window.getGlassPane().setVisible(true);

					}
				});
				delayVisibleTimer.start();
			}
		} finally {
			sLock.unlock();
		}
	}



}

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

package ch.swingfx.twinkle.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JWindow;

import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.closebutton.ICloseButton;
import ch.swingfx.twinkle.style.overlay.IOverlay;

/**
 * Glass pane for the notification. This component is responsible for displaying the {@link IOverlay}
 * found in {@link INotificationStyle}.getOverlay() and the {@link ICloseButton} found in {@link INotificationStyle}.getCloseButton()
 * @author Heinrich Spreiter
 *
 */
public class GlassPane extends JPanel {
	private final static long serialVersionUID = 1L;
	
	private final JWindow fWindow;
	private final INotificationStyle fStyle;
	private final int fCornerRadius;
	
	/**
	 * Create a new {@link GlassPane}
	 * @param window The glass pane is for this window
	 * @param style Style of the notification
	 * @param cornerRadius The corner radius of the window
	 */
	public GlassPane(JWindow window, INotificationStyle style, int cornerRadius) {
		super.setOpaque(false);
		fWindow = window;
		fStyle  = style;
		fCornerRadius = cornerRadius;
		
		fWindow.setGlassPane(this);
	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D copy = (Graphics2D) g.create();
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(fWindow.getMousePosition() != null) {
			fStyle.getOverlay().paintOverlayMouseOver(copy, fCornerRadius);
			fStyle.getCloseButton().paintCloseButton(copy);
		} else {
			fStyle.getOverlay().paintOverlayMouseOut(copy, fCornerRadius);
		}
		copy.dispose();
	}
}

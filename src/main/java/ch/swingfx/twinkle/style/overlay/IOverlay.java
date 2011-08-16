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

package ch.swingfx.twinkle.style.overlay;

import java.awt.Graphics;

import ch.swingfx.twinkle.window.GlassPane;

/**
 * This interface is used to paint overlay on the {@link GlassPane}.
 * @author Heinrich Spreiter
 *
 */
public interface IOverlay {
	/**
	 * Invoked when we should paint the overlay when the mouse is over
	 * @param g {@link Graphics} to paint on
	 * @param cornerRadius the corner radius of the window
	 */
	public void paintOverlayMouseOver(Graphics g, int cornerRadius);
	
	/**
	 * Invoked when we should paint the overlay when the mouse is out
	 * @param g {@link Graphics} to paint on
	 * @param cornerRadius the corner radius of the window
	 */
	public void paintOverlayMouseOut(Graphics g, int cornerRadius);
	
	/**
	 * Set when the overlay is painted
	 * @param paintMode paint mode to apply
	 */
	public void setOverlayPaintMode(OverlayPaintMode paintMode);
}

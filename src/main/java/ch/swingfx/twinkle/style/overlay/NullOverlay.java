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

/**
 * Empty implementation of {@link IOverlay}. Use this if you don't need any overlay.
 * @author Heinrich Spreiter
 *
 */
public class NullOverlay implements IOverlay {

	public void paintOverlayMouseOver(Graphics g, int cornerRadius) {
		// Do nothing
	}

	public void paintOverlayMouseOut(Graphics g, int cornerRadius) {
		// Do nothing
	}

	public void setOverlayPaintMode(OverlayPaintMode paintMode) {
		// Do nothing
	}

}

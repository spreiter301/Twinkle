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

/**
 * Tells us if we should paint the overlay always, or only on mouse over/out
 * @author Heinrich Spreiter
 *
 */
public enum OverlayPaintMode {
	/**
	 * Paint the overlay always.
	 */
	ALWAYS,
	/**
	 * Paint the overlay only if the mouse is over.
	 */
	MOUSE_OVER,
	/**
	 * Paint the overlay only if the mouse is out.
	 */
	MOUSE_OUT
}

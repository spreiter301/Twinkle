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

package ch.swingfx.twinkle.style.closebutton;

import java.awt.Graphics;
import java.awt.Point;

/**
 * A {@link NullCloseButton} is an invisible close button. So if you don't want to display a close button use this class.
 * @author Heinrich Spreiter
 *
 */
public class NullCloseButton implements ICloseButton {

	public void paintCloseButton(Graphics g) {
		// Do nothing

	}

	public boolean isMouseOverCloseButton(Point mousePosition) {
		return false;
	}

	public ICloseButton withPosition(int x, int y) {
		return this;
	}

}

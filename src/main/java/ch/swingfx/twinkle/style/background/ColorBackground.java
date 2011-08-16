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

package ch.swingfx.twinkle.style.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import ch.swingfx.color.ColorUtil;

/**
 * Paints a color as background
 * @author Heinrich Spreiter
 *
 */
public class ColorBackground implements IBackground {

	/** Color of the background */
	private Color fColor;
	/**
	 * Create a new {@link ColorBackground}
	 * @param color color of this background
	 */
	public ColorBackground(Color color) {
		fColor = color;
	}
	
	public void paintBackground(Graphics g, boolean isMouseOver, int cornerRadius) {
		final Graphics2D copy = (Graphics2D) g.create();
		final Shape clip = copy.getClip();
		copy.setColor(fColor);
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(cornerRadius == 0) {
			copy.fill(clip);
		} else {
			copy.fill(new RoundRectangle2D.Double(0, 0, clip.getBounds().getWidth(), clip.getBounds().getHeight(), cornerRadius, cornerRadius));
		}
		copy.dispose();
	}

	public void setAlpha(float alpha) {
		fColor = ColorUtil.withAlpha(fColor, alpha);
	}

}

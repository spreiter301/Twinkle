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
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

import ch.swingfx.color.ColorUtil;


/**
 * Paint a top to bottom gradient.
 * @author Heinrich Spreiter
 *
 */
public class GradientBackground implements IBackground {

	private Color fStartColor;
	private Color fEndColor;

	/**
	 * Create a gradient background
	 * @param startColor color to use from top
	 * @param endColor color to use at the bottom
	 */
	public GradientBackground(Color startColor, Color endColor) {
		this.fStartColor = startColor;
		this.fEndColor = endColor;
	}
	
	public void paintBackground(Graphics g, boolean isMouseOver, int cornerRadius) {
		final Graphics2D g2d = (Graphics2D) g.create();
		final Shape clip = g2d.getClip();
		final Rectangle bounds = clip.getBounds();
		
		final GradientPaint gp = new GradientPaint(new Point2D.Float(bounds.x, bounds.y), fStartColor, new Point2D.Float(bounds.x, bounds.y + bounds.height), fEndColor);
		g2d.setPaint(gp);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(cornerRadius == 0) {
			g2d.fill(clip);
		} else {
			g2d.fill(new RoundRectangle2D.Double(0, 0, clip.getBounds().getWidth(), clip.getBounds().getHeight(), cornerRadius, cornerRadius));
		}
		
		
		g2d.dispose();
	}

	public void setAlpha(float alpha) {
		fStartColor = ColorUtil.withAlpha(fStartColor, alpha);
		fEndColor = ColorUtil.withAlpha(fEndColor, alpha);
	}

}

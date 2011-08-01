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

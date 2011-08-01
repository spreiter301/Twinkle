package ch.swingfx.twinkle.style.overlay;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

/**
 * Paints a simple gradient from top to bottom
 * @author Heinrich Spreiter
 *
 */
public class GradientOverlay extends AbstractOverlay {

	private final Color fStartColor;
	private final Color fEndColor;

	/**
	 * Create a gradient overlay
	 * @param startColor top color
	 * @param endColor bottom color
	 * @param paintMode paint mode of the overlay
	 */
	public GradientOverlay(Color startColor, Color endColor, OverlayPaintMode paintMode) {
		this(startColor, endColor, paintMode, new NullOverlay());
	}
	
	/**
	 * Create a gradient overlay with a decorator
	 * @param startColor top color
	 * @param endColor bottom color
	 * @param paintMode paint mode of the overlay
	 * @param decorator Overlay we want to decorate
	 */
	public GradientOverlay(Color startColor, Color endColor, OverlayPaintMode paintMode, IOverlay decorator) {
		super(paintMode, decorator);
		this.fStartColor = startColor;
		this.fEndColor = endColor;
	}

	
	private void paintGradient(Graphics g, int cornerRadius) {
		final Graphics2D copy = (Graphics2D) g.create();
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final Shape clip = g.getClip();
		GradientPaint mask = new GradientPaint(0, 0, fStartColor, 0, clip.getBounds().height, fEndColor);
		copy.setPaint(mask);
		
		if(cornerRadius == 0) {
			copy.fill(clip);
		} else {
			copy.fill(new RoundRectangle2D.Double(0, 0, clip.getBounds().getWidth(), clip.getBounds().getHeight(), cornerRadius, cornerRadius));
		}
		
		copy.dispose();
	}

	@Override
	public void paintMouseOver(Graphics g, int cornerRadius) {
		paintGradient(g, cornerRadius);
	}

	@Override
	public void paintMouseOut(Graphics g, int cornerRadius) {
		paintGradient(g, cornerRadius);
	}
	
}

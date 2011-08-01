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

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

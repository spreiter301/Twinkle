package ch.swingfx.twinkle.style.overlay;

import java.awt.Graphics;

/**
 * Extend this class if you want to create your own {@link IOverlay} implementations.<br />
 * It implements the logic for {@link OverlayPaintMode} values.
 * @author Heinrich Spreiter
 *
 */
public abstract class AbstractOverlay implements IOverlay {
	private final IOverlay fDecorator;
	private OverlayPaintMode fPaintMode;
	
	public AbstractOverlay(OverlayPaintMode paintMode, IOverlay decorator) {
		this.fDecorator = decorator;
		this.fPaintMode = paintMode;
	}
	
	public void paintOverlayMouseOver(Graphics g, int cornerRadius) {
		switch(fPaintMode) {
			case ALWAYS:
			case MOUSE_OVER:
				paintMouseOver(g, cornerRadius);
				break;
		}
		fDecorator.paintOverlayMouseOver(g, cornerRadius);
	}

	public void paintOverlayMouseOut(Graphics g, int cornerRadius) {
		switch(fPaintMode) {
		case ALWAYS:
		case MOUSE_OUT:
			paintMouseOut(g, cornerRadius);
			break;
		}
		fDecorator.paintOverlayMouseOut(g, cornerRadius);
		
	}
	
	public void setOverlayPaintMode(OverlayPaintMode paintMode) {
		fPaintMode = paintMode;
		fDecorator.setOverlayPaintMode(paintMode);
	}
	
	/**
	 * Called by paintOverlayMouseOver if we should paint it.
	 * @param g graphics to paint on
	 * @param cornerRadius corner radius of the window
	 */
	public abstract void paintMouseOver(Graphics g, int cornerRadius);
	/**
	 * Called by paintOverlayMouseOut if we should paint it.
	 * @param g graphics to paint on
	 * @param cornerRadius corner radius of the window
	 */
	public abstract void paintMouseOut(Graphics g, int cornerRadius);
}

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

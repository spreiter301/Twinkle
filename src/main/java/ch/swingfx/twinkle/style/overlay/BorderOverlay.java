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

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

/**
 * Paints a border around the notification
 * @author Heinrich Spreiter
 *
 */
public class BorderOverlay extends AbstractOverlay {

    private BufferedImage fImage;
	private final Color fColor;
    private final int fWidth;

    /**
	 * Create a new {@link BorderOverlay}
	 * @param width width of the border
	 * @param color color of the border
	 * @param paintMode paint mode of the border
	 */
	public BorderOverlay(int width, Color color, OverlayPaintMode paintMode) {
		this(width, color, paintMode, new NullOverlay());
	}
	
	/**
	 * Create a new {@link BorderOverlay} with a decorator
	 * @param width width of the border
	 * @param color color of the border
	 * @param paintMode paint mode of the border
	 * @param decorator Overlay we want to decorate
	 */
	public BorderOverlay(int width, Color color, OverlayPaintMode paintMode, IOverlay decorator) {
		super(paintMode, decorator);
        fWidth = width;
		fColor = color;

	}
	
	@Override
	public void paintMouseOver(Graphics g, int cornerRadius) {
		paintBorder(g, cornerRadius);
	}

	@Override
	public void paintMouseOut(Graphics g, int cornerRadius) {
		paintBorder(g, cornerRadius);
	}

    /**
     * Create the border fImage. Using BasicStroke and draw doesn't
     * produce good looking result. Therefore we create a border fImage
     * that looks good.
     * @param g graphics
     * @param cornerRadius the corner radius of the window
     */
    private void createBorderImage(Graphics g, int cornerRadius) {
        if(fImage == null) {
            final Shape shape = g.getClip();

            fImage = new BufferedImage(shape.getBounds().width, shape.getBounds().height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = (Graphics2D) fImage.getGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(fColor);

            // first we just fill the whole rectangle
            g2d.fill(new RoundRectangle2D.Double(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, cornerRadius, cornerRadius));
            // then we clear the inner part of the rectangle
            // so there is only a border left.
            AlphaComposite composite = AlphaComposite.Clear;
            g2d.setComposite(composite);
            final int widthUsedByBorder = fWidth * 2;
            g2d.fill(new RoundRectangle2D.Double(new RoundRectangle2D.Double(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, cornerRadius, cornerRadius).getBounds().x + fWidth, new RoundRectangle2D.Double(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, cornerRadius, cornerRadius).getBounds().y + fWidth, new RoundRectangle2D.Double(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, cornerRadius, cornerRadius).getBounds().width - widthUsedByBorder, new RoundRectangle2D.Double(shape.getBounds().x, shape.getBounds().y, shape.getBounds().width, shape.getBounds().height, cornerRadius, cornerRadius).getBounds().height - widthUsedByBorder, cornerRadius, cornerRadius));

            g2d.dispose();
        }
    }

	private void paintBorder(Graphics g, int cornerRadius) {
        createBorderImage(g, cornerRadius);
		final Graphics2D copy = (Graphics2D) g.create();
		copy.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		copy.drawImage(fImage, 0,0, null);
		copy.dispose();
	}
}

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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;



/**
 * This panel just call paintComponent on the {@link IBackground}.
 * Use this to create your custom backgrounds
 * @author Heinrich Spreiter
 *
 */
public class BackgroundPainterPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private final IBackground fBackground;
	private final int fCornerRadius;


	/**
	 * Create a new BackgroundPainterPanel
	 * @param background background we want to paint
	 * @param cornerRadius corner radius of the notification
	 */
	public BackgroundPainterPanel(IBackground background, int cornerRadius) {
		super(new GridBagLayout(), true);
		fBackground = background;
		fCornerRadius = cornerRadius;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		final Point windowLocation = getRootPane().getParent().getLocationOnScreen();
		final Dimension size = getRootPane().getSize();
		final Rectangle rectangle = new Rectangle(windowLocation.x, windowLocation.y, size.width, size.height);
		fBackground.paintBackground(g, rectangle.contains(MouseInfo.getPointerInfo().getLocation()), fCornerRadius);
	}
}

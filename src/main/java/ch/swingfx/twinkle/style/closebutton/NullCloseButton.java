package ch.swingfx.twinkle.style.closebutton;

import java.awt.Graphics;
import java.awt.Point;

/**
 * A {@link NullCloseButton} is an invisible close button. So if you don't want to display a close button use this class.
 * @author Heinrich Spreiter
 *
 */
public class NullCloseButton implements ICloseButton {

	public void paintCloseButton(Graphics g) {
		// Do nothing

	}

	public boolean isMouseOverCloseButton(Point mousePosition) {
		return false;
	}

	public ICloseButton withPosition(int x, int y) {
		return this;
	}

}

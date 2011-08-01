package ch.swingfx.twinkle.style.overlay;

/**
 * Tells us if we should paint the overlay always, or only on mouse over/out
 * @author Heinrich Spreiter
 *
 */
public enum OverlayPaintMode {
	/**
	 * Paint the overlay always.
	 */
	ALWAYS,
	/**
	 * Paint the overlay only if the mouse is over.
	 */
	MOUSE_OVER,
	/**
	 * Paint the overlay only if the mouse is out.
	 */
	MOUSE_OUT
}

package ch.swingfx.twinkle.style.theme;

import ch.swingfx.color.ColorUtil;
import ch.swingfx.twinkle.style.AbstractNotificationStyle;
import ch.swingfx.twinkle.style.background.ColorBackground;
import ch.swingfx.twinkle.style.closebutton.RoundCloseButton;
import ch.swingfx.twinkle.style.overlay.BorderOverlay;
import ch.swingfx.twinkle.style.overlay.GradientOverlay;
import ch.swingfx.twinkle.style.overlay.OverlayPaintMode;
import ch.swingfx.twinkle.window.NotificationWindowTypes;

import java.awt.*;

/**
 * Dark theme for the default window
 * @author Heinrich Spreiter
 * @see NotificationWindowTypes
 */
public class DarkDefaultNotification extends AbstractNotificationStyle {
	public DarkDefaultNotification() {
		super();
		withNotificationWindowCreator(NotificationWindowTypes.DEFAULT);
		withTitleFontColor(new Color(0xff, 0xcc, 0x33));
		withMessageFontColor(Color.WHITE);
		withAlpha(0.85f);
		withWidth(320);
		withBackground(new ColorBackground(new Color(0x10, 0x10, 0x10)));
		withWindowCornerRadius(8);
		withOverlay(new BorderOverlay(1, Color.WHITE, OverlayPaintMode.MOUSE_OVER,
					new GradientOverlay(ColorUtil.withAlpha(Color.WHITE, 0f), ColorUtil.withAlpha(Color.WHITE, 0.1f), OverlayPaintMode.MOUSE_OVER)));
		withCloseButton(new RoundCloseButton(ColorUtil.withAlpha(Color.BLACK, 0.6f), Color.WHITE).withPosition(9, 9));
	}
}

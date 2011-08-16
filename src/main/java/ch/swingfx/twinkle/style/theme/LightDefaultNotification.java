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

package ch.swingfx.twinkle.style.theme;

import ch.swingfx.color.ColorUtil;
import ch.swingfx.twinkle.style.AbstractNotificationStyle;
import ch.swingfx.twinkle.style.background.ColorBackground;
import ch.swingfx.twinkle.style.closebutton.RectangleCloseButton;
import ch.swingfx.twinkle.style.closebutton.RoundCloseButton;
import ch.swingfx.twinkle.style.overlay.BorderOverlay;
import ch.swingfx.twinkle.style.overlay.GradientOverlay;
import ch.swingfx.twinkle.style.overlay.OverlayPaintMode;
import ch.swingfx.twinkle.window.NotificationWindowTypes;

import java.awt.*;

/**
 * Light theme for the default window
 * @author Heinrich Spreiter
 * @see ch.swingfx.twinkle.window.NotificationWindowTypes
 */
public class LightDefaultNotification extends AbstractNotificationStyle {
	public LightDefaultNotification() {
		super();
		withNotificationWindowCreator(NotificationWindowTypes.DEFAULT);
		withTitleFontColor(Color.BLACK);
		withMessageFontColor(Color.BLACK);
		withAlpha(0.85f);
		withWidth(320);
		withBackground(new ColorBackground(new Color(0xfd, 0xfd, 0xfd)));
		withWindowCornerRadius(8);
		withOverlay(new BorderOverlay(1, Color.BLACK, OverlayPaintMode.ALWAYS,
					new GradientOverlay(ColorUtil.withAlpha(Color.BLACK, 0f), ColorUtil.withAlpha(Color.BLACK, 0.1f), OverlayPaintMode.MOUSE_OVER)));
		withCloseButton(new RectangleCloseButton(ColorUtil.withAlpha(Color.WHITE, 0.8f), Color.BLACK).withPosition(9, 9));
	}
}

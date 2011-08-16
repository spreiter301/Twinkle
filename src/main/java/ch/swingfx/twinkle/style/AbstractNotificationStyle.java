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

package ch.swingfx.twinkle.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

import ch.swingfx.twinkle.style.background.ColorBackground;
import ch.swingfx.twinkle.style.background.IBackground;
import ch.swingfx.twinkle.style.closebutton.ICloseButton;
import ch.swingfx.twinkle.style.closebutton.NullCloseButton;
import ch.swingfx.twinkle.style.overlay.IOverlay;
import ch.swingfx.twinkle.style.overlay.NullOverlay;
import ch.swingfx.twinkle.window.ICreateNotificationWindow;
import ch.swingfx.twinkle.window.NotificationWindowTypes;

/**
 * Provides a base class for {@link INotificationStyle} implementations. It provides
 * default values for most styles except color values
 * @author Heinrich Spreiter
 *
 */
public abstract class AbstractNotificationStyle implements INotificationStyle {

	private Font fTitleFont = null;
	private Font fMessageFont = null;
	private Color fTitleFontColor = Color.WHITE;
	private Color fMessageFontColor = Color.WHITE;
	private Insets fWindowInsets = null;
	private int fWindowWidth = 320;
	private float fAlpha = 1f;
	private ICloseButton fCloseButton;
	private IOverlay fOverlay;
	private IBackground fBackground;
	private int fWindowCornerRadius;
	private ICreateNotificationWindow fWindowCreator;
	private Map<String, Object> fCustomParams;

	public AbstractNotificationStyle() {
		final Font font = new JLabel().getFont();
		fWindowCornerRadius = 0;
		fCustomParams = new HashMap<String, Object>();
		fBackground = new ColorBackground(Color.GRAY);
		fTitleFont = font.deriveFont(13f).deriveFont(Font.BOLD);
		fMessageFont = font.deriveFont(12f).deriveFont(Font.PLAIN);
		fWindowInsets = new Insets(10, 10, 10, 10);
		fOverlay = new NullOverlay();
		fCloseButton = new NullCloseButton();
		fWindowCreator = NotificationWindowTypes.DEFAULT;
	}

	/**
	 * Override the default width
	 * @param width new width
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withWidth(int width) {
		fWindowWidth = width;
		return this;
	}
	
	/**
	 * Override the default window corner radius
	 * @param cornerRadius new corner radius
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withWindowCornerRadius(int cornerRadius) {
		if(cornerRadius < 0) {
			throw new RuntimeException("Window corner radius must be >= 0");
		}
		fWindowCornerRadius = cornerRadius;
		return this;
	}
	
	/**
	 * Override the default window insets
	 * @param windowInsets new window insets
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withWindowInsets(Insets windowInsets) {
		this.fWindowInsets = windowInsets;
		return this;
	}
	
	
	/**
	 * Override the default title font
	 * @param titleFont new title font
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withTitleFont(Font titleFont) {
		this.fTitleFont = titleFont;
		return this;
	}
	
	/**
	 * Override the default message font
	 * @param messageFont new message font
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withMessageFont(Font messageFont) {
		this.fMessageFont = messageFont;
		return this;
	}
	
	/**
	 * Override the default alpha of the notification
	 * @param alpha new alpha
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withAlpha(float alpha) {
		this.fAlpha = alpha;
		getBackground().setAlpha(alpha);
		return this;
	}
	
	/**
	 * Override the default {@link IBackground}
	 * @param background new {@link IBackground}
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withBackground(IBackground background) {
		this.fBackground = background;
		background.setAlpha(getAlpha());
		return this;
	}
	
	/**
	 * Override the default title font color
	 * @param color new font color
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withTitleFontColor(Color color) {
		this.fTitleFontColor = color;
		return this;
	}
	
	/**
	 * Override the default message font color
	 * @param color new font color
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withMessageFontColor(Color color) {
		this.fMessageFontColor = color;
		return this;
	}
	
	/**
	 * Override the default {@link IOverlay}
	 * @param overlay new {@link IOverlay}
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withOverlay(IOverlay overlay) {
		this.fOverlay = overlay;
		return this;
	}
	
	/**
	 * Override the default {@link ICloseButton}
	 * @param closeButton new {@link ICloseButton}
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withCloseButton(ICloseButton closeButton) {
		this.fCloseButton = closeButton;
		return this;
	}
	
	/**
	 * Override the default {@link ICreateNotificationWindow}
	 * @param windowCreator new {@link ICreateNotificationWindow}
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withNotificationWindowCreator(ICreateNotificationWindow windowCreator) {
		this.fWindowCreator = windowCreator;
		return this;
	}

	/**
	 * Override the custom parameters
	 * @param customParams new custom parameters
	 * @return this {@link AbstractNotificationStyle}
	 */
	public AbstractNotificationStyle withCustomParams(Map<String, Object> customParams) {
		fCustomParams = customParams;
		return this;
	}

	public int getWidth() {
		return fWindowWidth;
	}
	
	public int getWindowCornerRadius() {
		return fWindowCornerRadius;
	}
	public Insets getWindowInsets() {
		return (Insets) fWindowInsets.clone();
	}

	public Font getTitleFont() {
		return fTitleFont;
	}
	
	public Font getMessageFont() {
		return fMessageFont;
	}
	
	public float getAlpha() {
		return fAlpha;
	}
	
	public IBackground getBackground() {
		return fBackground;
	}

	public Color getTitleFontColor() {
		return fTitleFontColor;
	}
	public Color getMessageFontColor() {
		return fMessageFontColor ;
	}
	public IOverlay getOverlay() {
		return fOverlay;
	}
	
	public ICloseButton getCloseButton() {
		return fCloseButton;
	}
	
	public ICreateNotificationWindow getNotificationWindowCreator() {
		return fWindowCreator;
	}
	public Map<String, Object> getCustomParams() {
		return fCustomParams;
	}
}
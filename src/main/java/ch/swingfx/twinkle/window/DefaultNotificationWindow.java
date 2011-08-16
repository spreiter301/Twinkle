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

package ch.swingfx.twinkle.window;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.plaf.basic.BasicTextAreaUI;

import ch.swingfx.text.TextUtil;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.style.background.BackgroundPainterPanel;
import ch.swingfx.window.translucentandshaped.ITranslucentAndShapedWindowApi;
import ch.swingfx.window.translucentandshaped.Translucency;
import ch.swingfx.window.translucentandshaped.TranslucentAndShapedWindowApiFactory;

/**
 * The default notification window layout.<br />
 * You can override the insets for the icon, title and message. Example
 * <code>
 *     <pre>
 *         map.put(DefaultNotificationWindow.OVERRIDE_ICON_INSETS, new Insets(5,5,5,5));
 *         map.put(DefaultNotificationWindow.OVERRIDE_TITLE_INSETS, new Insets(5,5,5,5));
 *         map.put(DefaultNotificationWindow.OVERRIDE_MESSAGE_INSETS, new Insets(5,5,5,5));
 *         ...
 *         style.withCustomParams(map);
 *     </pre>
 * </code>
 * @author Heinrich Spreiter
 *
 */
public class DefaultNotificationWindow extends JWindow {
	private static final long serialVersionUID = 1L;

	public static final String OVERRIDE_ICON_INSETS = DefaultNotificationWindow.class.getName() + "-icon-insets";
	public static final String OVERRIDE_TITLE_INSETS = DefaultNotificationWindow.class.getName() + "-title-insets";
	public static final String OVERRIDE_MESSAGE_INSETS = DefaultNotificationWindow.class.getName() + "-message-insets";

	/** default insets for the icon */
	private static final Insets sIconInsets = new Insets(10, 5, 10, 10);
	/** default insets for the title */
	private static final Insets sTitleLabelInsets = new Insets(10, 0, 5, 10);
	/** default insets for the message */
	private static final Insets sMessageLabelInsets = new Insets(0, 0, 10, 10);

	/**
	 * Create a new notification
	 * @param icon icon we want to display - can be null
	 * @param title title we want to display - can not be null
	 * @param message message we want to display - can be null
	 * @param style style we want to use - can not be null
	 * @param graphicsConfiguration {@link GraphicsConfiguration} for the window - can not be null
	 */
	public DefaultNotificationWindow(Icon icon, String title, String message, INotificationStyle style, GraphicsConfiguration graphicsConfiguration) {
		super(graphicsConfiguration);
		// setup insets
		final Map<String,Object> customParams = style.getCustomParams();
		Insets fIconInsets = sIconInsets;
		if (customParams.containsKey(OVERRIDE_ICON_INSETS)) {
			fIconInsets = (Insets) customParams.get(OVERRIDE_ICON_INSETS);
		}
		Insets fTitleLabelInsets = sTitleLabelInsets;
		if (customParams.containsKey(OVERRIDE_TITLE_INSETS)) {
			fTitleLabelInsets = (Insets) customParams.get(OVERRIDE_TITLE_INSETS);
		}
		Insets fMessageLabelInsets = sMessageLabelInsets;
		if (customParams.containsKey(OVERRIDE_MESSAGE_INSETS)) {
			fMessageLabelInsets = (Insets) customParams.get(OVERRIDE_MESSAGE_INSETS);
		}
		
        int cornerRadius = 0;
		final ITranslucentAndShapedWindowApi api = TranslucentAndShapedWindowApiFactory.getApi();
		// can we draw rounded corners?
		if(api.isTranslucencyCapable(graphicsConfiguration) && 
				api.isTranslucencySupported(Translucency.PERPIXEL_TRANSLUCENT, graphicsConfiguration.getDevice())) {
			cornerRadius = style.getWindowCornerRadius();
		}
        JPanel fContentPane = new BackgroundPainterPanel(style.getBackground(), cornerRadius);
		fContentPane.setPreferredSize(new Dimension(style.getWidth(), 100));
		getContentPane().add(fContentPane);
		
        JLabel fIconLabel;
        if(icon != null) {
			fIconLabel = new JLabel(icon);
		} else {
			// add an empty label for padding
			fIconLabel = new JLabel("");
		}
		fContentPane.add(fIconLabel, new GridBagConstraints(0, 0, 1, 2, 0, 0, GridBagConstraints.NORTH, GridBagConstraints.NONE, fIconInsets, 0, 0));

        JTextArea fTitleLabel;
        if(title != null) {
			fTitleLabel = getTextAreaForDisplay(title);
			fTitleLabel.setForeground(style.getTitleFontColor());
			fTitleLabel.setFont(style.getTitleFont());
			fContentPane.add(fTitleLabel, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, fTitleLabelInsets, 0, 0));
		} else {
			// this is required!
			throw new RuntimeException("Title can not be empty!");
		}

        JTextArea fMessageLabel;
        if(message != null) {
			fMessageLabel = getTextAreaForDisplay(message);
			fMessageLabel.setForeground(style.getMessageFontColor());
			fMessageLabel.setFont(style.getMessageFont());
            fContentPane.add(fMessageLabel, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH, fMessageLabelInsets, 0, 0));
		} else {
			fMessageLabel = null;
		}
		// call to pack calculates the correct widths of the TextAreas
		// but not the correct heights.
		pack();
		
		// that's why we calculated the preferred heights here,
		// set the new preferred heights
		// and pack it again
		int titlePreferredHeight = TextUtil.calculatePreferredHeight(fTitleLabel.getFontMetrics(fTitleLabel.getFont()), fTitleLabel.getWidth() - fTitleLabelInsets.left - fTitleLabelInsets.right, title);
        fTitleLabel.setPreferredSize(new Dimension(fTitleLabel.getWidth(), titlePreferredHeight));

        int messagePreferredHeight = 0;
		if(message != null) {
			messagePreferredHeight = TextUtil.calculatePreferredHeight(fMessageLabel.getFontMetrics(fMessageLabel.getFont()), fMessageLabel.getWidth() - fMessageLabelInsets.left - fMessageLabelInsets.right, message);
			fMessageLabel.setPreferredSize(new Dimension(fMessageLabel.getWidth(), messagePreferredHeight));
		}
		int preferredHeight = titlePreferredHeight + messagePreferredHeight + fTitleLabelInsets.top + fTitleLabelInsets.bottom + fMessageLabelInsets.top + fMessageLabelInsets.bottom;
		final int iconHeight = fIconLabel.getHeight() + fIconInsets.top + fIconInsets.bottom;
		if(preferredHeight < iconHeight) {
			preferredHeight = iconHeight;
		}
		setPreferredSize(new Dimension(getPreferredSize().width, preferredHeight));
		pack();
	}

	/**
	 * Constructs a new display only {@link JTextArea}
	 * @param text text that the {@link JTextArea} displays
	 * @return a new display only {@link JTextArea}
	 */
	private JTextArea getTextAreaForDisplay(String text) {
		final JTextArea ta = new JTextArea(text);
		ta.setUI(new BasicTextAreaUI());
		ta.setEditable(false);
		ta.setFocusable(false);
		ta.setOpaque(false);
		ta.setWrapStyleWord(true);
		ta.setLineWrap(true);
		return ta;
	}
	
}


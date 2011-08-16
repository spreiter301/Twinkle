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

package ch.swingfx.twinkle;

import ch.swingfx.awt.GraphicsEnvironmentUtil;
import ch.swingfx.timer.AnimationTimer;
import ch.swingfx.timer.AnimationTimer.AnimationTarget;
import ch.swingfx.timer.AnimationTimer.FrameRate;
import ch.swingfx.twinkle.event.INotificationEventListener;
import ch.swingfx.twinkle.event.NotificationEvent;
import ch.swingfx.twinkle.event.NotificationEventAdapter;
import ch.swingfx.twinkle.manager.INotificationManager;
import ch.swingfx.twinkle.manager.NotificationManagers;
import ch.swingfx.twinkle.style.INotificationStyle;
import ch.swingfx.twinkle.window.GlassPane;
import ch.swingfx.twinkle.window.IPosition;
import ch.swingfx.twinkle.window.Positions;
import ch.swingfx.window.translucentandshaped.ITranslucentAndShapedWindowApi;
import ch.swingfx.window.translucentandshaped.Translucency;
import ch.swingfx.window.translucentandshaped.TranslucentAndShapedWindowApiFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.UUID;

/**
 * This class lets you build a notification window.<br />
 * @author Heinrich Spreiter
 *
 */
public class NotificationBuilder {
	/** version of the library */
	private static final int version = 100;

	/**Duration of the fade in animation in milliseconds */
	private static final int ANIMATION_FADE_IN_DURATION = 800;
	/**Duration of the fade out animation in milliseconds */
	private static final int ANIMATION_FADE_OUT_DURATION = 200;
	/**delay before we start the fade in animation 
	 * because of flickering on some systems/jres. */
	private static final int ANIMATION_FADE_IN_START_DELAY = 200;
	/** The default display time for a notification in milliseconds */
	private static final int DEFAULT_DISPLAY_TIME_IN_MILLIS = 5000;
	
	/** id of this {@link NotificationBuilder}. Sent in {@link NotificationEvent} */
	private final UUID fId;
	
	/** The Icon we want to display */
	private Icon fIcon;
	/** The title we want to display */
	private String fTitle;
	/** The message we want to display */
	private String fMessage;
	/** Time in millis we want to display the notification */
	private int fDisplayTime = DEFAULT_DISPLAY_TIME_IN_MILLIS;
	/** Listener that receives notification events */
	private INotificationEventListener fNotificationListener = new NotificationEventAdapter() { /** empty */ };
	/** styling for our window */
	private INotificationStyle fStyle;
	/** Position of the notification */
	private IPosition fPosition = Positions.NORTH_EAST;
	/** Flag that tells us if we should fade in the notification */
	private boolean fFadeIn;
	/** Flag that tells us if we should fade out the notification */
	private boolean fFadeOut;
	/**Flag that tells us if the window is closed or not */
	private boolean fWindowClosed;
	/** The manager that shows the notifications */
	private INotificationManager fManager;

	
	/** api for Translucent and Shaped Windows */
	private static final ITranslucentAndShapedWindowApi sWindowApi;
	private final static GraphicsDevice sMainScreen;
	private final static GraphicsConfiguration sMainScreenGraphicsConfiguration;
	/** The size of the main screen including all the menu/task bars*/
	private final static Dimension sMainScreenSize;
	/** Insets of menu/task bars */
	private static Insets sMainScreenInsets;
	private final static Toolkit sToolkit;

	static {
		sMainScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		sMainScreenGraphicsConfiguration = sMainScreen.getDefaultConfiguration();
		sToolkit = Toolkit.getDefaultToolkit();
		sMainScreenSize = sToolkit.getScreenSize();
		sWindowApi = TranslucentAndShapedWindowApiFactory.getApi();
		final boolean isX11 = GraphicsEnvironmentUtil.isX11GraphicsEnvironment;
		final boolean isOSX = GraphicsEnvironmentUtil.isCGraphicsEnvironment;
		if(isX11) {
			final Rectangle x11RootNetWorkarea = GraphicsEnvironmentUtil.getX11RootNetWorkarea();
			if(x11RootNetWorkarea != null) {
				// use x11 workaround
				sMainScreenInsets = new Insets(x11RootNetWorkarea.x, x11RootNetWorkarea.y, sMainScreenSize.height - x11RootNetWorkarea.height, sMainScreenSize.width - x11RootNetWorkarea.width);
			} else {
				// fall back to default
				sMainScreenInsets = sToolkit.getScreenInsets(sMainScreenGraphicsConfiguration);
			}
		} else if(isOSX) {
			sMainScreenInsets = sToolkit.getScreenInsets(sMainScreenGraphicsConfiguration);
			// only fall back if we can't get the insets with the toolkit
			if(sMainScreenInsets == null || sMainScreenInsets.top == 0 /* OS X always has a menu bar and therefore top must be > 0 */) {
				sMainScreenInsets = GraphicsEnvironmentUtil.OSX_MENU_BAR_SCREEN_INSETS;
			}
		} else {
			sMainScreenInsets = sToolkit.getScreenInsets(sMainScreenGraphicsConfiguration);
		}
	}
	
	public NotificationBuilder() {
		fId = UUID.randomUUID(); 
		fWindowClosed = false;
		fFadeIn = true;
		fFadeOut = true;
		fManager = NotificationManagers.SEQUENTIAL;
	}
	
	/**
	 * set the style of the notification
	 * @param style the style of the notification
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withStyle(INotificationStyle style) {
		fStyle = style;
		return this;
	}
	
	/**
	 * set the icon of the notification
	 * @param iconPath path to the icon
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withIcon(String iconPath) {
		return withIcon(new ImageIcon(iconPath));
	}
	
	/**
	 * set the icon of the notification
	 * @param icon the icon
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withIcon(Icon icon) {
		fIcon = icon;
		return this;
	}
	
	/**
	 * set the title of the notification
	 * @param title the title
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withTitle(String title) {
		if("".equals(title)) {
			title = null;
		}

		fTitle = title;
		return this;
	}
	
	/**
	 * set the message of the notification
	 * @param message the message
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withMessage(String message) {
		if("".equals(message)) {
			message = null;
		}
		this.fMessage = message;
		return this;
	}

	/**
	 * set the time the notification should be displayed
	 * @param timeInMillis time in milliseconds. minimum 1000
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withDisplayTime(int timeInMillis) {
		if(timeInMillis < 1000) {
			timeInMillis = 1000;
		}
		this.fDisplayTime = timeInMillis;
		return this;
	}
	
	/**
	 * Set the position of the notification
	 * @param position position of the notification
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withPosition(IPosition position) {
		this.fPosition = position;
		return this;
	}
	
	/**
	 * set true if you want to fade in the notification.<br />
	 * Note fade in is not supported on all platform. If it doesn't
	 * work on a platform it will use no animation.
	 * @param animate true if you want to fade in the notification
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withFadeInAnimation(boolean animate) {
		this.fFadeIn = animate;
		return this;
	}
	
	/**
	 * set true if you want to fade out the notification.<br />
	 * Note fade out is not supported on all platform. If it doesn't
	 * work on a platform it will use no animation.
	 * @param animate true if you want to fade in the notification
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withFadeOutAnimation(boolean animate) {
		this.fFadeOut = animate;
		return this;
	}
	
	/**
	 * Sets a {@link INotificationEventListener} for receiving events from the notification
	 * @param listener listener to add
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withListener(INotificationEventListener listener) {
		this.fNotificationListener = listener;
		return this;
	}

	/**
	 * Set the {@link INotificationManager} that handles how the notification are displayed
	 * @param manager {@link INotificationManager} to use
	 * @return this {@link NotificationBuilder}
	 */
	public NotificationBuilder withNotificationManager(INotificationManager manager) {
		this.fManager = manager;
		return this;
	}
	
	/**
	 * Get the {@link INotificationStyle} of this notification
	 * @return the {@link INotificationStyle} of this notification
	 */
	public INotificationStyle getStyle() {
		return fStyle;
	}
	
	
	
	/**
	 * shows the notification
	 * @return Unique identifier for this notification. Call NotificationEvent.getId() to retrieve this id
	 */
	public UUID showNotification() {
		if(fStyle == null) {
			throw new IllegalStateException("Please set a style with \"withStyle()\"");
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showNotificationEDT();				
			}
		});
		return fId;
	}

	private void showNotificationEDT() {
		// This timer closes the window automatically if the mouse
		// is not over the window.
		final Timer closeWindowTimer = new Timer(fDisplayTime, null);
		// for the fade in animation
		final AnimationTimer fadeInAnimationTimer = new AnimationTimer(FrameRate.FPS_25);

		// This window displays the notification
		final JWindow window = fStyle.getNotificationWindowCreator().createNotificationWindow(fIcon, fTitle, fMessage, fStyle, sMainScreenGraphicsConfiguration);
		
		// set the position of the window
		final Point windowPosition = fPosition.getPosition(sMainScreenSize, sMainScreenInsets, window, fStyle);
		final int x = windowPosition.x;
		final int y = windowPosition.y;
		window.setLocation(x, y);
		
		// store the size of the window.
		// this is important because if we want to fade in the window
		// we can not move it off screen in jdk 7 so we have to make
		// it's size 0,0 so it will be invisible to the user.
		// then when we show the window we restore the size
		// and fade the window in.
		// If we would just fade it in, it would blink before it's transparent
		final Dimension windowSize = window.getBounds().getSize();
		if(fFadeIn && sWindowApi.isTranslucencySupported(Translucency.TRANSLUCENT, sMainScreen)) {
			window.setSize(0, 0);
		}
		
		closeWindowTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fWindowClosed) {
					return;
				}
				closeWindowTimer.stop();
				closeWindow(window);
				fNotificationListener.closed(new NotificationEvent(NotificationBuilder.this, fId));
			}
		});
		
		// check if the system supports corners for the notification.
		// we do not use sWindowApi.setWindowShape() because that creates
		// ugly corners. See http://download.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
		// Instead, if supported by the system, we use the per pixel translucency.
		// The backgrounds / overlay will then check for the cornerRadius and
		// decide if they should paint a Rectangle or a RoundedRectangle. If they
		// paint a RoundedRectangle only the painted pixels will be visible and
		// therefore the corners will be smooth and nice.
		int cornerRadius = 0;
		if(sWindowApi.isTranslucencySupported(Translucency.PERPIXEL_TRANSLUCENT, sMainScreen)
							&& sWindowApi.isTranslucencyCapable(window.getGraphicsConfiguration())) {
			cornerRadius = fStyle.getWindowCornerRadius();
		}
		// this glass pane intercepts the mouse events for us
		final GlassPane glassPane = new GlassPane(window, fStyle, cornerRadius);
		//window.setGlassPane(glassPane);
		
		glassPane.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				if(fWindowClosed) {
					return;
				}
				// the notification was clicked.
				// check if we clicked on the close button or not
				fadeInAnimationTimer.stop();
				final boolean mouseOverCloseButton = fStyle.getCloseButton().isMouseOverCloseButton(window.getMousePosition());
				if(!mouseOverCloseButton) {
					fNotificationListener.clicked(new NotificationEvent(NotificationBuilder.this, fId));
				}
				closeWindow(window);
				if(mouseOverCloseButton) {
					fNotificationListener.closed(new NotificationEvent(NotificationBuilder.this, fId));
				}
			}
			
			public void mouseEntered(MouseEvent event) {
				if(fWindowClosed) {
					return;
				}
				fadeInAnimationTimer.stop();
				closeWindowTimer.stop();
				fNotificationListener.mouseOver(new NotificationEvent(NotificationBuilder.this, fId));
				glassPane.repaint();
			}
			
			public void mouseExited(MouseEvent event) {
				if(fWindowClosed) {
					return;
				}
				closeWindowTimer.start();
				fNotificationListener.mouseOut(new NotificationEvent(NotificationBuilder.this, fId));
				glassPane.repaint();
			}
		});
		
		glassPane.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent event) {
				if(fWindowClosed) {
					return;
				}
				final boolean mouseOverCloseButton = fStyle.getCloseButton().isMouseOverCloseButton(window.getMousePosition());
				if(mouseOverCloseButton) {
					glassPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				} else {
					glassPane.setCursor(Cursor.getDefaultCursor());
				}
			}
		});

		window.setAlwaysOnTop(true);
		
		window.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				if(e.getID() == WindowEvent.WINDOW_OPENED) {
					if(fStyle.getAlpha() < 1f && sWindowApi.isTranslucencySupported(Translucency.PERPIXEL_TRANSLUCENT, sMainScreen)
							&& sWindowApi.isTranslucencyCapable(window.getGraphicsConfiguration())) {
						// if we set it before the window is visible, mouseExited event wont work if there are 1 or more windows opened.
						// therefore set opaque false when the window is opened
						sWindowApi.setWindowOpaque(window, false);
						
					}
					if(fFadeIn && sWindowApi.isTranslucencySupported(Translucency.TRANSLUCENT, sMainScreen)) {
						// animate
						fadeInAnimationTimer.setDuration(ANIMATION_FADE_IN_DURATION);
						fadeInAnimationTimer.setAnimationTarget(new AnimationTarget() {
							
							public void event(AnimationTimer timer, float fraction) {
								sWindowApi.setWindowOpacity(window, fraction);
							}
							
							public void end(AnimationTimer timer) {
								sWindowApi.setWindowOpacity(window, 1f);
							}
							
							public void begin(AnimationTimer timer) {
								// restore the size
								window.setSize(windowSize);
							}
						});
						sWindowApi.setWindowOpacity(window, 0f);
						// on some systems it takes some time
						// to set the opacity to 0. Therefore
						// wait 200 milliseconds before we
						// start the animation and therefore
						// restore the size of the window
						new Timer(ANIMATION_FADE_IN_START_DELAY, new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Timer t = (Timer) e.getSource();
								t.stop();
								fadeInAnimationTimer.start();
							}
						}).start();
					}
					
					fNotificationListener.opened(new NotificationEvent(NotificationBuilder.this, fId));
					// we only need to start the timer if it's not over the notification.
					// Otherwise it could be that first mouseEntered occurs and hence closeWindowTimer.stop()
					// before closeWindowTimer.start(). This means that even though the mouse is over the
					// window, the timer started and the window will close.
					// So we just check if the mouse is over the window or not.
					final Rectangle windowRect = new Rectangle(windowSize.width, windowSize.height);
					windowRect.setLocation(x, y);
					if(!windowRect.contains(MouseInfo.getPointerInfo().getLocation())) {
						// not over the window. save to start the timer
						closeWindowTimer.start();
					}
				}
			}
		});
		
		fManager.showNotification(window);
	}


	/**
	 * Closes the window
	 * @param window the window to close
	 */
	private void closeWindow(final JWindow window) {
		fWindowClosed = true;
		if(fFadeOut && sWindowApi.isTranslucencySupported(Translucency.TRANSLUCENT, sMainScreen)) {
			final AnimationTimer animationTimer = new AnimationTimer(FrameRate.FPS_25);
			animationTimer.setDuration(ANIMATION_FADE_OUT_DURATION);
			animationTimer.setAnimationTarget(new AnimationTarget() {
				
				public void event(AnimationTimer timer, float fraction) {
					sWindowApi.setWindowOpacity(window, 1f - fraction);
				}
				
				public void end(AnimationTimer timer) {
					window.dispose();
				}
				
				public void begin(AnimationTimer timer) {
					
				}
			});
			animationTimer.start();
		} else {
			window.dispose();
		}
	}
	
	
	
}

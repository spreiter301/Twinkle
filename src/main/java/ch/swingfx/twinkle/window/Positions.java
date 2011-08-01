package ch.swingfx.twinkle.window;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JWindow;

import ch.swingfx.twinkle.NotificationBuilder;
import ch.swingfx.twinkle.style.INotificationStyle;

/**
 * This enum has some default positions that can be used by {@link NotificationBuilder}.withPosition()
 * @author Heinrich Spreiter
 *
 */
public enum Positions implements IPosition {

	NORTH_EAST {
		public Point getPosition(Dimension screenSize, Insets screenInsets, JWindow window, INotificationStyle style) {
			final int x = screenSize.width - window.getWidth() - screenInsets.right - style.getWindowInsets().right;
			final int y = screenInsets.top + style.getWindowInsets().top;
			return new Point(x, y);
		}
		
	},
	NORTH_WEST {
		public Point getPosition(Dimension screenSize, Insets screenInsets, JWindow window, INotificationStyle style) {
			final int x = style.getWindowInsets().left;
			final int y = screenInsets.top + style.getWindowInsets().top;
			return new Point(x, y);
		}
	},
	NORTH {

		public Point getPosition(Dimension screenSize, Insets screenInsets, JWindow window, INotificationStyle style) {
			final int x = screenSize.width / 2 - window.getWidth() / 2;
			final int y = screenInsets.top + style.getWindowInsets().top;
			return new Point(x, y);
		}
		
	},
	SOUTH_EAST {

		public Point getPosition(Dimension screenSize, Insets screenInsets, JWindow window, INotificationStyle style) {
			final int x = screenSize.width - window.getWidth() - screenInsets.right - style.getWindowInsets().right;
			final int y = screenSize.height - screenInsets.bottom - window.getHeight() - style.getWindowInsets().bottom; 
			return new Point(x, y);
		}
		
	},
	
	SOUTH_WEST {

		public Point getPosition(Dimension screenSize, Insets screenInsets,
				JWindow window, INotificationStyle style) {
			final int x = style.getWindowInsets().left;
			final int y = screenSize.height - screenInsets.bottom - window.getHeight() - style.getWindowInsets().bottom; 
			return new Point(x, y);
		}
	},
	
	SOUTH {

		public Point getPosition(Dimension screenSize, Insets screenInsets,
				JWindow window, INotificationStyle style) {
			final int x = screenSize.width / 2 - window.getWidth() / 2;
			final int y = screenSize.height - screenInsets.bottom - window.getHeight() - style.getWindowInsets().bottom; 
			return new Point(x, y);
		}
	},
	
	WEST {

		public Point getPosition(Dimension screenSize, Insets screenInsets,
				JWindow window, INotificationStyle style) {
			final int x = style.getWindowInsets().left;
			final int y = screenSize.height / 2 - window.getHeight() / 2 - style.getWindowInsets().bottom / 2 + style.getWindowInsets().top / 2;
			return new Point(x, y);
		}
	},
	
	EAST {
		public Point getPosition(Dimension screenSize, Insets screenInsets,
				JWindow window, INotificationStyle style) {
			final int x = screenSize.width - window.getWidth() - screenInsets.right - style.getWindowInsets().right;
			final int y = screenSize.height / 2 - window.getHeight() / 2 - style.getWindowInsets().bottom / 2 + style.getWindowInsets().top / 2;
			return new Point(x, y);
		}
	},
	
	CENTER {

		public Point getPosition(Dimension screenSize, Insets screenInsets,
				JWindow window, INotificationStyle style) {
			final int x = screenSize.width / 2 - window.getWidth() / 2;
			final int y = screenSize.height / 2 - window.getHeight() / 2 - style.getWindowInsets().bottom / 2 + style.getWindowInsets().top / 2;
			return new Point(x, y);
		}
		
	}
}

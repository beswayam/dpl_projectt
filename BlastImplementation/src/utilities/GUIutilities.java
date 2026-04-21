package utilities;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

/**
 * Utility class for creating consistently styled GUI components.
 * <p>
 * This class provides helper methods to generate Swing components such as
 * labels and combo boxes with predefined fonts, colors, and styling.
 * It ensures a uniform look and feel across the application by centralizing
 * visual configuration.
 * </p>
 */

public class GUIutilities {
	

	    private final Color CARD = new Color(22, 28, 45);
	    private final Color BORDER = new Color(30, 41, 59);
	    private final Color TEXT_MUTED = new Color(100, 116, 139);
	    private final Color TEXT_LIGHT = new Color(226, 232, 240);
	    
	    /**
	     * Creates a standard label with muted text styling.
	     *
	     * @param text text to display inside the label
	     * @return configured JLabel with muted appearance
	     */
	    
	    public JLabel label(String text) {
	        JLabel lbl = new JLabel(text);
	        lbl.setFont(new Font("Monospaced", Font.PLAIN, 12));
	        lbl.setForeground(TEXT_MUTED);
	        return lbl;
	    }
	    
	    /**
	     * Creates a bold label with highlighted text styling.
	     *
	     * @param text text to display inside the label
	     * @return configured JLabel with bold and light-colored appearance
	     */
	    public JLabel boldLabel(String text) {
	        JLabel lbl = new JLabel(text);
	        lbl.setFont(new Font("Monospaced", Font.BOLD, 12));
	        lbl.setForeground(TEXT_LIGHT);
	        return lbl;
	    }

//	    public JButton button(String text) {
//	        JButton btn = new JButton(text);
//	        btn.setFont(new Font("Monospaced", Font.PLAIN, 11));
//	        btn.setBackground(CARD);
//	        btn.setForeground(Color.WHITE);
//	        btn.setFocusPainted(false);
//	        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
//	        btn.setBorder(new CompoundBorder(
//	            new LineBorder(BORDER, 1),
//	            new EmptyBorder(6, 12, 6, 12)
//	        ));
//	        return btn;
//	    }
	    
	    /**
	     * Creates a styled combo box with predefined color scheme.
	     *
	     * @param items array of items to populate the combo box
	     * @return configured JComboBox with custom styling
	     */
	    public JComboBox<String> combo(String[] items) {
	        JComboBox<String> box = new JComboBox<>(items);
	        box.setBackground(CARD);
	        box.setForeground(TEXT_LIGHT);
	        box.setFont(new Font("Monospaced", Font.PLAIN, 11));
	        return box;
	    }
	    
	    
		/**
		 * Applies the shared rounded-button style used throughout the application.
		 *
		 * @param btn button to style
		 * @param fillColor inner button color
		 * @param outerColor surrounding background color
		 */
		public static void applyRoundedStyle(JButton btn, Color fillColor, Color outerColor) {
			btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
				@Override
				public void paint(Graphics g, JComponent c) {
					Graphics2D g2 = (Graphics2D) g;
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					// Whole rectangle
					g2.setColor(outerColor);
					g2.fillRect(0, 0, c.getWidth(), c.getHeight());

					// Rounded button on top
					g2.setColor(fillColor);
					g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
					super.paint(g, c);
				}
			});

			btn.setFont(new Font("Monospaced", Font.BOLD, 12));
			btn.setForeground(Color.WHITE);
			btn.setOpaque(false);
			btn.setFocusPainted(false);
			btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btn.setBorder(new EmptyBorder(8, 18, 8, 18));
		}}


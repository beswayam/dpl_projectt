package utilities;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class GUIutilities {
	

	    private final Color CARD = new Color(22, 28, 45);
	    private final Color BORDER = new Color(30, 41, 59);
	    private final Color TEXT_MUTED = new Color(100, 116, 139);
	    private final Color TEXT_LIGHT = new Color(226, 232, 240);

	    public JLabel label(String text) {
	        JLabel lbl = new JLabel(text);
	        lbl.setFont(new Font("Monospaced", Font.PLAIN, 12));
	        lbl.setForeground(TEXT_MUTED);
	        return lbl;
	    }

	    public JLabel boldLabel(String text) {
	        JLabel lbl = new JLabel(text);
	        lbl.setFont(new Font("Monospaced", Font.BOLD, 12));
	        lbl.setForeground(TEXT_LIGHT);
	        return lbl;
	    }

	    public JButton button(String text) {
	        JButton btn = new JButton(text);
	        btn.setFont(new Font("Monospaced", Font.PLAIN, 11));
	        btn.setBackground(CARD);
	        btn.setForeground(Color.WHITE);
	        btn.setFocusPainted(false);
	        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        btn.setBorder(new CompoundBorder(
	            new LineBorder(BORDER, 1),
	            new EmptyBorder(6, 12, 6, 12)
	        ));
	        return btn;
	    }

	    public JComboBox<String> combo(String[] items) {
	        JComboBox<String> box = new JComboBox<>(items);
	        box.setBackground(CARD);
	        box.setForeground(TEXT_LIGHT);
	        box.setFont(new Font("Monospaced", Font.PLAIN, 11));
	        return box;
	    }
	    
	    public void applyRoundedStyle(JButton btn, Color fillColor, Color outerColor) {
	        btn.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
	            @Override
	            public void paint(Graphics g, JComponent c) {
	                Graphics2D g2 = (Graphics2D) g;
	                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	                // outer background
	                g2.setColor(outerColor);
	                g2.fillRect(0, 0, c.getWidth(), c.getHeight());

	                // rounded button
	                g2.setColor(fillColor);
	                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);

	                super.paint(g, c);
	            }
	        });

	        btn.setFont(new Font("Monospaced", Font.BOLD, 12));
	        btn.setForeground(Color.WHITE);
	        btn.setContentAreaFilled(false);   
	        btn.setOpaque(false);
	        btn.setFocusPainted(false);
	        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        btn.setBorder(new EmptyBorder(8, 18, 8, 18));
	    }
	}


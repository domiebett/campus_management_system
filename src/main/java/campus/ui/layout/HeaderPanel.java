package campus.ui.layout;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	public HeaderPanel() {
		/**
		 * JPanel for the header containing title
		 */
		setLayout(new BorderLayout());
		JLabel title = new JLabel("<html><div style='text-align: center;'>Campus Management System</div></html>");
		
		add(title, BorderLayout.CENTER);
	}
	
}

package campus.ui.layout;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SidePanel extends JPanel {
	public SidePanel() {
		setLayout(new BorderLayout());
		JLabel tempLabel = new JLabel("Side Panel");
		
		add(tempLabel, BorderLayout.CENTER);
	}
}

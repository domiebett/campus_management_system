package campus.ui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	
	private CourseUI courseUI;
	
	public ContentPanel() {
		setLayout(new BorderLayout());
		courseUI = new CourseUI();
		
		add(courseUI, BorderLayout.CENTER);
	}
}

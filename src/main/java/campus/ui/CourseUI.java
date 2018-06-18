package campus.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import campus.models.Course;
import campus.queries.CourseQuery;

public class CourseUI extends JPanel {
	/**
	 * JPanel that holds a listing of all courses.
	 */
	static JPanel panel;
	static JPanel bodyPanel;
	static JPanel footerPanel;
	static JLabel nameLabel;
	static JLabel codeLabel;
	static JLabel holderLabel;
	static JTextField nameTextField;
	static JTextField codeTextField;
	static JButton submitButton;
	static JButton viewButton;
	static JButton addCourseButton;
	static JButton cancelButton;
	
	BorderLayout borderLayout = new BorderLayout();
    	
	public CourseUI() {
		setLayout(borderLayout);
        
        bodyPanel = new JPanel();
        footerPanel = new JPanel();
        
        bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
        
        List<Course> courses = new CourseQuery().getAllCourses();
        for (Course course : courses) {
			holderLabel = new JLabel();
			JPanel holderPanel = new JPanel() {
				{
					setSize(810, 40);
					setMaximumSize(getSize());
				}
			};
			
			holderPanel.setLayout(new BorderLayout());
			holderPanel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
			
			String courseTitle = course.getName() + " (" + course.getCode() + ")";
			holderLabel.setText(courseTitle);
			holderPanel.add(holderLabel, BorderLayout.WEST);
			
			bodyPanel.add(holderPanel);
		};
		
		addCourseButton = new JButton("Add Course");
		addCourseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentPanel contentPanel = (ContentPanel) getParent();
				contentPanel.display(new AddCourseUI(), "add course");
			}
		});
		footerPanel.add(addCourseButton);
        
        add(bodyPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
	}
}

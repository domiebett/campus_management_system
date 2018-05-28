package campus.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import campus.models.Course;
import campus.queries.CourseQuery;

public class CourseUI extends JPanel {
	
	static JPanel panel;
	static JPanel contentPanel;
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
    
    public CourseUI() { }
	
	public CourseUI(Session session) {
		setLayout(borderLayout);
        
        contentPanel = new JPanel();
        footerPanel = new JPanel();
        
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        
        List<Course> courses = CourseQuery.getAllCourses(session);
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
			
			contentPanel.add(holderPanel);
		};
		
		addCourseButton = new JButton("Add Course");
		footerPanel.add(addCourseButton);
        
        add(contentPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
	}
	
	private static void getAddCourseUI() {
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField();
		
		codeLabel = new JLabel("Code: ");
		codeTextField = new JTextField();
		
		submitButton = new JButton("Submit");
		cancelButton = new JButton("Cancel");
	}
	
	private static void deleteCourseConfirmation() {
		
	}
	
	private static void addCourseConfirmation() {
		
	}
	
}

package campus.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import campus.queries.CourseQuery;

public class AddCourseUI extends JPanel {
	/**
	 * JPanel containing form for adding course
	 */
	private static final long serialVersionUID = -3113520377554973605L;
	private JTextField nameTextField;
	private JTextField codeTextField;
	
	private JLabel nameLabel;
	private JLabel codeLabel;
	
	private JButton submitButton;
	private JButton cancelButton;
	
	private Integer fullWidth = Integer.MAX_VALUE;
	private Integer panelHeight = 30;
	private Dimension panelDimension = new Dimension(fullWidth, panelHeight);
	
	private Integer textFieldWidth = 700;
	private Integer textFieldHeight = 20;
	private Dimension textFieldDimension = new Dimension(textFieldWidth, textFieldHeight);
	
	public AddCourseUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel(new FlowLayout());
		nameLabel = new JLabel("Name: ");
		nameTextField = new JTextField();
		nameTextField.setPreferredSize(textFieldDimension);
		nameTextField.setMaximumSize(textFieldDimension);
		namePanel.setPreferredSize(panelDimension);
		namePanel.add(nameLabel);
		namePanel.add(nameTextField);
		
		JPanel codePanel = new JPanel(new FlowLayout());
		codeLabel = new JLabel("Code: ");
		codeTextField = new JTextField();
		codeTextField.setPreferredSize(textFieldDimension);
		codeTextField.setMaximumSize(textFieldDimension);
		codePanel.setPreferredSize(panelDimension);
		codePanel.add(codeLabel);
		codePanel.add(codeTextField);
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ContentPanel contentPanel = (ContentPanel) getParent();
				contentPanel.display(new CourseUI(), "view courses");
			}
		});
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courseName = nameTextField.getText();
				String courseCode = codeTextField.getText();
				
				if (courseName.length() <= 0 || courseCode.length() <= 0) {
					System.out.println("You cannot enter empty course");
				} else {
					new CourseQuery().addCourse(courseName, courseCode);
					ContentPanel contentPanel = (ContentPanel) getParent();
					contentPanel.display(new CourseUI(), "view courses");
				}
			}
		});
		
		add(nameLabel);
		add(nameTextField);
		
		add(codeLabel);
		add(codeTextField);
		
		add(submitButton);
		add(cancelButton);
	}
}

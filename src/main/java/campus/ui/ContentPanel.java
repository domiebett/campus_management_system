package campus.ui;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	
	/**
	 * Container for all the contents to be displayed in the campus application
	 */
	private static final long serialVersionUID = 4024883297545753696L;
	private CourseUI courseUI;
	
	public ContentPanel() {
		
		setLayout(new CardLayout(10, 10));
		
		courseUI = new CourseUI();
		new AddCourseUI();
		
		add("view courses", courseUI);
	}
	
	/**
	 * Display the JPanel specified and assigns the name to it.
	 * 
	 * @param panel
	 * @param cardName
	 */
	public void display(JPanel panel, String cardName) {
		this.deleteCard(cardName);
		this.add(cardName, panel);
		CardLayout cardLayout = (CardLayout) this.getLayout();
		cardLayout.show(this, cardName);
	}
	
	/**
	 * Deletes a card from the layout if it exists
	 * 
	 * @param cardName
	 */
	public void deleteCard(String cardName) {
		Component[] components = this.getComponents();
		
		System.out.print(components[0].getName());
		
		try {
			for(int i = 0; i < components.length; i++) {
			    if(components[i].getName().equals(cardName)) {
			        this.getLayout().removeLayoutComponent(components[i]);
			    }
			} 
		} catch (NullPointerException e) { }
	}
}

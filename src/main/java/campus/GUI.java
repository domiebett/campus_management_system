package campus;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import campus.ui.LayoutUI;
import hibernate.HibernateUtils;

public class GUI {
	
	static JFrame frame;
	
	public static JFrame createFrame() {
		
		frame = new JFrame( "Campus Management System" );
		
		frame.add(new LayoutUI());
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		return frame;
		
	}
	
	public Session createSession() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
		} catch (Exception e) {
			System.out.println("Big problems arose");
		}
		
		return session;
	}
	
	public GUI() {
		
	}
}

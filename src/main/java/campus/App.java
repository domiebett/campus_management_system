package campus;

import java.util.HashMap;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import campus.models.Course;
import campus.models.Student;
import campus.queries.CourseQuery;
import campus.queries.StudentQuery;
import hibernate.HibernateUtils;

public class App {
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			
			session.getTransaction().begin();
			
			// Create filters for course.
			HashMap<String, String> courseFilters = new HashMap<String, String>();
			courseFilters.put("name", "Bachelo of Computer Science");
			courseFilters.put("code", "BCS");
			
			// Run various queries for the course.
			CourseQuery courseQuery = new CourseQuery();
			courseQuery.addCourse(session, "Bachelor of Computer Science", "BCS");
			courseQuery.getAllCourses(session);
			Course course = courseQuery.getSingleCourse(session, 1);
			courseQuery.getFilteredCourses(session, courseFilters);
			
			// Create filters for students.
			HashMap<String, String> studentFilters = new HashMap<String, String>();
			studentFilters.put("firstName", "Dominic");
			studentFilters.put("lastName", "Kipchumba");
			studentFilters.put("regNo", "BCR/0056/13");
			
			// Run various queries for students
			StudentQuery studentQuery = new StudentQuery();
			studentQuery.addStudent(session, course, "BCR/0056/13", "Dominic", "Kipchumba", "Bett");
			studentQuery.getAllStudents(session);
			studentQuery.getSingleStudent(session, 1);
			studentQuery.getFilteredStudents(session, studentFilters);
			
			session.getTransaction().commit();
			
		} catch (ConstraintViolationException e) {
			System.out.println("You have entered a duplicate value");
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (NoResultException e) {
			System.out.println("No result was found in the query.");
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}

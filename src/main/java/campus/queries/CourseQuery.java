package campus.queries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import campus.models.Course;
import hibernate.HibernateUtils;

public class CourseQuery {
	
	static SessionFactory factory = HibernateUtils.getSessionFactory();
	private static Session session = factory.getCurrentSession();
	
	public CourseQuery() {
		try {
			session.getTransaction().begin();
		} catch (IllegalStateException e) {
			System.out.println("Transaction is already active");
		}
	}
	
	/**
	 * Gets a single course from database
	 * 
	 * @param id - primary key for the course
	 * 
	 * @return Course
	 */
	public static Course getSingleCourse(Integer id) {
		Course course = (Course) session.get(Course.class, id);
		return course;
	}
	
	/**
	 * Gets all courses from the db
	 * 
	 * @return List<Course>
	 */
	public List<Course> getAllCourses() {
		List<Course> courses = session.createQuery( "from Course" ).list();
		return courses;
	}
	
	/**
	 * Gets filtered courses depending on filters passed
	 * 
	 * @param filters
	 * @return List<Course>
	 */
	public List<Course> getFilteredCourses(HashMap<String, String> filters) {
		
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Course> query = builder.createQuery(Course.class);
		Root<Course> root = query.from(Course.class);
		
		final List<Predicate> predicates = new ArrayList<Predicate>();
		
		for (final Map.Entry<String, String> entry : filters.entrySet()) {
			final String key = entry.getKey();
			final String value = entry.getValue();
			
			if ((key != null) && (value != null)) {
				if (value.contains("%")) {
					predicates.add(builder.like(root.<String>get(key), value));
				} else {
					predicates.add(builder.equal(root.get(key), value));
				}
			}
		}
		
		query.select(root).where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		
		List<Course> courses = session.createQuery( query ).list();
		return courses;
	}
	
	/**
	 * Adds a course to the db using provided name and code
	 * 
	 * @param name
	 * @param code
	 * 
	 * @return Course
	 */
	public Course addCourse(String name, String code) {
		Course course = new Course(name, code);
		try {
			session.save(course);
		} catch (ConstraintViolationException e) {
			System.out.println("You have entered a duplicate value");
			session.getTransaction().rollback();
			initializeSession();
		} catch (Exception e) {
			System.out.println("An error occured");
			e.printStackTrace();
			session.getTransaction().rollback();
			initializeSession();
		}
		
		return course;
	}
	
	public void initializeSession() {
		try {
			session = factory.getCurrentSession();
			session.getTransaction().begin();
		} catch (Exception e) {
			System.out.println("Session exists");
		}
	}
}

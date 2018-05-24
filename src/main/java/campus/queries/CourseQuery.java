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

import campus.models.Course;

public class CourseQuery {
	
	public CourseQuery() { }
	
	// Gets a single course.
	public static Course getSingleCourse(Session session, Integer id) {
		Course course = (Course) session.get(Course.class, id);
		return course;
	}
	
	// Gets all courses from db.
	public static List<Course> getAllCourses(Session session) {
		List<Course> courses = session.createQuery( "from Course" ).list();
		return courses;
	}
	
	// Gets courses depending on filters passed.
	public static List<Course> getFilteredCourses(Session session, HashMap<String, String> filters) {
		
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
	
	// Adds a course to db.
	public static Course addCourse(Session session, String name, String code) {
		Course course = new Course(name, code);
		session.save(course);
		return course;
	}
}

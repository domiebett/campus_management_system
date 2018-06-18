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

import campus.models.Course;
import campus.models.Student;
import hibernate.HibernateUtils;

public class StudentQuery {
	
	private static Session session;
	
	public StudentQuery() {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		session = factory.getCurrentSession();
	}
	
	public static Student addStudent(Course course, String regNo, String firstName, String lastName, String surname) {
		Student student = new Student(regNo, firstName, lastName, surname);
		student.setCourse(course);
		course.getStudents().add(student);
		session.save(student);
		return student;
	}
	
	public static List<Student> getAllStudents() {
		List<Student> students = session.createQuery( "from Student" ).list();
		return students;
	}
	
	public static Student getSingleStudent(Integer id) {
		Student student = (Student) session.get(Student.class, id);
		return student;
	}
	
	public static List<Student> getFilteredStudents(HashMap<String, String> filters) {

		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Student> query = builder.createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		
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
		
		List<Student> students = session.createQuery( query ).list();
		return students;
	}
	
}

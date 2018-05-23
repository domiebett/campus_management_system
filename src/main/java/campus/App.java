package campus;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

import campus.models.Student;
import hibernate.HibernateUtils;

public class App {
	
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtils.getSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.getTransaction().begin();
			
			// Creating and saving using hibernate.
			Student dominic = new Student("BCR/0055/13", "Dominic", "Kipchumba", "Bett");
			session.save(dominic);
			
			// Get all students and loop through them.
			List<Student> students = session.createQuery( "from Student" ).list();
			for (Student student: students) {
				System.out.println("Reg No: " + student.getRegNo());
				System.out.println("Name: " + student.getFullName() + "\n");
			}
			
			// Getting a single student using id.
			Student student = (Student) session.get(Student.class, 1);
			System.out.println("He is " + student.getFullName() + " of reg No: " + student.getRegNo());
			
			// Instantiating query builder and all required variables.
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Student> query = builder.createQuery(Student.class);
			Root<Student> root = query.from(Student.class);
			
			// Building multiple queries to get using regNo and name
			query.select(root).where(
					builder.and(
							builder.equal(root.get("regNo"), "BCR/0058/13"),
							builder.equal(root.get("firstName"), "Dominic")
							)
					);
			
			// Querying the db to get the result of the query.
			Student student2 = session.createQuery( query ).getSingleResult();
			System.out.println("The student is " + student2.getFullName());
			
			session.getTransaction().commit();
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}
}

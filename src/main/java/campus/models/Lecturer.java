package campus.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="lecturers")
public class Lecturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="staff_id")
	private String staffId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="surname")
	private String surname;
	
	@OneToMany(mappedBy = "lecturer")
	private Set<Unit> students = new HashSet<Unit>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Set<Unit> getStudents() {
		return students;
	}

	public void setStudents(Set<Unit> students) {
		this.students = students;
	}
	
	public Lecturer() {
		
	}
	
	public Lecturer(String staffId, String firstName, String lastName, String surname) {
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.surname = surname;
	}
	
}

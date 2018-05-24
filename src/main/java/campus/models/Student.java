package campus.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="students", 
	uniqueConstraints = {@UniqueConstraint(columnNames = { "reg_no" })})
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="reg_no", nullable=false)
	private String regNo;
	
	@Column(name="first_name", nullable=false)
	private String firstName;
	
	@Column(name="last_name", nullable=false)
	private String lastName;
	
	@Column(name="surname", nullable=true)
	private String surname;
	
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	
	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	public Student() {
		
	}
	
	public Student(String regNo, String firstName, String lastName) {
		this.regNo = regNo;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Student(String regNo, String firstname, String lastName, String surname) {
		this.regNo = regNo;
		this.firstName = firstname;
		this.lastName = lastName;
		this.surname = surname;
	}
	
}

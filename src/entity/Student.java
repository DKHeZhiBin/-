package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer sid;
	private Student student;
	private String sname;
	private String class_;
	private String major;
	private String password;
	private Set students = new HashSet(0);
	private Set scoreTables = new HashSet(0);

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** minimal constructor */
	public Student(Integer sid, Student student, String sname, String class_,
			String major, String password) {
		this.sid = sid;
		this.student = student;
		this.sname = sname;
		this.class_ = class_;
		this.major = major;
		this.password = password;
	}

	/** full constructor */
	public Student(Integer sid, Student student, String sname, String class_,
			String major, String password, Set students, Set scoreTables) {
		this.sid = sid;
		this.student = student;
		this.sname = sname;
		this.class_ = class_;
		this.major = major;
		this.password = password;
		this.students = students;
		this.scoreTables = scoreTables;
	}

	// Property accessors

	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getMajor() {
		return this.major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getStudents() {
		return this.students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	public Set getScoreTables() {
		return this.scoreTables;
	}

	public void setScoreTables(Set scoreTables) {
		this.scoreTables = scoreTables;
	}

}
package entity;

/**
 * ScoreTable entity. @author MyEclipse Persistence Tools
 */

public class ScoreTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private Student student;
	private TestArrangeTable testArrangeTable;
	private Integer score;

	// Constructors

	/** default constructor */
	public ScoreTable() {
	}

	/** full constructor */
	public ScoreTable(Integer id, Student student,
			TestArrangeTable testArrangeTable, Integer score) {
		this.id = id;
		this.student = student;
		this.testArrangeTable = testArrangeTable;
		this.score = score;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public TestArrangeTable getTestArrangeTable() {
		return this.testArrangeTable;
	}

	public void setTestArrangeTable(TestArrangeTable testArrangeTable) {
		this.testArrangeTable = testArrangeTable;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}
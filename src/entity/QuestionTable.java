package entity;

/**
 * QuestionTable entity. @author MyEclipse Persistence Tools
 */

public class QuestionTable implements java.io.Serializable {

	// Fields

	private Integer qid;
	private String questText;
	private String answer;
	private Integer mark;
	private String a;
	private String b;
	private String c;
	private String d;

	// Constructors

	/** default constructor */
	public QuestionTable() {
	}

	/** minimal constructor */
	public QuestionTable(Integer qid, String questText, String answer) {
		this.qid = qid;
		this.questText = questText;
		this.answer = answer;
	}

	/** full constructor */
	public QuestionTable(Integer qid, String questText, String answer,
			Integer mark, String a, String b, String c, String d) {
		this.qid = qid;
		this.questText = questText;
		this.answer = answer;
		this.mark = mark;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}

	// Property accessors

	public Integer getQid() {
		return this.qid;
	}

	public void setQid(Integer qid) {
		this.qid = qid;
	}

	public String getQuestText() {
		return this.questText;
	}

	public void setQuestText(String questText) {
		this.questText = questText;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getMark() {
		return this.mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getA() {
		return this.a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public String getB() {
		return this.b;
	}

	public void setB(String b) {
		this.b = b;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getD() {
		return this.d;
	}

	public void setD(String d) {
		this.d = d;
	}

}
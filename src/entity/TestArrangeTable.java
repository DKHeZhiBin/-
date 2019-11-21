package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * TestArrangeTable entity. @author MyEclipse Persistence Tools
 */

public class TestArrangeTable implements java.io.Serializable {

	// Fields

	private Integer id;
	private Teacher teacher;
	private String qidVess;
	private String beginTime;
	private Integer limitTime;
	private String class_;
	private Set scoreTables = new HashSet(0);

	// Constructors

	/** default constructor */
	public TestArrangeTable() {
	}

	/** minimal constructor */
	public TestArrangeTable(Integer id, Teacher teacher, String qidVess,
			String beginTime, Integer limitTime, String class_) {
		this.id = id;
		this.teacher = teacher;
		this.qidVess = qidVess;
		this.beginTime = beginTime;
		this.limitTime = limitTime;
		this.class_ = class_;
	}

	/** full constructor */
	public TestArrangeTable(Integer id, Teacher teacher, String qidVess,
			String beginTime, Integer limitTime, String class_, Set scoreTables) {
		this.id = id;
		this.teacher = teacher;
		this.qidVess = qidVess;
		this.beginTime = beginTime;
		this.limitTime = limitTime;
		this.class_ = class_;
		this.scoreTables = scoreTables;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getQidVess() {
		return this.qidVess;
	}

	public void setQidVess(String qidVess) {
		this.qidVess = qidVess;
	}

	public String getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public Integer getLimitTime() {
		return this.limitTime;
	}

	public void setLimitTime(Integer limitTime) {
		this.limitTime = limitTime;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public Set getScoreTables() {
		return this.scoreTables;
	}

	public void setScoreTables(Set scoreTables) {
		this.scoreTables = scoreTables;
	}

}
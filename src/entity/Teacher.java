package entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private Integer tid;
	private String tname;
	private String password;
	private Set testArrangeTables = new HashSet(0);

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(Integer tid, String tname, String password) {
		this.tid = tid;
		this.tname = tname;
		this.password = password;
	}

	/** full constructor */
	public Teacher(Integer tid, String tname, String password,
			Set testArrangeTables) {
		this.tid = tid;
		this.tname = tname;
		this.password = password;
		this.testArrangeTables = testArrangeTables;
	}

	// Property accessors

	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getTestArrangeTables() {
		return this.testArrangeTables;
	}

	public void setTestArrangeTables(Set testArrangeTables) {
		this.testArrangeTables = testArrangeTables;
	}

}
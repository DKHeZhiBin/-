package entity;

/**
 * AdminTable entity. @author MyEclipse Persistence Tools
 */

public class AdminTable implements java.io.Serializable {

	// Fields

	private Integer mid;
	private String password;

	// Constructors

	/** default constructor */
	public AdminTable() {
	}

	/** minimal constructor */
	public AdminTable(Integer mid) {
		this.mid = mid;
	}

	/** full constructor */
	public AdminTable(Integer mid, String password) {
		this.mid = mid;
		this.password = password;
	}

	// Property accessors

	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
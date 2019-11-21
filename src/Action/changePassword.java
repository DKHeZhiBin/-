package Action;

import tool.StudentDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.Student;

public class changePassword extends ActionSupport{
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	private String newPass;
	
	private String objectType;
	public String execute(){
		if(objectType.equals("student"))
		{
			StudentDAO sd = new StudentDAO();
			Student s = sd.findById(id);
			s.setPassword(newPass);
			sd.save(s);
		}
		return "goTestResult";
	}

}

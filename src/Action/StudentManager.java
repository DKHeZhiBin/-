package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.StudentDAO;
import tool.TeacherDAO;
import tool.TestArrangeTableDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.TestArrangeTable;
import entity.Student;
import entity.Teacher;

public class StudentManager extends ActionSupport{
	private int sid;
	private String sname;
	private String password;
	private String major;
	private String class_;
	private String method;
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClass_() {
		return class_;
	}
	public void setClass_(String class1) {
		class_ = class1;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	public String execute()
	{
		StudentDAO studao = new StudentDAO();

		if(method.equals("delete"))  {Student s = studao.findById(sid);studao.delete(s);}
		
		else if(method.equals("update")){
			Student s = studao.findById(sid);
			s.setSname(sname);
			s.setClass_(class_);
			s.setMajor(major);
			s.setPassword(password);
			studao.save(s);
	   }
	   else  if(method.equals("add"))
	   {
		    Student s = new Student();
		    s.setSid(sid);
			s.setSname(sname);
			s.setClass_(class_);
			s.setMajor(major);
			s.setPassword(password);
			studao.save(s);
			
	   }
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect("http://localhost:8080/ontest1/admin.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

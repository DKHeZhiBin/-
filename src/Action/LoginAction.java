package Action;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import entity.AdminTable;
import entity.Student;
import entity.Teacher;
import tool.AdminTableDAO;
import tool.StudentDAO;
import tool.TeacherDAO;
import tool.getTool;

public class LoginAction extends ActionSupport{
	int id;
	private String password;
	private String role;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}


	public String  Nativate() {
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if(role.equals("student"))
		{
			StudentDAO sd=new StudentDAO();
			
			Student s = sd.findById(id);
			if(s != null && s.getPassword().equals(password)) 
			{
				HttpServletRequest req = ServletActionContext.getRequest();
				req.getSession().setAttribute("student", s);
				try {
					response.sendRedirect("http://localhost:8080/ontest1/StudentMainPage.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else getTool.senderrormessage("您的id或者密码错误", "http://localhost:8080/ontest1/login.jsp");
		}
		
		else if(role.equals("teacher")) {
			TeacherDAO td=new TeacherDAO();
			Teacher t = td.findById(id); 
			if(t !=null && t.getPassword().equals(password)) 
			{
				HttpServletRequest req = ServletActionContext.getRequest();
				req.getSession().setAttribute("teacher", t);
				try {
					response.sendRedirect("http://localhost:8080/ontest1/TeacherMainPage.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else getTool.senderrormessage("您的id或者密码错误", "http://localhost:8080/ontest1/login.jsp");
		}
		
		else if(role.equals("admin"))
		{
			AdminTableDAO ad = new AdminTableDAO();
			AdminTable a = ad.findById(id);
			if(a !=null && a.getPassword().equals(password)) 
			{
				HttpServletRequest req = ServletActionContext.getRequest();
				req.getSession().setAttribute("admin", a);
				try {
					response.sendRedirect("http://localhost:8080/ontest1/admin.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else getTool.senderrormessage("您的id或者密码错误", "http://localhost:8080/ontest1/login.jsp");
		}
		return null;
	}
}

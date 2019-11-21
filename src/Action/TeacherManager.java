package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.TeacherDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.Student;
import entity.Teacher;

public class TeacherManager extends ActionSupport{
	private int tid;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	private String tname;
	private String password;
	private String method;
	public String execute(){
		TeacherDAO tdao = new TeacherDAO();
		if(method.equals("delete"))  {Teacher t = tdao.findById(tid);tdao.delete(t);}
		else if(method.equals("update")){
			Teacher t = tdao.findById(tid);
			t.setTname(tname);
			t.setPassword(password);
			tdao.save(t);
	   }
	   else if(method.equals("add"))
	   {
		   Teacher t =new Teacher();
			t.setTname(tname);
			t.setPassword(password);
			tdao.save(t);
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

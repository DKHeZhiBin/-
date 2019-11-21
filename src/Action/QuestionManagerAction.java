package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.QuestionTableDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionTable;

public class QuestionManagerAction extends ActionSupport{
	private int qid;
	String method;
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	
	public String getNewquestionText() {
		return NewquestionText;
	}
	public void setNewquestionText(String newquestionText) {
		NewquestionText = newquestionText;
	}
	public int getNewmark() {
		return Newmark;
	}
	public void setNewmark(int newmark) {
		Newmark = newmark;
	}
	public String getNewa() {
		return Newa;
	}
	public void setNewa(String newa) {
		Newa = newa;
	}
	public String getNewb() {
		return Newb;
	}
	public void setNewb(String newb) {
		Newb = newb;
	}
	public String getNewc() {
		return Newc;
	}
	public void setNewc(String newc) {
		Newc = newc;
	}
	public String getNewd() {
		return Newd;
	}
	public void setNewd(String newd) {
		Newd = newd;
	}
	public String getNewanswer() {
		return Newanswer;
	}
	public void setNewanswer(String newanswer) {
		Newanswer = newanswer;
	}
	
	
	private String NewquestionText;
	private int Newmark;
	private String Newa;
	private String Newb;
	private String Newc;
	private String Newd;
	private String Newanswer;
	
	public String execute() {
		QuestionTableDAO qd = new QuestionTableDAO();
		
		if(method.equals("delete"))  {QuestionTable q = qd.findById(qid);qd.delete(q);}
		else if(method.equals("update")){
			QuestionTable q = qd.findById(qid);
			q.setQuestText(NewquestionText);
			q.setMark(Newmark);
			q.setA(Newa);
			q.setB(Newb);
			q.setC(Newc);
			q.setD(Newd);
			q.setAnswer(Newanswer);
			qd.save(q);
	   }
	   else if(method.equals("add"))
	   {
		    QuestionTable q = new QuestionTable();
		    q.setQuestText(NewquestionText);
			q.setMark(Newmark);
			q.setA(Newa);
			q.setB(Newb);
			q.setC(Newc);
			q.setD(Newd);
			q.setAnswer(Newanswer);
			qd.save(q);
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

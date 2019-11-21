package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.QuestionTableDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionTable;

public class addQuestionAction extends ActionSupport{
	private String a;
	private String b;
	private String c;
	private String d;
    private String answer;
    private String questText;
	public String getQuestText() {
		return questText;
	}
	public void setQuestText(String questText) {
		this.questText = questText;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}

	
	private int mark;
	
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public String addQuestion(){
		QuestionTable question = new QuestionTable();
		question.setA(a);
		question.setB(b);
		question.setC(c);
		question.setD(d);
		question.setMark(mark);
		question.setAnswer(answer);
		question.setQuestText(questText);
		QuestionTableDAO qd = new QuestionTableDAO();
		qd.save(question);
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect("http://localhost:8080/ontest1/TeacherMainPage.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}

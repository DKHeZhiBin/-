package Action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.QuestionTableDAO;
import tool.ScoreTableDAO;
import tool.StudentDAO;
import tool.TestArrangeTableDAO;

import com.opensymphony.xwork2.ActionSupport;

import entity.TestArrangeTable;
import entity.QuestionTable;
import entity.ScoreTable;
import entity.Student;

public class ScoreManager extends ActionSupport{
	private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEid() {
		return Eid;
	}
	public void setEid(int eid) {
		Eid = eid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getSid() {
		return Sid;
	}
	public void setSid(int sid) {
		Sid = sid;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	private int Eid;
    private int score;
    private int Sid;
    private String method;
	public String execute(){
		ScoreTableDAO sd=new ScoreTableDAO();
		if(method.equals("update")){
			ScoreTable scoreTable = sd.findById(id);
			TestArrangeTableDAO pd=new TestArrangeTableDAO();
			TestArrangeTable paperTable = pd.findById(Eid);
			
			scoreTable.setTestArrangeTable(paperTable);
			scoreTable.setScore(score);
			StudentDAO studao = new StudentDAO();
			Student s = studao.findById(Sid);
			scoreTable.setStudent(s);
			sd.save(scoreTable);
	   }
	   else if(method.equals("add"))
	   {
		    ScoreTable scoreTable = new ScoreTable();
		    TestArrangeTableDAO pd=new TestArrangeTableDAO();
		    TestArrangeTable testArrange = pd.findById(Eid);
			
			scoreTable.setTestArrangeTable(testArrange);
			scoreTable.setScore(score);
			StudentDAO studao = new StudentDAO();
			Student s = studao.findById(Sid);
			scoreTable.setStudent(s);
			System.out.println(Eid);
			System.out.println(score);
			System.out.println(Sid);
			
			sd.save(scoreTable);
	   }
	   else if(method.equals("delete")) {ScoreTable s = sd.findById(id);sd.delete(s);}
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

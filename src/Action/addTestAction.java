package Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import tool.QuestionTableDAO;
import tool.TeacherDAO;
import tool.TestArrangeTableDAO;
import tool.getTool;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionTable;
import entity.Teacher;
import entity.TestArrangeTable;
public class addTestAction  extends ActionSupport{
	private int tid;
	private int[] randNum = new int[10];
	String BeginTime;
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int[] getRandNum() {
		return randNum;
	}
	public void setRandNum(int[] randNum) {
		this.randNum = randNum;
	}
	public String getBeginTime() {
		return BeginTime;
	}
	public void setBeginTime(String beginTime) {
		BeginTime = beginTime;
	}
	public String getTclass() {
		return Tclass;
	}
	public void setTclass(String tclass) {
		Tclass = tclass;
	}

	private int LimitTime;
	private String Tclass;
	
	public int getLimitTime() {
		return LimitTime;
	}
	public void setLimitTime(int limitTime) {
		LimitTime = limitTime;
	}
	
	public void senderrormessage(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>alert('当前班级该时间段已经有考试安排...');window.location='http://localhost:8080/ontest1/TeacherMainPage.jsp' </script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String  generateTest() throws ParseException{
		
			TestArrangeTable newTest = new  TestArrangeTable();
			newTest.setBeginTime(BeginTime);
			newTest.setClass_(Tclass);
			newTest.setLimitTime(LimitTime);
			TestArrangeTableDAO PD = new TestArrangeTableDAO();
			QuestionTableDAO QD = new QuestionTableDAO();
			Random rand = new Random();
			int i = 0;
			List list = QD.findAll();
			String qidVess="";
			int Totalnum=list.size();
			if(Totalnum<10) getTool.senderrormessage("对不起，题库中暂时没有足够的题目","http://localhost:8080/ontest1/StudentMainPage.jsp");
			else{
				while(i<10){                                                //随机从题库抽取10题选择题作为统考题
						int getRandNum = rand.nextInt(Totalnum);
						QuestionTable Randomquestion = (QuestionTable)list.get(getRandNum);
						qidVess = qidVess + Randomquestion.getQid()+",";
						list.remove(getRandNum);
						Totalnum= Totalnum-1;
						i++;
					}
					newTest.setQidVess(qidVess);
					TeacherDAO td = new TeacherDAO();
					Teacher t = td.findById(tid);
					newTest.setTeacher(t);
					PD.save(newTest);
					HttpServletResponse response = ServletActionContext.getResponse();
					try {
						response.sendRedirect("http://localhost:8080/ontest1/TeacherMainPage.jsp");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		return null;
	}
}

package Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

import tool.QuestionTableDAO;
import tool.StudentDAO;
import tool.TestArrangeTableDAO;
import tool.getTool;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionTable;
import entity.Student;
import entity.TestArrangeTable;
import tool.*;

public class chooseTestTypeAction extends ActionSupport{
	
	
	public String  testTypeOption() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String examType = request.getParameter("examType");
		QuestionTableDAO QD = new QuestionTableDAO();
		if(examType.equals("testYourself"))                     //如果是自我测试，就从题库随机选取10道题
		{
			List list = QD.findAll();
			int Totalnum=list.size();
			if(Totalnum<10) {getTool.senderrormessage("对不起，题库中暂时没有足够的题目","http://localhost:8080/ontest1/StudentMainPage.jsp");return null;}
			else{
				Random rand = new Random();
				List getRandQuestion = new ArrayList();
				int i = 0;
				while(i<10){                                       //随机选取10道题
					int getRandNum = rand.nextInt(Totalnum);
					Object Randomquestion = list.get(getRandNum);
					getRandQuestion.add(Randomquestion);
					list.remove(getRandNum);
					Totalnum= Totalnum-1;
					i++;
				}
				request.setAttribute("list", getRandQuestion);
				request.getSession().setAttribute("examtype", "testbyself");
				request.setAttribute("endtime", "0:0:0");
				return "begintest";
			}
		}
		
		
		else                                                       //班级统考的题目是统一的
		{
			
			Student s  = (Student)request.getSession().getAttribute("student");
			String Class_ = s.getClass_();              
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式 HH:mm:ss
	        String Today=df.format(new Date());                      // new Date()为获取当前系统时间
              
	        Session sess = getTool.getSessions();
			sess.beginTransaction();
			Query query = sess.createQuery("from TestArrangeTable where class_ ='"+Class_+"'");
			List<TestArrangeTable> AllTest = query.list();
	        for(TestArrangeTable t : AllTest) {
	        	
	        	String datetime = t.getBeginTime();
	        	String[] splitArray= datetime.split("T");
	        	String beginDate = splitArray[0];
	        	String beginTime = splitArray[1];

	        	if(Today.equals(beginDate)==false) continue;

	        	int limit = t.getLimitTime();                                   //获取该学生所在班级的所有测试，与今天的日期相比比较，且当前时间在考试时间范围内
	        	
	        	String[] temp = beginTime.split(":");
	        	int hour = Integer.parseInt(temp[0]);
	        	int minute = Integer.parseInt(temp[1]);
	        	int second = Integer.parseInt(temp[2]);
	        	                                                                   
	        	if(minute+limit<60)   minute=minute+limit;                      // 计算考试结束时间
	        	else{
	        		hour = hour+(minute+limit)/60;
	        		minute=(minute+limit)%60;
	        	}
	        	String endTime = hour+":"+minute+":"+second;
	        	
	        	if(getTool.isInDate(System.currentTimeMillis(), beginTime, endTime)) {   //在考试时间范围的话就获取试卷题目  
	        		QuestionTableDAO qd =new QuestionTableDAO();
	        		List list = new ArrayList();
	        		String[] qidVesse = t.getQidVess().split(",");
	        		for(String str : qidVesse) {
	        			int id = Integer.parseInt(str);
	        			list.add(qd.findById(id));
	        		}
	        		request.setAttribute("list", list);
	        		
	        		request.setAttribute("endtime", endTime);
	        		request.getSession().setAttribute("examtype", "testunit");
	        		request.getSession().setAttribute("testarrangetable", t);
	        		return "begintest";
	        	}
	        }
	        getTool.senderrormessage("'亲，你当前没有考试哦！","http://localhost:8080/ontest1/StudentMainPage.jsp");     //否则就拒绝进入考试
	        return null;
		}
	}
		
		
			
}


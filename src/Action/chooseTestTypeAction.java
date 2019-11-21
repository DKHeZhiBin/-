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
		if(examType.equals("testYourself"))                     //��������Ҳ��ԣ��ʹ�������ѡȡ10����
		{
			List list = QD.findAll();
			int Totalnum=list.size();
			if(Totalnum<10) {getTool.senderrormessage("�Բ����������ʱû���㹻����Ŀ","http://localhost:8080/ontest1/StudentMainPage.jsp");return null;}
			else{
				Random rand = new Random();
				List getRandQuestion = new ArrayList();
				int i = 0;
				while(i<10){                                       //���ѡȡ10����
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
		
		
		else                                                       //�༶ͳ������Ŀ��ͳһ��
		{
			
			Student s  = (Student)request.getSession().getAttribute("student");
			String Class_ = s.getClass_();              
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ HH:mm:ss
	        String Today=df.format(new Date());                      // new Date()Ϊ��ȡ��ǰϵͳʱ��
              
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

	        	int limit = t.getLimitTime();                                   //��ȡ��ѧ�����ڰ༶�����в��ԣ�������������ȱȽϣ��ҵ�ǰʱ���ڿ���ʱ�䷶Χ��
	        	
	        	String[] temp = beginTime.split(":");
	        	int hour = Integer.parseInt(temp[0]);
	        	int minute = Integer.parseInt(temp[1]);
	        	int second = Integer.parseInt(temp[2]);
	        	                                                                   
	        	if(minute+limit<60)   minute=minute+limit;                      // ���㿼�Խ���ʱ��
	        	else{
	        		hour = hour+(minute+limit)/60;
	        		minute=(minute+limit)%60;
	        	}
	        	String endTime = hour+":"+minute+":"+second;
	        	
	        	if(getTool.isInDate(System.currentTimeMillis(), beginTime, endTime)) {   //�ڿ���ʱ�䷶Χ�Ļ��ͻ�ȡ�Ծ���Ŀ  
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
	        getTool.senderrormessage("'�ף��㵱ǰû�п���Ŷ��","http://localhost:8080/ontest1/StudentMainPage.jsp");     //����;ܾ����뿼��
	        return null;
		}
	}
		
		
			
}


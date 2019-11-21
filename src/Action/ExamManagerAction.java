package Action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.QuestionTableDAO;
import tool.TeacherDAO;
import tool.TestArrangeTableDAO;
import tool.getTool;

import com.opensymphony.xwork2.ActionSupport;

import entity.Teacher;
import entity.TestArrangeTable;

public class ExamManagerAction extends ActionSupport{
	private String method;
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getEid() {
		return Eid;
	}

	public void setEid(int eid) {
		Eid = eid;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getClass_() {
		return class_;
	}

	public void setClass_(String class1) {
		class_ = class1;
	}

	public int getLimitTime() {
		return limitTime;
	}

	public void setLimitTime(int limitTime) {
		this.limitTime = limitTime;
	}

	public int getTid() {
		return Tid;
	}

	public void setTid(int tid) {
		Tid = tid;
	}

	private int Eid;
	private String beginTime;
	private String class_;
	private int limitTime;
	private int Tid;
	private String qidVess;
	
	public String getQidVess() {
		return qidVess;
	}

	public void setQidVess(String qidVess) {
		this.qidVess = qidVess;
	}

	public String execute() {
		TestArrangeTableDAO qd = new TestArrangeTableDAO();
		
		if(method.equals("delete"))  {TestArrangeTable q = qd.findById(Eid);qd.delete(q);}
		else if(method.equals("update")){
			TestArrangeTable q = qd.findById(Eid);
			TeacherDAO td = new TeacherDAO();
			Teacher teacher = td.findById(Tid);
			q.setTeacher(teacher);
			q.setBeginTime(beginTime);
			q.setLimitTime(limitTime);
			q.setQidVess(qidVess);
			q.setClass_(class_);
			qd.save(q);
	   }
	   else if(method.equals("add"))
	   {
		   TestArrangeTable q = new TestArrangeTable();
		   TeacherDAO td = new TeacherDAO();
		   Teacher teacher = td.findById(Tid);
		   if(teacher==null)  /* 判断是否存在教师 */
			   getTool.senderrormessage("不存在的教师号..", "http://localhost:8080/ontest1/admin.jsp");
		   else{
			  /* 判断设置的考试时间是否合法 */
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String[] temp  = beginTime.split("T");
				try {
					Date beginDate = df.parse(temp[0]+" "+temp[1]);
					Date currtime = new Date();              //当前时间
					if(beginDate.getTime()<currtime.getTime()) 
						getTool.senderrormessage("您输入的考试时间过时了", "http://localhost:8080/ontest1/admin.jsp");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/*判断输入的题号是否格式匹配*/
			    String regex = "[0-9,]{1,}";
			    Pattern p=Pattern.compile(regex);
			    Matcher m=p.matcher(qidVess);
			    if(!m.matches()) 
			    	getTool.senderrormessage("您输入的题号格式错误", "http://localhost:8080/ontest1/admin.jsp");
			    else{
			    	/*判断是否能找到相应的试题*/
			    	QuestionTableDAO questiondao = new QuestionTableDAO();
			    	String[] vess = qidVess.split(",");
			    	boolean flag = true;
			    	for(String v : vess)
			    	{	
			    		if(questiondao.findById(Integer.parseInt(v))==null) 
			    			flag=false;
			    	}
			    	if(flag==false) 
			    		getTool.senderrormessage("无法找到试题号所对应的信息", "http://localhost:8080/ontest1/admin.jsp");
			    	else if(class_.getBytes().length > 48 || class_.getBytes().length == 0 )  /*判断班级的字符大小是否合理*/
			    		getTool.senderrormessage("参考班级名长度不合理", "http://localhost:8080/ontest1/admin.jsp");
			    	else    /*所有信息均正确才存入信息*/
			    	{
			    		getTool.senderrormessage("添加成功", "http://localhost:8080/ontest1/admin.jsp");
			    		q.setBeginTime(beginTime);
			    		q.setTeacher(teacher);
			    		q.setLimitTime(limitTime);
			    		q.setQidVess(qidVess);
			    		q.setClass_(class_);
		     		    qd.save(q);
			    	}
			    } 
		   }  
	   }
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.sendRedirect("http://localhost:8080/ontest1/admin.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}

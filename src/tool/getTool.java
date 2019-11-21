package tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class getTool {
	public static Session getSessions(){
		Configuration con = new Configuration().configure();
        SessionFactory sessf = con.buildSessionFactory();
        Session sess = sessf.openSession();
		return sess;
	}
	
	public static boolean isInDate(long time,String strDateBegin,String strDateEnd) {
	      Calendar calendar = Calendar.getInstance();
	      // 处理开始时间
	      String[] startTime = strDateBegin.split(":");
	      calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(startTime[0]));
	      calendar.set(Calendar.MINUTE, Integer.valueOf(startTime[1]));
	      calendar.set(Calendar.SECOND, Integer.valueOf(startTime[2]));
	      calendar.set(Calendar.MILLISECOND, 0);
	      long startTimeL = calendar.getTimeInMillis();
	      // 处理结束时间
	      String[] endTime = strDateEnd.split(":");
	      calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(endTime[0]));
	      
	      calendar.set(Calendar.MINUTE, Integer.valueOf(endTime[1]));
	      calendar.set(Calendar.SECOND, Integer.valueOf(endTime[2]));
	      calendar.set(Calendar.MILLISECOND, 0);
	      long endTimeL = calendar.getTimeInMillis();
	      return time >= startTimeL && time <= endTimeL;
	   }
	
	public static void senderrormessage(String errormessage,String url){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>alert('"+errormessage+"...');window.location='"+url+"' </script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


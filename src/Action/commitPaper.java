package Action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import tool.ScoreTableDAO;
import tool.getTool;

import com.opensymphony.xwork2.ActionSupport;

import entity.QuestionTable;
import entity.ScoreTable;
import entity.Student;
import entity.TestArrangeTable;

public class commitPaper extends ActionSupport{
	private int sum = 0;
	private int size;
	private String[] StudentAnswer ={"","","","","","","","","",""};
	private int correctNum = 0;


	public int getCorrectNum() {
		return correctNum;
	}

	public void setCorrectNum(int correctNum) {
		this.correctNum = correctNum;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String[] getStudentAnswer() {
		return StudentAnswer;
	}

	public void setStudentAnswer(String[] studentAnswer) {
		StudentAnswer = studentAnswer;
	}


	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String judgePaper(){
		HttpServletRequest request = ServletActionContext.getRequest();
	    List<QuestionTable> list = (List<QuestionTable>)request.getSession().getAttribute("tempQuestion"); //��ȡ��������
		int i = 0;
		for(QuestionTable q : list)
		{
			String correctAnsw = q.getAnswer(); //��ȡ������ȷ��
		    if(StudentAnswer[i].equals(correctAnsw)) //�ж�ѧ���ύ�Ĵ��Ƿ���ȷ���Ʒ�
		    {
		    	sum = sum+q.getMark();
		    	correctNum = correctNum+1;
		    }
		    i = i+1;
		}
		String examtype = request.getSession().getAttribute("examtype").toString();
		if(examtype.equals("testunit"))          //����ǰ༶ͳ����Ҫ���ɼ��������ݿ�
		{
			
			ScoreTableDAO sd = new ScoreTableDAO();
			TestArrangeTable tat = (TestArrangeTable)request.getSession().getAttribute("testarrangetable");
			Student s = (Student)request.getSession().getAttribute("student");
			List l = sd.findAll();
			for(Object t:l)
			{
				ScoreTable score = (ScoreTable)t;
				//�ж�ѧ���Ƿ��Ѿ��μӹ��ÿ���
				if(score.getStudent().getSid().intValue()==s.getSid().intValue() && score.getTestArrangeTable().getId().intValue()==tat.getId().intValue())
				{
					getTool.senderrormessage("���Ѿ��μӹ��ÿ���","http://localhost:8080/ontest1/StudentMainPage.jsp' </script>");  
				}
			}
			ScoreTable newscore = new ScoreTable();
			newscore.setScore(sum);
			newscore.setStudent(s);
			newscore.setTestArrangeTable(tat);
			sd.save(newscore);
		}
		return "goTestResult";
	}

}

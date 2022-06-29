package co.edu;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/FullCalenderServ")
public class FullCalenderServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FullCalenderServ() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		
		EmpDAO dao = new EmpDAO();
		List<CalendarVO> schedules =dao.getSchedule();
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(schedules));
		
		//[{title,startDate, endDate},{title,startDate}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//입력정보 한글포함
		request.setCharacterEncoding("utf-8");
		
		
		// 파라미터정보: cmd=insert, title=입력한값, start=입력한값, end=입력한값
	      String cmd, title, start, end;
	      cmd = request.getParameter("cmd");
	      title = request.getParameter("title");
	      start = request.getParameter("start");
	      end = request.getParameter("end");
	      
	      EmpDAO dao = new EmpDAO();
	      if (cmd.equals("insert")) {
	         CalendarVO vo = new CalendarVO();
	         // 사용자 입력값을 vo 셋팅
	         vo.setTitle(title);
	         vo.setStartDate(start);
	         vo.setEndDate(end);
	         
	         // 정상적으로 입력처리가 되면
	         if(dao.insertSchedule(vo)) {
	            response.getWriter().print("{\"retCode\":\"Success\"}");
	         }else {
	            response.getWriter().print("{\"retCode\":\"Fail\"}");
	         }
	      }else if (cmd.equals("delete")) {
	         
	      }
	   }

	}


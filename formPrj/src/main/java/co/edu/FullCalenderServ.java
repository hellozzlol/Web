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
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		EmpDAO dao = new EmpDAO();
		List<CalendarVO> schedules =dao.getSchedule();
		
		Gson gson = new GsonBuilder().create();
		response.getWriter().print(gson.toJson(schedules));
		
		//[{title,startDate, endDate},{title,startDate}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

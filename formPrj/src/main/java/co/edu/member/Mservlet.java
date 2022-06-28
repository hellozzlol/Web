package co.edu.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet({ "/memberservlet", "/fileupload" })
public class Mservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Mservlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isMulti = ServletFileUpload.isMultipartContent(request);

		if (isMulti) {

			// 요청정보
			String mn = request.getParameter("memberName");

			String file = request.getServletContext().getRealPath("images");
			int fileSize = 5 * 1023 * 1024;// 5메가
			MultipartRequest mr = new MultipartRequest(request, file// 파일이름
					, fileSize// 파일사이즈
					, "utf-8"// 인코딩타입
					, new DefaultFileRenamePolicy()// 리네임정책
			);

			mn = mr.getParameter("memberName");
			String ph = mr.getParameter("phone");
			String ad = mr.getParameter("address");
			String bi = mr.getParameter("birth");
			String im = mr.getParameter("image");
			im = mr.getFilesystemName("image");

			membervo vo = new membervo();
			vo.setMembname(mn);
			vo.setMembaddr(ad);
			vo.setMembphone(ph);
			vo.setMembbirth(bi);
			vo.setMembImage(im);

			MemberDAO dao = new MemberDAO();
			Gson gson = new GsonBuilder().create();
			PrintWriter out = response.getWriter();

			dao.insertMember(vo);
			System.out.println(mn);
//{"retCode": "Fullfilled"}
			out.print("{\"retCode\": \"Fullfilled\"}");

		} else {
			String cmd = request.getParameter("cmd");
			String id = request.getParameter("delId");

			if (cmd.equals("delete")) {
				MemberDAO dao = new MemberDAO();
				PrintWriter out = response.getWriter();
				if (dao.deleteMember(Integer.parseInt(id))) {
					
					out.print("{\"retCode\" : \"success\"}");
				} else {
					out.print("{\"retCode\": \"Fail\"}");
				}
			}
		}
	}
}

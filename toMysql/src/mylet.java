import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DB.managsql;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


public class mylet extends HttpServlet {

	
	public mylet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
      	request.setCharacterEncoding("utf-8");
		managsql.connectsql();
	//	response.getOutputStream().print(managsql.initseat());//��λ��ʼ��
	//	String stucardid = request.getParameter("name");
	//	String stupwd = request.getParameter("pwd");
	//	System.out.println(stucardid+" "+stupwd);
	//	response.getOutputStream().print(managsql.logininfo(stucardid,stupwd));//��½��֤
//		String floornum = request.getParameter("num");
//		System.out.println(floornum);
//		response.getOutputStream().print(managsql.getfloor(floornum));
//		String stuid = request.getParameter("stuid");
//		System.out.println(stuid);
//		response.getOutputStream().print(managsql.gethistoryinfo(stuid));
//		String sql= request.getParameter("sql");
//		String infonum = request.getParameter("num");
		//response.getOutputStream().print(managsql.excu("select stucardid,stupwd from student;","3"));
		//System.out.println(request.getParameter("name"));
		String sql=request.getParameter("sql");
		System.out.println(sql);
		String num = request.getParameter("num");
		System.out.print(num);
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().print(managsql.excu(sql,num)); 
		response.getWriter().close();
      	
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

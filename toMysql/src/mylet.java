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
import com.Thread.cleanseat;
import com.Thread.run;
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
      	String  strflag=null;
      	int     flag=0;
      	String sql=null;
      	String strnum = null;
      	String datetimebeg = null;
      	String datetimeend = null;
      	String time=null;	
      	String seatid = null;
		response.setContentType("text/html; charset=utf-8");
		strflag = request.getParameter("flag");
		flag = Integer.parseInt(strflag);
		switch(flag){
		case 1:
			sql=request.getParameter("sql");
			System.out.println(sql);
			response.getWriter().print(managsql.excuSelect(sql)); 
			break;
		case 2:
			sql=request.getParameter("sql");
			System.out.println(sql);
			response.getWriter().print(managsql.excu_update_delete_insert(sql)); 
			break;
		case 3:
			response.getWriter().print(managsql.useinfo_present()); 
			break;
		case 4:
			response.getWriter().print(managsql.useinfo_present_advance_hascom()); 
			break;
		case 5:
			time = request.getParameter("time");
			System.out.print(time);
			response.getWriter().print(managsql.useinfo_psecent_nocom(time)); 
			break;
		case 6:
			datetimebeg = request.getParameter("begintime");
			datetimeend = request.getParameter("timeend");
			System.out.println(datetimebeg);
			System.out.println(datetimeend);
			response.getWriter().print(managsql.useinfo_Advance(datetimebeg, datetimeend)); 
			break;
		case 7:
			response.getWriter().print(managsql.Welcome()); 
			break;
		case 8:
			seatid = request.getParameter("seatid");
			System.out.println(seatid);
			response.getWriter().print(managsql.neartime_seat(seatid)); 
		//	System.out.println(managsql.neartime_seat(seatid));
			break;
		default:
			response.getWriter().print("error");
			break;					
		}
		response.getWriter().close();
	//	response.getOutputStream().print(managsql.initseat());//座位初始化
	//	String stucardid = request.getParameter("name");
	//	String stupwd = request.getParameter("pwd");
	//	System.out.println(stucardid+" "+stupwd);
	//	response.getOutputStream().print(managsql.logininfo(stucardid,stupwd));//登陆验证
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
		
//		String sql=request.getParameter("sql");
//		System.out.println(sql);
//		String num = request.getParameter("num");
//	//	System.out.print(num);
//		response.setContentType("text/html; charset=utf-8");
//		response.getWriter().print(managsql.excu(sql,num)); 
//		response.getWriter().close();
      	
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
	//	System.out.println("ddddddddddd");
			if(managsql.connectsql().equals("succeed")){;
			Thread cleaninfo = new Thread(new run());
			 cleaninfo.start();
			 Thread cleantable = new Thread(new cleanseat());
			 cleantable.start();
			}
	
	}

}

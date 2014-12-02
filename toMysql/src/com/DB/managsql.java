/**
 * 
 */
package com.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * @author Administrator
 *
 */
public class managsql {

	private static  Connection conn=null;
	private static PreparedStatement pre=null;
	private static final String url="jdbc:mysql://121.41.51.176:3306/cdio_1_9_DB?useUnicode=true&characterEncoding=UTF-8";//������ݿ����
	private static final String userName="cdio_1_9";
	private static final String password="cdio";
	private static StringBuilder str=null;
	private static ResultSet rs=null;
	private static Statement statement;
	public managsql() {
		
	}
	
	public static void connectsql(){		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
       try {
			conn = (Connection) DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static String  excu(String sql,String num){	
//		sql = "update student set stucardid = '123012012080' where stucardid = '123012012111';";
		
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		if(sql.indexOf("select") != -1){
			  try {
					rs = pre.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
		
		 str = new StringBuilder();
		 int realnum = Integer.parseInt(num);
	//	 System.out.print(realnum);
	     
	      
	      try {
	    	  	if(rs==null){
	    	  		return null;
	    	  	}
	    	  	else{
//	    	  		if(rs.equals(true))
//	    	  			return "true";
	    	  		while(rs.next()){
	    	  			for(int i=0;i<realnum;i++){
	    	  				if(i==realnum-1)
							str.append(rs.getString(i+1));
						else{
					    str.append(rs.getString(i+1)+",");
						}
				    }
					str.append("\n");
	    	  		}
	    	  	}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	return str.toString();	
		}
		else if(sql.indexOf("select") == -1){
			 try {
				 pre = (PreparedStatement) conn.prepareStatement(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			try {
				int row = pre.executeUpdate();	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return sql;
		}
		return null;
	
	}
	
	public static String  initseat(){		
		 String sql="select seatid,seatnum,posX,posY from seat;";
		 str = new StringBuilder();
	      try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      try {
	    	  		
				while(rs.next()){
				    str.append(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+"\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	return str.toString();	
		
	}
	
	public static String  logininfo(String stucardid,String stupwd){
		
	//	System.out.println(stucardid+" "+stupwd);
		String stucard = null;
		String stupd = null;
		String sql=null;
				
		sql="select * from student where stucardid="+stucardid+" and stupwd="+stupwd+";";//��ѯuser�����
		 str = new StringBuilder();
	      try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      try { 
	    
	    	  		while(rs.next()){
	    	  			
	    	  			stucard="\""+rs.getString(1)+"\"";
	    	  			stupd="\""+rs.getString(2)+"\"";
	    	  			
	    	  			System.out.println(stucard+" "+stupd);
	    	  			
		    	  		if(stucard.equals(stucard)&&(stupd.equals(stupd))){
		    	  			
		    	  			return "YES";
		    	  		}
		    	  		else
		    	  			return "NO";
	    	  		}				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	return "NO";	
		
	}
	
	public static String  getfloor(String floornum){	
		
		 String sql="select floorunable from floord where floornum="+floornum+";";//��ѯuser�����
		 System.out.print(sql);
		 String floorn=null;
		 str = new StringBuilder();
	      try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      try {
	    	  		
				while(rs.next()){
					floorn = rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	return floorn;
		
	}
	
	public static String  gethistoryinfo(String stuid){		
		
		String sql=null;
//		 String sql="select a.floornum,b.seatnum,c.begintime,c.endtime from floord a,seat b,history c where a.floorid=b.floorid and b.seatid=c.seatid
//";//��ѯuser�����
		 str = new StringBuilder();
	      try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	      try {
	    	  		
				while(rs.next()){
				    str.append(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+"\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	return str.toString();	
		
	}
	

}

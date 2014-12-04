/**
 * 
 */
package com.DB;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
	private static PreparedStatement pre2=null;
	private static final String url="jdbc:mysql://121.41.51.176:3306/cdio_1_9_DB?useUnicode=true&characterEncoding=UTF-8";//链接数据库语句
	private static final String userName="cdio_1_9";//服务器名称
	private static final String password="cdio";//服务器密码
	private static StringBuilder str=null;
	private static ResultSet rs=null;
	private static ResultSet rs2=null;
	private static Calendar cal= Calendar.getInstance();
	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static List<String> id = new ArrayList<String>();
	public managsql() {
		
	}
	
	/**
	 * 连接Mysql数据库函数
	 * @return
	 */
	public static String connectsql(){		
		try {
			Class.forName("com.mysql.jdbc.Driver");//设置JDBC驱动
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
       try {
			conn = (Connection) DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "fail";
		}
       return "succeed";
		
	}
	
	
	/**
	 * 执行查询事务
	 * 参数：sql--要执行的Mysql语句
	 * 返回值：成功--查询结果
	 * 		失败--NO
	 * @return
	 */
	public static String  excuSelect(String sql){		
		
	//		System.out.print(sql);
	//	Statement statement = null;
		 str = new StringBuilder();
		 int realnum = 0;
	//	 System.out.print(realnum);
	      try {
				pre = (PreparedStatement) conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE,ResultSet.HOLD_CURSORS_OVER_COMMIT);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				realnum=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	      try {
	    	  
	    	  if(rs.next()==false)
	    		  return "NO";
	    	  else{
	    		  	rs.first();//返回结果集头部
	    		  	for(int i=0;i<realnum;i++){
	    		  		if(i==realnum-1)
	    		  			str.append(rs.getString(i+1));
	    		  		else{
	    		  			str.append(rs.getString(i+1)+",");
					}
	    		  }
	    		  	str.append("\n");
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
	      System.out.print(str.toString());
	      
	      	return str.toString();	
		
	}
	
	/**
	 * 执行  插入，删除，修改语句
	 * 
	 * 参数：sql--要执行的Mysql语句
	 * 返回值：成功--succeed
	 * 		失败--fail
	 * @return
	 */
	public static String  excu_update_delete_insert(String sql){		
		
			 str = new StringBuilder();
			 int num = 0;
		//	 System.out.print(realnum);
		      try {
					pre = (PreparedStatement) conn.prepareStatement(sql);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		      try {
		    	  	num = pre.executeUpdate();
		    		if(num>0){
		    	  		return "succeed";
		    	  	}
		    		else
		    			return "fail";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
		      return "fail";
		     		    	  
		}
	
	
	/**
	 * 连接测试函数
	 * 
	 * @return
	 */
	public static String Welcome(){
		return "welcome to assistant";
	}
	
	
	/**
	 * 根据时间段筛选预定占用信息
	 * 参数：timebeg---起始时间 ， timeend---终止时间
	 * 返回值：成功--查询结果
	 * 		失败--NO
	 * @return
	 */
	public static String useinfo_Advance(String timebeg,String timeend){
		 str = new StringBuilder();
		 Date dateben = new Date();
		 Date dateend = new Date();
		 String sql = "select * from advancetb;";
		 String sql1 =null;
		 String strr="";
		 int num=0;
		 SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			dateben = simpledate.parse(timebeg);
			dateend = simpledate.parse(timeend);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    		 // 	try {
						try {
							id.clear();
							while(rs.next()){
								//System.out.print("xx"+rs.getString(4)+"  "+rs.getString(5)+"\n");
							try {
								if(!((simpledate.parse(rs.getString(4)).compareTo(dateend)>0)||(simpledate.parse(rs.getString(5)).compareTo(dateben)<0))){
									System.out.print("pp"+rs.getString(4)+"  "+rs.getString(5)+"\n");							
									sql1="select posX,posY from seat where seatid='"+rs.getString(2)+"';";
									id.add(rs.getString(2));									
									//str.append("\n");
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
							
							}
							for(String str1:id){
								strr+=getpos(str1);
							}	
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
						} 
				
	 //    System.out.println(strr);
		return strr;
	}
	
	
	/**
	 * 根据当前时间筛选返回预订信息
	 * 参数：time---当前时间
	 * 返回值：成功--查询结果
	 * 		失败--NO
	 * @return
	 */
	public static String useinfo_psecent_nocom(String time){
		 str = new StringBuilder();
		 Date datetime = new Date();
		 String sql = null;
		 int num=0;
		 SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {			
			datetime = simpledate.parse(time);
			sql = "select seat.posX,seat.posY from seat,advancetb where advancetb.seatid=seat.seatid and advancetb.iscom='0' and advancetb.advbegintime " +
					"< '"+time+"'  and advancetb.advendtime > '"+time+"';";
			System.out.println(sql);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	      try {	    	  
	    	  if(rs.next()==false)
	    		  return "NO";
	    	  else{
	    		  	rs.first();
	    		  	for(int i=0;i<num;i++){
	    		  		if(i==num-1)
	    		  			str.append(rs.getString(i+1));
	    		  		else{
	    		  			str.append(rs.getString(i+1)+",");
					}
	    		  }
	    		  	str.append("\n");
	    		  	while(rs.next()){
	    	  		for(int i=0;i<num;i++){
	    	  			if(i==num-1)
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
	
	public static String getpos(String id){
		 str = new StringBuilder();
		 String sql = "select posX,posY from seat where seatid='"+id+"'";
		 int num=0;
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    		  	try {
						while(rs.next()){
						for(int i=0;i<num;i++){
							if(i==num-1)
							str.append(rs.getString(i+1));
							else{
						    str.append(rs.getString(i+1)+",");
							}
						}
						str.append("\n");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	   // System.out.println(str.toString());	  	
		return str.toString();
	}
	
	/**
	 * 返回现场选坐表信息
	 * 
	 * 返回值：成功--查询结果
	 * 		失败--NO
	 * @return
	 */
	
	public static String useinfo_present(){
		 str = new StringBuilder();
		 int num=0;
		 String sql = "select seat.posX,seat.posY from seat,scenetb where seat.seatid=scenetb.sceneid; ";
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	      try {	    	  
	    	  if(rs.next()==false)
	    		  return "NO";
	    	  else{
	    		  	rs.first();
	    		  	for(int i=0;i<num;i++){
	    		  		if(i==num-1)
	    		  			str.append(rs.getString(i+1));
	    		  		else{
	    		  			str.append(rs.getString(i+1)+",");
					}
	    		  }
	    		  	str.append("\n");
	    		  	while(rs.next()){
	    	  		for(int i=0;i<num;i++){
	    	  			if(i==num-1)
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
	
	
	/**
	 * 返回预定表中到场信息
	 *
	 * @return
	 */
	public static String useinfo_present_advance_hascom(){
		 str = new StringBuilder();
		 int num=0;
		 String sql = "select seat.posX,seat.posY from seat,advancetb where seat.seatid=advancetb.seatid and advancetb.iscom='1';";
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	      try {	    	  
	    	  if(rs.next()==false)
	    		  return "NO";
	    	  else{
	    		  	rs.first();
	    		  	for(int i=0;i<num;i++){
	    		  		if(i==num-1)
	    		  			str.append(rs.getString(i+1));
	    		  		else{
	    		  			str.append(rs.getString(i+1)+",");
					}
	    		  }
	    		  	str.append("\n");
	    		  	while(rs.next()){
	    	  		for(int i=0;i<num;i++){
	    	  			if(i==num-1)
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
	
	
	public static String neartime_seat(String seatid){
		 str = new StringBuilder();
		 int num=0;
		 Date neardate = new Date();
		 String sql = "select min(advbegintime) from advancetb where iscom = '0' and seatid='"+seatid+"';";
		 try {
				pre = (PreparedStatement) conn.prepareStatement(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      try {
				rs = pre.executeQuery();
			//	System.out.print(rs.getRow());
				num=rs.getMetaData().getColumnCount();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	      	try {
	      		rs.next();
				if(rs.getString(1)==null){
					return "NO";
				}else{
					try {
						neardate = dateformat.parse(rs.getString(1));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  System.out.println(dateformat.format(neardate));
				  return dateformat.format(neardate);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	try {
				System.out.print(rs.getString(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      	return "NO";
	}
	
	
	/**
	 * 线程函数，遍历预订信息表，定时删除超时信息，以及时间到时座位的释放
	 * 
	 * @return
	 */
	
	public static void cleanAdcance(){	
//		Thread thread = new Thread(new Runnable() {			
//			public void run() {
				// TODO Auto-generated method stub
		//		System.out.println("i am running");
				Date date = new Date();
				Date datenow = null;
				 str = new StringBuilder();
				 String sql = "select * from advancetb;";
				 try {
						pre = (PreparedStatement) conn.prepareStatement(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			      try {
						rs = pre.executeQuery();
					//	System.out.print(rs.getRow());
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();						
					}
			    try {
					while(rs.next()){
							if(rs.getString(6).equals("0")){
								try {
									datenow = new Date();
									String strdatenow = dateformat.format(datenow);
									String strdate = dateformat.format(dateformat.parse(rs.getString(4)).getTime()+1800000);
									if(datenow.compareTo(dateformat.parse(strdate))==0){
										if(excu_update_delete_insert("delete from advancetb where seatid='"+rs.getString(3)+"';").trim().equals("succeed")){
											System.out.printf("stuid:%s seatid:%s cannot incom delete the seat",rs.getString(2),rs.getString(3));							
										}
									}
				//					System.out.print(strdatenow+"\n"+strdate+"\n"+strdate1+"\n"+dateformat.format(dateformat.parse(strdate).getTime()-1800000)+"\n"+sqldate+"\n");
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}						
								
							}
							else if(rs.getString(6).equals("1")){
								
								datenow = new Date();
								try {
									if(datenow.compareTo(dateformat.parse(rs.getString(5)))==0){
										String tohis="delete from advancetb where seatid='"+rs.getString(2)+"';insert into history value('','"+rs.getShort(3)
										+"','"+rs.getString(2)+"',"+"'"+rs.getString(4)+"','"+rs.getString(5)+"');";
										System.out.println(tohis);
										System.out.println(rs.getString(4));
										if(excu_update_delete_insert(tohis).trim().equals("succeed")){
											System.out.printf("stuid:%s seatid:%s his outtime delete the seat to history",rs.getString(2),rs.getString(3));							
										}
									}
								} catch (ParseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
			
							}
						  		
							  	
						  }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			    	  					
				
	//		}
	//	});
		
	}
}

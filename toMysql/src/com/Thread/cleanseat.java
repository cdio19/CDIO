package com.Thread;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask; 

public class cleanseat implements Runnable{ 
	 
	public static	java.util.Timer timer = new java.util.Timer(true);
	public  static boolean finished;
	public static void cleanintime(){
		
	}

	private static Date getNextDate(){   
		Calendar cal = Calendar.getInstance();    
		  if (cal.get(Calendar.HOUR_OF_DAY) > 22) { 
			// �Ƿ��Ѿ�����5����      
			cal.add(Calendar.DAY_OF_YEAR, 1); // ֻ��������   
			} 
			cal.set(Calendar.HOUR_OF_DAY,22);
			cal.set(Calendar.MINUTE,30);			
			return cal.getTime();  	  
		}

	public void run() {
		// TODO Auto-generated method stub
		while (true) { 
			// ������һ��ѭ��      
			System.out.print("i am busy");
			finished = false;      
			timer.schedule(new cleanwork(), getNextDate()); 
			// ������һ��ִ������     
			while (!finished) { 
				// ������Ӧ��ֹͣ����        
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}      
				}    
			}     
	}	
}

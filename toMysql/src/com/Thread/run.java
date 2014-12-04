/**
 * 
 */
package com.Thread;

import com.DB.managsql;

/**
 * @author Administrator
 *
 */
public class run implements Runnable {
	
	//线程函数，监听预定表信息，及其座位的释放
	public void run() {
		// TODO Auto-generated method stub	
		while(true){
			managsql.cleanAdcance();
		}
		//System.out.println("MessageServer is runing.....");
		
	}

}

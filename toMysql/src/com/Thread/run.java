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
	
	//�̺߳���������Ԥ������Ϣ��������λ���ͷ�
	public void run() {
		// TODO Auto-generated method stub	
		while(true){
			managsql.cleanAdcance();
		}
		//System.out.println("MessageServer is runing.....");
		
	}

}

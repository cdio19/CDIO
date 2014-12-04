package com.Thread;

import java.util.TimerTask;

import com.DB.managsql;

class cleanwork extends TimerTask {  
		public void run() {
			if(managsql.excu_update_delete_insert("SET SQL_SAFE_UPDATES = 0; delete from scenetb;").equalsIgnoreCase("succeed")){
				System.out.println("the data has clean OK");
			cleanseat.finished = true;  
			}
			cleanseat.finished = false; 
		}		
}
			
		
package com.home.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OracleHome {
	private static DataSource datasource = null;
	private static Context context = null;
	
	public static DataSource DataSourceConn(){
		
		if(datasource!=null){
			return datasource;
		}
		
		try{
			if(context == null){
				context = new InitialContext();
			}
			datasource = (DataSource)context.lookup("HomeJNDI");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return datasource;
	}
	
}

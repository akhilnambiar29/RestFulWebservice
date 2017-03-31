package com.home.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.home.dao.OracleHome;

@Path("/v1/status")
public class V1Status {
	
	private static final String api_version = "1.0";

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle(){
		return "<p>Java Web Service</p>";
	}
	
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion(){
		return "<p>Version :</p>" + api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getDatabaseStatus(){
		
		PreparedStatement query = null;
		String myString = null;
		
		String returnString = null;
		Connection conn = null;
		
		try{
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME from sys.dual");
			
			ResultSet rs = query.executeQuery();
			
			while(rs.next()){
				myString = rs.getString("DATETIME");
			}
			
			query.close();
			
			returnString = "<p> Database status</p>" +"<p>Database Date/Time return: " +myString + "</p>";
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return returnString;
		
		
	}
	
}

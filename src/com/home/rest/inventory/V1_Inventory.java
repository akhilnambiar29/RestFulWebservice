package com.home.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.home.dao.OracleHome;
import com.home.utilites.ToJson;

@Path("/v1/inventory")
public class V1_Inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)  
	public Response returnAllPcParts(){
		
		
		//System.out.println("Here");
		
		PreparedStatement query = null;
		
		String returnString = null;
		Connection conn = null;
		
		Response rb = null;
		
		try{
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("select * from PC_PARTS");
			
			ResultSet rs = query.executeQuery();
			
			ToJson converter = new ToJson();
			JSONArray array = new JSONArray(); 
			
			array = converter.toJSONArray(rs);
			query.close();
			
			returnString = array.toString();
			
			rb = Response.ok(returnString).build();
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
		
		return rb;

	}
}

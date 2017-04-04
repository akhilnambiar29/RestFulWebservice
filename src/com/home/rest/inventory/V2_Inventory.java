package com.home.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.home.dao.OracleHome;
import com.home.utilites.ToJson;

@Path("/v2/inventory")
public class V2_Inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	
public Response returnBrandParts(@QueryParam("brand")String brand){
		
		
		System.out.println("Here brand:" + brand);
		
		PreparedStatement query = null;
		
		String returnString = null;
		Connection conn = null;
		
		//Response rb = null;
		
		try{
			
			if(brand == null){
				return Response.status(400).entity("Please specify a brand name").build();
			}
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("Select * from PC_PARTS where PC_PARTS_MAKER=?");
			
			query.setString(1, brand);
			
			ResultSet rs = query.executeQuery();
			
			System.out.println("Here as well");
			
			ToJson converter = new ToJson();
			JSONArray array = new JSONArray(); 
			
			array = converter.toJSONArray(rs);
			query.close();
			
			returnString = array.toString();
			
			//rb = Response.ok(returnString).build();
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
					return Response.status(500).entity("Server was unable to process the request").build();
				}
			}
		}
		
		return Response.ok(returnString).build();

}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/{brand}")
public Response returnBrandPartsPath(@PathParam("brand")String brand){
		
		
		System.out.println("Here brand:" + brand);
		
		PreparedStatement query = null;
		
		String returnString = null;
		Connection conn = null;
		
		//Response rb = null;
		
		try{
			
			if(brand == null){
				return Response.status(400).entity("Please specify a brand name").build();
			}
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("Select * from PC_PARTS where PC_PARTS_MAKER=?");
			
			query.setString(1, brand);
			
			ResultSet rs = query.executeQuery();
			
			System.out.println("Here as well");
			
			ToJson converter = new ToJson();
			JSONArray array = new JSONArray(); 
			
			array = converter.toJSONArray(rs);
			query.close();
			
			returnString = array.toString();
			
			//rb = Response.ok(returnString).build();
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
					return Response.status(500).entity("Server was unable to process the request").build();
				}
			}
		}
		
		return Response.ok(returnString).build();

}	
}

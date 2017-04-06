package com.home.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
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
	

	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	@Path("/{brand}/{item_number}")
public Response returnBrandPartsPathTwo(@PathParam("brand")String brand , @PathParam("item_number")String item_number){
		
		
		System.out.println("Here brand:" + brand + " " + item_number + "?");
		
		PreparedStatement query = null;
		
		String returnString = null;
		Connection conn = null;
		
		//Response rb = null;
		
		try{
			
			if(brand == null || item_number == null ){
				return Response.status(400).entity("Please specify a brand/item name").build();
			}
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("Select * from PC_PARTS where PC_PARTS_MAKER=? and PC_PARTS_TITLE=?");
			
			query.setString(1, brand);
			query.setString(2, item_number);
			
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/insert")
public Response InsertIntoValues(String formdata){
		
		System.out.println("Data"+ formdata);
		
	//	System.out.println("Here brand:" + brand + " " + item_number + "?");
		
		System.out.println("Here");
		
		PreparedStatement query = null;
		
		String returnString = null;
		Connection conn = null;
		
		//Response rb = null;
		
		try{
			ObjectMapper objmapper = new ObjectMapper();
			ItemEntry itemEntry = objmapper.readValue(formdata, ItemEntry.class);
			
			conn = OracleHome.DataSourceConn().getConnection();
			query = conn.prepareStatement("INSERT INTO PC_PARTS (PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL) VALUES (?,?,?,?,?)");
			
			//query.setString(1, brand);
			//query.setString(2, item_number);
			
			query.setString(1, itemEntry.PC_PARTS_PK);
			query.setString(2, itemEntry.PC_PARTS_TITLE);
			query.setString(3, itemEntry.PC_PARTS_CODE);
			query.setString(4, itemEntry.PC_PARTS_MAKER);
			query.setString(5, itemEntry.PC_PARTS_AVAIL);
			
			query.executeUpdate();
			
			/*System.out.println("Here as well");
			
			ToJson converter = new ToJson();
			JSONArray array = new JSONArray(); 
			
			array = converter.toJSONArray(rs);
			query.close();*/
			
			//returnString = array.toString();
			
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

class ItemEntry{
	
	public String PC_PARTS_PK;
	public String PC_PARTS_TITLE;
	public String PC_PARTS_CODE;
	public String PC_PARTS_MAKER;
	public String PC_PARTS_AVAIL;
}

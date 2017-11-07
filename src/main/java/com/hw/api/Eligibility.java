package com.hw.api;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/eligibility")
public class Eligibility {
 
	@GET
	@Path("/{program}")
	public Response getMsg(@PathParam("program") String program) {
		
		//String output = "Eligibility API says : " + msg;
		String output = "{\"program\" : \""+program+"\",\"success\" : \"true\"," +
						"\"eligibility\":[" +
						"{\"field\":\"Driver's License\", \"value\":\"true\"}" +
						//"{\"Field\":\"Driver's License\", \"Value\":\"True\"}" +
						"]}";
		return Response.status(200).entity(output).build();
 
	}
 
}
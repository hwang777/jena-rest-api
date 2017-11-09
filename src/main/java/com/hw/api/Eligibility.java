package com.hw.api;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.StreamingOutput;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
/*
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import static org.neo4j.graphdb.Direction.INCOMING;
import static org.neo4j.graphdb.Direction.OUTGOING;



import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
*/
import org.neo4j.helpers.collection.MapUtil;
import org.neo4j.driver.v1.*;

//import static org.neo4j.driver.v1.Values.parameters;

//import java.util.MapUtil;
@Path("/eligibility")
public class Eligibility {
 
    private ObjectMapper objectMapper = new ObjectMapper();
    //private GraphDatabaseService graphDb;
    String neo4JServer = "bolt://dqcloud6.southcentralus.cloudapp.azure.com:7687";
    String username = "neo4j";
    String passwd = "Qwerty123";
	@GET
	@Path("/{program}")
	public Response getMsg(@PathParam("program") String program) {

		StreamingOutput stream = new StreamingOutput()
        {
            @Override
            public void write( OutputStream os ) throws IOException, WebApplicationException
            {

        		System.out.println("Looking for " + program);
        		final Map<String, Object> params = MapUtil.map( "program", program );
        		System.out.println("Looking for " + program);
        		Driver driver = GraphDatabase.driver( neo4JServer, AuthTokens.basic( username, passwd) );
        		Session session = driver.session();
    			StatementResult result = session.run( queryString(), params);
                JsonGenerator jg = objectMapper.getJsonFactory().createJsonGenerator( os, JsonEncoding.UTF8 );
                jg.writeStartObject();
                jg.writeFieldName("Eligibility Requirements" );
                jg.writeStartArray();

                while ( result.hasNext() )
                {
                    Record row = result.next();
                    jg.writeString(row.get("name").asString());
                    System.out.println(row.get("name").asString());
                }
                jg.writeEndArray();
                jg.writeEndObject();
                jg.flush();
                jg.close();
                session.close();
                driver.close();
            }
        };
		return Response.ok().entity( stream ).type( MediaType.APPLICATION_JSON ).build();
	}
    private String queryString()
    {
    	return "MATCH (p:Program {name: $program })-[:REQUIRES]->(e:EligibilityRequirement) RETURN e.name as name";

    } 
}
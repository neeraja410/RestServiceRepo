package com.example;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users1")
public class UserResource {

    private static Map<Integer, String> users = new HashMap<>();

    static {
        users.put(1, "John Doe");
        users.put(2, "Jane Doe");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("totalEntries", users.size())
                .build();
        return Response.ok(jsonResponse.toString(), MediaType.APPLICATION_JSON).build();
        //return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") int id) {
        String user = users.get(id);
        //user.length();
        if (user == null) {
		 return Response.status(Response.Status.NOT_FOUND).build();
		}
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("user", user)
                .add("totalEntries", users.size())
                .build();
        return Response.ok(jsonResponse.toString(), MediaType.APPLICATION_JSON).build();
        //return Response.ok(user).build();
       //return users.values().size();
        
        
       
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(Map<String, String> user) {
        int id1 = users.size() + 1;
        users.put(id1, user.get("name"));
        int id2 = users.size() + 1;
        users.put(id2, user.get("name1"));
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") int id, Map<String, String> user) {
        if (!users.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        users.put(id, user.get("name"));
        return Response.ok(user).build();
    }

  /*  @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        if (!users.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        users.remove(id);
        return Response.noContent().build();
    }*/
    
    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser1(@PathParam("name") String name) {
        if (!users.containsValue(name)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        users.values().remove(name);
        return Response.noContent().build();
    }
}

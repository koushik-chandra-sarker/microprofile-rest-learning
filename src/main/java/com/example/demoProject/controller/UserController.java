package com.example.demoProject.controller;

import com.example.demoProject.pojo.User;
import com.example.demoProject.service.UserService;
import com.example.demoProject.service.UserServiceHb;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/user")
@Singleton
public class UserController {

    @Inject
    private UserServiceHb userService;

    @GET
    @Produces("application/json")
    @Path("/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        return Response.ok(userService.getUserByUsername(username)).build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    @Path("/add")
    public Response addUser(User user) {
        return Response.ok(userService.addUser(user)).build();
    }

    @GET
    @Produces("application/json")
    @Path("/all")
    public Response getAllUsers() {
        return Response.ok(userService.getAllUsers()).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("/delete/{username}")
    public Response deleteUser(@PathParam("username") String username) {
        return Response.ok(userService.deleteUser(username)?"User successfully deleted": "User not found.").build();
    }
}

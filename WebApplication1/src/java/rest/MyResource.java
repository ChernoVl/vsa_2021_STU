/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Singleton
@Path("moj-prvy")
public class MyResource {

    @Context
    private UriInfo context;
    
    private String msg = "nic";

    /**
     * Creates a new instance of MyResource
     */
    public MyResource() {
    }

    /**
     * Retrieves representation of an instance of rest.MyResource
     * @return an instance of java.lang.String
     */
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public String getHtml() {
//        return "<h1>adf</h1>";
//    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText() {
        return msg;
    }

    /**
     * PUT method for updating or creating an instance of MyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public void putText(String content) {
        msg = content;
    }
}

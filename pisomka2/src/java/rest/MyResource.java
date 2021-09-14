/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Singleton
@Path("menu")
public class MyResource {

    private Map<Den, List<Jedlo>> ponukaMap;
    private Ponuka ponuka;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyResource
     */
    public MyResource() {
        ponuka = new Ponuka();
        
        ponukaMap = new HashMap<>();
        Jedlo jedlo = new Jedlo(3.5,  "gulas");
        Jedlo jedlo2 = new Jedlo(3.3,  "palacinky");
        List<Jedlo> list = new ArrayList<>();
        list.add(jedlo);
        list.add(jedlo2);
        
        ponukaMap.put(new Den("pondelok"), list);
        
        ponuka.getPonuka().put(new Den("pondelok"), list);
    }

    /**
     * Retrieves representation of an instance of rest.MyResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Ponuka getXml() {
        return ponuka;
    }

    /**
     * PUT method for updating or creating an instance of MyResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}

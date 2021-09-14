/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author igor
 */
@Singleton
@Path("menu")
public class GenericResource {

//    private List<Jedlo> jedla;
    List<Ponuka> ponuka;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
//        jedla = new ArrayList<>();
//        jedla.add(new Jedlo("Vyprazany syr", 3.5));
        int k = 0;
        ponuka = new ArrayList<>();
        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo("pondelok Vyprazany syr", 3.5));
        ponuka.get(k++).setDen("pondelok");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo("utorok Vyprazany syr", 3.5));
        ponuka.get(k++).setDen("utorok");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo(" stredaVyprazany syr", 3.5));
        ponuka.get(k++).setDen("streda");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo("stvrtok Vyprazany syr", 3.5));
        ponuka.get(k++).setDen("stvrtok");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo(" piatokVyprazany syr", 3.5));
        ponuka.get(k++).setDen("piatok");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo("sobota Vyprazany syr", 3.5));
        ponuka.get(k++).setDen("sobota");

        ponuka.add(new Ponuka());
        ponuka.get(k).getJedla().add(new Jedlo("nedela Vyprazany syr", 3.5));
        ponuka.get(k++).setDen("nedela");
    }

    /**
     * Retrieves representation of an instance of rest.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Ponuka getMenu() {
        return ponuka.get(0);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(Jedlo content) {
        ponuka.get(0).getJedla().add(content);
        return "" + (ponuka.get(0).getJedla().size() - 1);
    }

    @GET
    @Path("{den}")
    @Produces(MediaType.APPLICATION_XML)
    public Ponuka getJedlo(@PathParam("den") String denstr) {
        int k = -1;
        switch (denstr) {
            case "pondelok":
                return ponuka.get(++k);
            case "utorok":
                return ponuka.get(++k);
            case "streda":
                return ponuka.get(++k);
            case "stvrtok":
                return ponuka.get(++k);
            case "piatok":
                return ponuka.get(++k);
            case "sobota":
                return ponuka.get(++k);
            case "nedela":
                return ponuka.get(++k);
            default:
                return null;
        }

    }
}

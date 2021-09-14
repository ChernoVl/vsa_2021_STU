package rest;

import java.io.File;
import javax.activation.MimetypesFileTypeMap;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path("generic-path")
public class MyResource {

    @Context
    private UriInfo context;

    public MyResource() {
    }

    @GET
    @Produces("text/html")
//    @Produces("text/plain")
    public String getText() {
        return "<h1>toto je root</h1>";
    }

    @GET
    @Path("ctx")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCtx(@Context ServletContext ctx) {
        return ctx.getAttribute("sprava").toString();
    }

    @PUT
    @Path("ctx")
    @Consumes(MediaType.TEXT_PLAIN)
    public void putCtx(String content, @Context ServletContext ctx) {
        ctx.setAttribute("sprava", content);
    }

    @GET
    @Path("/img")
    @Produces(MediaType.MULTIPART_FORM_DATA)
    public Response getDocument() {
        File f = new File("/home/vsa/NetBeansProjects/WebAppFromPrednaska8/web/images/tree.JPG");
        if (!f.exists()) {
            throw new WebApplicationException(404);
        }
        String mt = new MimetypesFileTypeMap().getContentType(f);
        System.out.println(mt); //vypisanie media-typu odosielaneho suboru

        return Response.ok(f, mt).build();
    }

    @GET
    @Path("child")
    @Produces(MediaType.TEXT_PLAIN)
    public String getChild(@Context ServletContext ctx) {
        return "child";
    }
    
    @GET
    @Path("meno/{name: [A-Z].*}")
    @Produces("text/plain")
    public String getName(@PathParam("name") String name) {
        //TODO return proper representation object
        return "Ahoj " + name;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Singleton
@Path("skuska")
public class SkuskaResource {

    @Context
    private UriInfo context;

    private List<Skuska> skusky;

    /**
     * Creates a new instance of Skuska
     */
    public SkuskaResource() {
        Skuska s = new Skuska();
        s.setDen("17-05-2021");
        s.setPredmet("OOP");
        s.setStudent(new ArrayList<>());

        skusky = new ArrayList<>();
        skusky.add(s);

//        Skuska s2 = new Skuska();
//        s2.setDen("18-05-2021");
//        s2.setPredmet("VSA");
//        s2.setStudent(new ArrayList<>());
//        skusky.add(s2);
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_XML)
    public void postXml(Skuska content) {
        for (Skuska skuska : skusky) {
            if (skuska.getPredmet().equals(content.getPredmet())) {
                   return;
            }
        }
        skusky.add(content);
    }

    @GET
    @Path("/{predmet}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getPocetStudentovNaSkuske(@PathParam("predmet") String predmet) {
        for (Skuska skuska : skusky) {
            if (skuska.getPredmet().equals(predmet)) {
                if (skuska.getStudent() == null) {
                    return "0";
                }
                return skuska.getStudent().size() + "";
            }
        }
        return null;
    }

    @GET
    @Path("/{predmet}")
    @Produces(MediaType.APPLICATION_XML)
    public Skuska getXmlForPredmet(@PathParam("predmet") String predmet) {
        for (Skuska skuska : skusky) {
            if (skuska.getPredmet().equals(predmet)) {
                return skuska;
            }
        }
        return null;
    }

    @POST
    @Path("/{predmet}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String postPrihlasenieStudentaNaSkusku(@PathParam("predmet") String predmet, String content) {
        for (Skuska skuska : skusky) {
            if (skuska.getPredmet().equals(predmet)) {
                if (skuska.getStudent() != null) {
                    for (String name : skuska.getStudent()) {
                        if (name.equals(content)) {
                            return skuska.getDen();
                        }
                    }
                    
                    skuska.getStudent().add(content);
                    return skuska.getDen();
                }
                
                skuska.setStudent(new ArrayList<>());
                skuska.getStudent().add(content);
                return skuska.getDen();
            }
        }
        return "neznamy predmet";
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(@QueryParam("student") String student) {
        if (student == null || student.equals("")) {
            return "";
        }
        
        StringBuilder resp = new StringBuilder();
        for (Skuska skuska : skusky) {
            if (skuska.getStudent() == null || skuska.getStudent().size() == 0) {
                continue;
            }
            for (String s : skuska.getStudent()) {
                if (s.equals(student)) {
                    resp.append(skuska.getPredmet() + " ");
                    break;
                }
            }
        }
        String r = resp.toString().trim();
        return r;
    }
}

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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author vsa
 */
@Path("menu")
@Singleton
public class GenericResource {

    @Context
    private UriInfo context;
    private List<Ponuka> ponuka;
    private static final String days = "pondelok\nutorok\nstreda\nstvrtok\npiatok\nsobota\nnedela";
    //private Map<String, List<Jedlo>> ponuka;

    public GenericResource() {
        ponuka = new ArrayList<>();
        int index = 0;
        for(String day: days.split("\n")){
            ponuka.add(new Ponuka(day));
        }
        List<Jedlo> list1 = new ArrayList<>();
//        list1.add(new Jedlo("Vyprazany syr", 4.0));
//        list1.add(new Jedlo("Gulas", 4.5));
        list1.add(new Jedlo("gulas", 3.5));
//        List<Jedlo> list2 = new ArrayList<>();
//        list2.add(new Jedlo("Vyprazany syr", 1.0));
//        List<Jedlo> list3 = new ArrayList<>();
//        list3.add(new Jedlo("Vyprazany syr", 11.0));
//        list3.add(new Jedlo("Gulas", 0.5));

        
//        ponuka.get(index++).setJedla(list3); //0 Vyprazany syr, Gulas
        ponuka.get(index++).setJedlo(list1); //1 Vyprazany syr, Gulas, gulas
//        ponuka.get(index++).setJedla(list2); //2 Vyprazany syr
//        ponuka.get(index++).setJedla(list1); //3 Vyprazany syr, Gulas, gulas
//        ponuka.get(index++).setJedla(list3); //4 Vyprazany syr, Gulas
//        ponuka.get(index++).setJedla(list2); //5 Vyprazany syr
//        ponuka.get(index++).setJedla(list1); //6 Vyprazany syr, Gulas, gulas

//         ponuka = new HashMap<>();
//         Jedlo j = new Jedlo("Vyprazany syr", 3.5);
//         List<Jedlo> js = new ArrayList<>();
//         js.add(j);
//         ponuka.put("pon", js);
    }

    private String findDays(String jedlo){
        Jedlo findJedlo = new Jedlo(jedlo, 0);
        StringBuilder sb = new StringBuilder();
        for(String day: days.split("\n")){
            List<Jedlo> list = getDayList(day);            
            if(list.contains(findJedlo)){
                sb.append(day).append(" ");
            }
        }
        if(sb.length() == 0){
            return "NEMAME";
        }
        return sb.toString().trim();
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String findJedloInMenuForEachDay(@QueryParam("nazov") String jedlo) {
        return findDays(jedlo);
    }

    private List<Jedlo> getDayList(String den) {
        switch (den) {
            case "pondelok":
                return ponuka.get(0).getJedlo();
            case "utorok":
                return ponuka.get(1).getJedlo();
            case "streda":
                return ponuka.get(2).getJedlo();
            case "stvrtok":
                return ponuka.get(3).getJedlo();
            case "piatok":
                return ponuka.get(4).getJedlo();
            case "sobota":
                return ponuka.get(5).getJedlo();
            case "nedela":
                return ponuka.get(6).getJedlo();
        }
        return null;
    }

    @GET
    @Path("{den}")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getPocetJedla(@PathParam("den") String den) {
        List<Jedlo> menu = getDayList(den);
        if (menu == null) {
            return 0;
        }
        return menu.size();
    }

    @GET
    @Path("{den}")
    @Produces(MediaType.APPLICATION_XML)
    public Ponuka getOneDaysMenu(@PathParam("den") String den) {
        Ponuka p = new Ponuka(den);
        p.setJedlo(getDayList(den));
        return p;
    }

    @POST
    @Path("{den}")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.TEXT_PLAIN)
    public String pridajJedlo(@PathParam("den") String den, Jedlo content) {
        List<Jedlo> menu = getDayList(den);
        if (menu == null || menu.contains(content)) {
            return "" + 0;
        }
        menu.add(content);
        return "" + menu.size();
    }
    
    @GET
    @Path("{den}/{n}")
    @Produces(MediaType.APPLICATION_XML)
    public Jedlo getOneDaysOneJedlo(@PathParam("den") String den, @PathParam("n") int n) {
        List<Jedlo> menu = getDayList(den);
        if (menu == null || (n < 1 || n > menu.size())) {
            return null;
        }
        return menu.get(n - 1);
    }
    
    @DELETE
    @Path("{den}/{n}")
    public void odstranJedlo(@PathParam("den") String den, @PathParam("n") int n) {
        List<Jedlo> menu = getDayList(den);
        if (menu == null || (n < 1 || n > menu.size())) {
            return;
        }
        menu.remove(n - 1);
    }
}

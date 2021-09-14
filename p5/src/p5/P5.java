/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p5;

import entities.Kniha;
import entities.Vydavatelstvo;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //main2();
        main1();
    }
    
    public static void main2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p5PU");
        EntityManager em = emf.createEntityManager();
        Vydavatelstvo v = em.find(Vydavatelstvo.class, 1L);
        for (Kniha k : v.getPublikacie()) {
            System.out.println(k);
        }
    }

    public static void main1() {
        Kniha k = new Kniha("Jazyk C");
        Vydavatelstvo v = new Vydavatelstvo();
        v.setNazov("Alfa");
        v.setPublikacie(new ArrayList<>());
        k.setVydavatel(v);
        v.getPublikacie().add(k);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p5PU");
        EntityManager em = emf.createEntityManager();
        em.persist(v);
        em.persist(k);
        em.getTransaction().begin();
        try {
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p5_manytomany;

import entities.Kniha;
import entities.Osoba;
import entities.Vydavatelstvo;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P5_ManyToMany {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //main2();
        main1();
    }
    
    public static void main2() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p5_ManyToManyPU");
        EntityManager em = emf.createEntityManager();
        Vydavatelstvo v = em.find(Vydavatelstvo.class, 1L);
        for (Kniha k : v.getPublikacie()) {
            System.out.println(k);
        }
    }

    public static void main1() {
        Kniha k = new Kniha("Jazyk C");
        Kniha k2 = new Kniha("Winetou");
        Vydavatelstvo v = new Vydavatelstvo();
        v.setNazov("Alfa");
        v.setPublikacie(new ArrayList<>());
        k.setVydavatel(v);
        //v.getPublikacie().add(k);

        Osoba o1 = new Osoba("Karnighen");
        Osoba o2 = new Osoba("Ritchie");
        k.setAutory(new ArrayList<>());
        k.getAutory().add(o1);
        k.getAutory().add(o2);
//        o1.getPublikacie().add(k2);
//        o2.getPublikacie().add(k2);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p5_ManyToManyPU");
        EntityManager em = emf.createEntityManager();
        em.persist(v);
        em.persist(k);
        em.persist(k2);
        em.persist(o2 );
        em.persist(o1);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4_onetomany;

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
public class P4_OneToMany {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kniha k = new Kniha("Jazyk C");
        Vydavatelstvo v = new Vydavatelstvo();
        v.setNazov("Alfa");
        v.setPublikacie(new ArrayList<>());
        v.getPublikacie().add(k);     
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p4_OneToManyPU");
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

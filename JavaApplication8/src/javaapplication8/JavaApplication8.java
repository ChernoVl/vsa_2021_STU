/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication8;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author vsa
 */
public class JavaApplication8 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaApplication8PU");
        EntityManager em = emf.createEntityManager();
        
         
//        TypedQuery tq = em.createNamedQuery("Osoba.findById", Osoba.class);
//        tq.setParameter("id", 51);
//        List<Osoba> l = tq.getResultList();
//        for(Osoba o: l){
//            System.out.println(o);
//        }
        
        em.getTransaction().begin();
        Osoba osoba = new Osoba();
        osoba.setId(1L);
        osoba.setMeno("Fero");
        
        Osoba osoba2 = em.merge(osoba);
        System.out.println(osoba);
        System.out.println(osoba2);
        em.getTransaction().commit();
    }

    
}

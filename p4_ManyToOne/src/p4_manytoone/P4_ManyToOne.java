/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4_manytoone;

import etities.Kniha;
import etities.Vydavatelstvo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P4_ManyToOne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Kniha k = new Kniha("Jazyk C");
        Vydavatelstvo v = new Vydavatelstvo();
        v.setNazov("Alga");
        k.setVydavatel(v);
        persist(v);
        persist(k);
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p4_ManyToOnePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}

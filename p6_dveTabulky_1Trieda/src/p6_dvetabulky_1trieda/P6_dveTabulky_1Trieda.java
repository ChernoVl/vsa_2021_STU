/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6_dvetabulky_1trieda;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P6_dveTabulky_1Trieda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        create();
    }
    
    public static void create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p6_dveTabulky_1TriedaPU");
        EntityManager em = emf.createEntityManager();
        
        Person p = new Person();
        p.setId(333L);
        p.setPsc("12345");
        p.setName("Jozo");
        em.getTransaction().begin();
        try {
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}

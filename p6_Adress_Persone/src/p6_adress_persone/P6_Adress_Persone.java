/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6_adress_persone;

import entities.Address;
import entities.Person;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P6_Adress_Persone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("p6_Adress_PersonePU");
        EntityManager em = emf.createEntityManager();
        Address add = new Address();
        add.setId(333L);
        Person p = new Person();
        p.setId(333L);
        p.setBydlisko(add);
        
        em.getTransaction().begin();
        try {
            
            em.persist(p);
            em.persist(add);
            
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p6_item;

import entities.Item;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P6_Item {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        create();
    }
    
    public static void create() {
        Item i0 = new Item();
        Item i1 = new Item();
        i1.setNext(i0);
        Item i2 = new Item();
        i2.setNext(i1);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p6_ItemPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(i0);
            em.persist(i1);
            em.persist(i2);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }  
    
    
}

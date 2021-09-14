/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import entities.Book;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class Projekt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws ClassNotFoundException, SQLException{
        updateBook("sdfa","asdf",2.0);
    }
    
    public static boolean updateBook(String isbn, String title, Double price)  throws ClassNotFoundException, SQLException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
        EntityManager em = emf.createEntityManager();
        
        Query q = em.createNativeQuery("select * from BOOK", Book.class);
        List<Book> books = q.getResultList();
       
        em.getTransaction().begin();
        Book book = new Book(isbn, title, price); 
        
        for(Book b: books){
            System.out.println(b.toString());
        }
        
        em.persist(book);
        em.getTransaction().commit();
        
        return false;
    }
    
    public void updatePriceList(Map<String, Double> priceList){
        
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("projektPU");
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

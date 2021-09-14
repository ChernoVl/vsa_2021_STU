/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p7_dedicnost;

import entities.GuiElement;
import entities.RectElement;
import entities.TextElement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author vsa
 */
public class P7_dedicnost {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p7_dedicnostPU");
        EntityManager em = emf.createEntityManager();

        create(em);

//        sqlAllElementNames(em);
//        sqlAllElements(em);
//        sqlTextElements(em);
//        
        jpqlAllElements(em);
        jpqlTextElements(em);
        find(em);

        em.close();
        emf.close();

    }

    public static void create(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

//        GuiElement ge = new GuiElement();
//        ge.setName("A gui element");

        TextElement te = new TextElement();
        te.setName("A text element");
        te.setText("hello");
        te.setFontFamily("Times New Roman");
        te.setFontSize(16);

        RectElement re = new RectElement();
        re.setName("A rectangle element");
        re.setHight(100);
        re.setWidth(200);

//        em.persist(ge);
        em.persist(re);
        em.persist(te);

        tx.commit();
    }

    public static void sqlAllElementNames(EntityManager em) {
        System.out.println("\n-- All element names - Native query ---------------------");

//        String sql = "select name from GUIELEMENT";                             // SIGN_TABLE JOINED
        String sql = "select name from GUIELEMENT " // TABLE_PER_CLASS
                        + "UNION (select name from TEXTELEMENT)"
                        + "UNION (select name from RECTELEMENT)";

        Query q0 = em.createNativeQuery(sql);
        List<String> ln = q0.getResultList();
        ln.forEach(System.out::println);
    }

    public static void sqlAllElements(EntityManager em) {
        System.out.println("\n-- Text elements - Native query ---------------------");

        // pre SINGLE_TABLE vytvori instanciu podtriedy podla dtype
        // pre JOINED  a TABLE_PER_CLASS je spravanie mozno trochu necakavane/zvlastne
        Query q1 = em.createNativeQuery("select * from GUIELEMENT", GuiElement.class); //skuste tiez TextElement.class
        List<GuiElement> l1 = q1.getResultList();
        l1.forEach(System.out::println);
    }

    public static void sqlTextElements(EntityManager em) {
        System.out.println("\n-- Text elements - Native query ---------------------");

//        String sql = "select * from GUIELEMENT e where e.dtype='TextElement'";  // SIGN_TABLE
//        String sql = "select * from GUIELEMENT e, TEXTELEMENT t "                           
//                        + "where e.dtype='TextElement' and e.id=t.id";        // JOINED
        String sql = "select * from TEXTELEMENT";                              // TABLE_PER_CLASS

        Query query = em.createNativeQuery(sql, TextElement.class);
        List<GuiElement> l1 = query.getResultList();
        l1.forEach(System.out::println);
    }

    public static void jpqlAllElements(EntityManager em) {
        System.out.println("\n-- All Gui elements JPQL ---------------------------");

        // GuiElement - meno triedy nie tabulky
        Query q3 = em.createQuery("select e from GuiElement e"); // JPQL
        List<GuiElement> textelementlist = q3.getResultList();
        textelementlist.forEach(System.out::println);
    }

    public static void jpqlTextElements(EntityManager em) {
        System.out.println("\n-- Text elements JPQL ---------------------------");

        Query q3 = em.createQuery("select e from TextElement e"); // JPQL
        List<TextElement> textelementlist = q3.getResultList();
        textelementlist.forEach(System.out::println);
    }
    
    public static void find(EntityManager em) {
        System.out.println("\n-- find ---------------------------");

        GuiElement e = em.find(TextElement.class, 3L);
        
        System.out.println(e);
    }
}

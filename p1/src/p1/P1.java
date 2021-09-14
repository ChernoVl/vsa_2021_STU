/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class P1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p1PU");
        EntityManager em = emf.createEntityManager();
        Kniha k = new Kniha();
        em.getTransaction().begin();
        k.setNazov("Pipi dlha pancucha");
        k.setCena(3.5);
        k.setDostupnost(Kniha.Dostupnost.DO3DNI);
        //Totou metodou poviem entity manageru ze chcem objekt k bol managovany
        //A ked komitnem tranzakciu on zisti ze on v DB ne je tak ho tam vytvory
        em.persist(k);
        em.getTransaction().commit();
        
    }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        //EntityManager - komponenta ktora nam citanie a zapisovanie dat z databazy do objektov
//        //p1PU - indetntefikator ktory je v .xml (name="p1PU")
//        //persistence-unit z .xml - definuje pripojene k nejakej databaze
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("p1PU");
//        EntityManager em = emf.createEntityManager();
//        
//        //createNativeQuery - dotaz ktory umoznuje vyslat SQL dotaz
//        //(SQL prikaz,sem dame triedu kam chceme ukladat data)
//        Query q = em.createNativeQuery("select * from KNIHA", Kniha.class);
//        List<Kniha> knihy = q.getResultList();
//        
//        //Zabespecime synchronizaciu a pamaty a databazy
//        //em.getTransaction().begin();
//        
//        for(Kniha k: knihy){
//            System.out.println(k);
//            k.setCena(0.5*k.getCena());
//            System.out.println(k);
//        }
//        
//        //em.getTransaction().begin();
//        //Tranzakciu skoncime komitom
//        //em.getTransaction().commit();
//        
//        //na miesto toho aby robil komit tak refresnem cenu
//        //refres obnovy stav z databazy
////        em.refresh(knihy.get(0));
////        System.out.println(knihy.get(0));
//
//        //zabny na ojekt ktory je megenovany v DB
//        //a find uz vitahne data ne z pamate a zase z DB
//        em.clear();
//
//        //Ked chcem nacitat knihu z DB na zaklade primarneho kluca
//        Kniha k = em.find(Kniha.class, "Hobit");
//        System.out.println(k);
//        
//    }
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//        //Class loader natiahne drajver jdbc.ClientDriver        
//        Class.forName("org.apache.derby.jdbc.ClientDriver");
//        //Robim konekciu na databazu
//        //ktora databaza: jdbc:derby://localhost:1527/sample, meno, heslo
//        Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/sample","app","app");
//        //Vytvaram statement
//        Statement st = con.createStatement();
//        //A vytvoreny statement umozny vykonat dotaz
//        //A tymto executom ziskame resultSet, pocou ktoreho mezeme iterovat zaznamy ktoty vratil SELECT
//        ResultSet rs = st.executeQuery("SELECT * FROM kniha");
//        //Nacitame zaznam z rs
//        
//        List<Kniha> knihy = new ArrayList<>();
//        while(rs.next()){
//            Kniha k = new Kniha();
//            k.setNazov(rs.getString(1));
//            k.setCena(rs.getDouble("CENA"));
//            knihy.add(k);
//            System.out.println("" + k);
//        }
//        
//    }
}

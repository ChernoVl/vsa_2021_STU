/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt;

import entities.Book;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author vsa
 */
public class Projekt {

    EntityManagerFactory emf = null;

    public Projekt() {
        emf = Persistence.createEntityManagerFactory("projektPU");
    }

    /**
     * Metóda vyhľadá v databáze záznam o knihe podľa zadaného isbn (klúč) a
     * aktualizuje údaje
     *
     * Ak kniha v databáze neexistuje, vytvorí novú knihu so zadanými údajmi a
     * vráti true.
     *
     * Ak v databáze už existuje, aktualizuje údaje o knihe takto: ak argument
     * metódy name je zadaný (nenulový) ale v stĺpci NAME je NULL, zapíše
     * hodnotu do stĺpca. ak argument metódy name je zadaný ale stĺpec NAME
     * obsahuje inú hodnotu, vráti false (a nerobí žiadne zmeny v databáze). ak
     * je argument metódy price zadaný, aktualizuje hodnotu v stlpci PRICE. Po
     * úspešnom zápise zmien do databázy vráti metóda true.
     *
     * @param isbn
     * @param name
     * @param price
     * @return
     */
    public boolean bookUpdate(String isbn, String name, Double price) {
        if (isbn == null) {
            return false;
        }
        Book bookDB = findBook(isbn);
        Book book = new Book(isbn, name, price);
        if (bookDB == null) {
            return create(book);
        } else {
            String bookNameDB = bookDB.getName();
            Double bookPriceDB = bookDB.getPrice();
            if (name != null) {
                if (bookNameDB == null) {
                    return edit(book);
                } else if (!name.equals(bookNameDB)) {
                    return false;
                }
                return edit(book);
            }
        }
        return false;
    }

    /**
     *
     * Pre každú položku z cenníka vyhľadá podľa kľúča záznam v databáze a
     * aktualizuje cenu.
     *
     * Ak kniha s daným isbn v databáze ešte neexistuje, vytvorí ju (názov knihy
     * ostane prázdny).
     *
     * Návod: Na aktualizáciu použite metódu bookUpdate, pričom názov knihy
     * nezadáte.
     *
     * @param priceList je mapa obsahujúca cenník kníh: kľúč je isbn knihy a
     * hodnota jej cena.
     */
    public void priceListUpdate(Map<String, Double> priceList) {
        if (priceList == null) {
            return;
        }
        for (Map.Entry<String, Double> entry : priceList.entrySet()) {
            String isbn = entry.getKey();
            Double price = entry.getValue();
            Book book = new Book(isbn, null, price);
            Book bookDB = findBook(isbn);
            if (bookDB != null) {
                book.setName(bookDB.getName());
                edit(book);
            } else {
                create(book);
            }
        }
    }
    
    public boolean create(Book book) {
        boolean passed = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            passed = true;
        } catch (Exception ex) {
            if (findBook(book.getIsbn()) != null) {
                System.out.println("Book " + book + " already exists.");
            }
            System.out.println(":D Exception from create");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return passed;
    }
    
    public boolean edit(Book book) {
        boolean passed = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            book = em.merge(book);
            em.getTransaction().commit();
            passed = true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = book.getIsbn();
                if (findBook(id) == null) {
                    System.out.println("The book with id " + id + " no longer exists.");
                }
            }
            System.out.println(":D Exception from edit");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return passed;
    }

    public Book findBook(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}

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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static void main(String[] args) {
//        boolean b = updateBook("isbn10", "hoho", null);
//        System.out.println("bool=" + b);
    }

    /**
     * Metóda vyhľadá v databáze záznam o knihe podľa zadaného isbn (klúč) a
     * aktualizuje údaje
     *
     * Ak kniha v databáze neexistuje, vytvorí novú knihu so zadanými údajmi a
     * vráti true.
     *
     * Ak v databáze už existuje, aktualizuje údaje o knihe takto: *ak argument
     * metódy name je zadaný (nenulový) ale v stĺpci NAME je NULL, zapíše
     * hodnotu do stĺpca. *ak argument metódy name je zadaný ale stĺpec NAME
     * obsahuje inú hodnotu, vráti false (a nerobí žiadne zmeny v databáze). *ak
     * je argument metódy price zadaný, aktualizuje hodnotu v stlpci PRICE. Po
     * úspešnom zápise zmien do databázy vráti metóda true.
     *
     * @param isbn
     * @param name
     * @param price
     * @return
     */
    public static boolean updateBook(String isbn, String name, Double price) {
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
            if (name != null && bookNameDB == null) {
                return edit(book);
            } else if (name != null && bookNameDB != null) {
                return false;
            }
        }
        return edit(book);
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
    public void updatePriceList(Map<String, Double> priceList) {
        if (priceList == null) {
            return;
        }
        for (Map.Entry<String, Double> entry : priceList.entrySet()) {
            String isbn = entry.getKey();
            Double price = entry.getValue();
            Book book = new Book(isbn, null, price);
            Book bookDB = findBook(isbn);
            if(bookDB != null){
                edit(book);
            }
            else{
                create(book);
            }
        }
    }

    private static boolean create(Book book) {
        try {
            new Book().create(book);
            return true;
        } catch (Exception e) {
            System.out.println(":D Exception from create");
        }
        return false;
    }

    private static Book findBook(String id) {
        return new Book().findBook(id);
    }

    private static boolean edit(Book book) {
        try {
            new Book().edit(book);
            return true;
        } catch (Exception ex) {
            System.out.println(":D Exception from edit");
        }
        return false;
    }
}

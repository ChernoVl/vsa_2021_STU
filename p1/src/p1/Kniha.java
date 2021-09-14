/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author vsa
 * 
 * minimalna enitna trieda musi mat dve anotacie
 */
//Ma byt toto entitna trieda. Cize musime povedat 
//ze toto je trieda ktora mapovana na databazovu tabulku
//a to povieme pomocou anotacie @Entity
//a treba pridat kniznicu na pracu z objektno relacnim mapovanim
//Toto entity nam hovori ze nekde musi existovat tabulka Kniha
@Entity
public class Kniha {
    enum Dostupnost{NASKLADE,DO3DNI, VYPREDANE}
    //musime este povedat ktory stlpec bude primarne kluc
    @Id
    private String nazov;
    @Column(nullable=false)
    private Double cena;
    //Ked sme tam ne chcely mam nejake datove cleny, ktore
    //ne chceme zapisovat do DB (cleny ktore pouzivame kvoli nejakim vipoctom)
    //to mozme oznacit anotaciou
    @Transient
    private int pocet;
    
    //enumeracny typ
    @Enumerated(javax.persistence.EnumType.STRING)
    private Dostupnost dostupnost;

    public Dostupnost getDostupnost() {
        return dostupnost;
    }

    public void setDostupnost(Dostupnost dostupnost) {
        this.dostupnost = dostupnost;
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public int getPocet() {
        return pocet;
    }

    public void setPocet(int pocet) {
        this.pocet = pocet;
    }
    
    @Override
    public String toString() {
        return "Kniha{" + "nazov=" + nazov + ", cena=" + cena + '}';
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vsa
 */
@XmlRootElement
public class Ponuka {
    private String den;
    private List<Jedlo> jedlo;
    
    public Ponuka() {
        jedlo = new ArrayList<>();
    }
    
    public Ponuka(String den) {
        jedlo = new ArrayList<>();
        this.den = den;
    }

    public List<Jedlo> getJedlo() {
        return jedlo;
    }

    public void setJedlo(List<Jedlo> jedla) {
        this.jedlo = jedla;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }
    
}

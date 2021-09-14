/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eperimentyclient2;

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
    private List<Jedlo> jedla;
    
    public Ponuka() {
        jedla = new ArrayList<>();
    }
    
    public Ponuka(String den) {
        jedla = new ArrayList<>();
        this.den = den;
    }

    public List<Jedlo> getJedla() {
        return jedla;
    }

    public void setJedla(List<Jedlo> jedla) {
        this.jedla = jedla;
    }

    public String getDen() {
        return den;
    }

    public void setDen(String den) {
        this.den = den;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("den=" + den + "\n");
        jedla.forEach(el -> sb.append(el + "\n"));
        return sb.toString();
    }
    
    
}

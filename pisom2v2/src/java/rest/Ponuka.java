/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

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
    private Map<Den, List<Jedlo>> ponuka;

    public Ponuka() {
        ponuka = new HashMap<>();
    }

    
    
    public Map<Den, List<Jedlo>> getPonuka() {
        return ponuka;
    }

    public void setPonuka(Map<Den, List<Jedlo>> ponuka) {
        this.ponuka = ponuka;
    }
    
    
}

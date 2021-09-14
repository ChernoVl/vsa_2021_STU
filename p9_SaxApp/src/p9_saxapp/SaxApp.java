/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9_saxapp;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author vsa
 */
public class SaxApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);    // dolezite aby extrahoval localName

        SAXParser saxParser = spf.newSAXParser();
        
        saxParser.parse("resources/adresar.xml", new MyHandler());
//        saxParser.parse("/home/vsa/NSetBeansProjects/p9_SaxApp/resources/adresar2.xml", new Handler2());
    }
    
}

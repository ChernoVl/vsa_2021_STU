/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9_saxapp;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author vsa
 */
public class Handler2 extends DefaultHandler {

    String text;
    String localName;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("\t#characters");

        text = new String(ch, start, length);
        System.out.println("\t<" + localName + ">" + text);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Handler2#endElement </" + localName + ">");

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Handler2#startElement <" + localName + ">");

        System.out.println("\turi=" + uri);
        System.out.println("\tlocalName=" + (this.localName = localName));
        System.out.println("\tqName=" + qName);
//        System.out.println("\tgen=" + attributes.getValue(0));
        System.out.println("\tgen=" + attributes.getValue("gen"));
    }

}

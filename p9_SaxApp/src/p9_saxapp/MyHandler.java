/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p9_saxapp;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author vsa
 */
public class MyHandler extends DefaultHandler {

    String meno = null;
    String bydl = null;
    String text = null;

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("meno")) {
            meno = text;
        }

        if (localName.equals("bydlisko")) {
            bydl = text;
        }

        if (localName.equals("osoba")) {
            if ("Praha".equals(bydl)) {
                System.out.println(meno);
            }

            bydl = null;
            meno = null;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch, start, length);
    }
}

package pl.lublin.wsei.java.cwiczenia;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Infografika {

    int szerokosc = 0;
    int wysokosc = 0;
    String tutul = "";
    String adresGrafiki = "";
    String adresStrony = "";

    String adresMiniaturki = "";


    public Infografika(String text) {
        Pattern pat = Pattern.compile("<title><!\\[CDATA\\[(.*)\\]\\]");
        Matcher m = pat.matcher(text);
        if(m.find())
        tutul = m.group(1);
         else
            tutul = "";

        pat = Pattern.compile("<link>(.*)</link>");
        m = pat.matcher(text);
        if(m.find())
            adresStrony = m.group(1);
         else
            adresStrony = "";



        pat = Pattern.compile("url=(.*) type");
        m = pat.matcher(text);
        if(m.find())
            adresGrafiki = m.group(1);
         else
            adresGrafiki = "";

        pat = Pattern.compile("<img src=(.*) alt");
        m = pat.matcher(text);
        if(m.find())
            adresMiniaturki = m.group(1);
        else
            adresMiniaturki = "";

        pat = Pattern.compile("width=\"(.*)\" height");
        m = pat.matcher(text);
        if(m.find())
            szerokosc = Integer.parseInt(m.group(1));
        else
            szerokosc = 0;

        pat = Pattern.compile("height=\"(.*)\"");
        m = pat.matcher(text);
        if(m.find())
            wysokosc = Integer.parseInt(m.group(1));
        else
            wysokosc = 0;


    }

  public void print() {
        System.out.println("Infografika:"  + "\n" +
                "tytul: " + tutul + "\n" +
                "adres strony: " + adresStrony + "\n" +
                "adres grafiki: " + adresGrafiki + "\n" +
                "adres miniaturki" + adresMiniaturki + "\n" +
                "rozmiarył: " + wysokosc + "x" + szerokosc);
    }
}

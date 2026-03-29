//Autors: Josep Gabriel Fornés Reynés i Antoni Cruz Carrió
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author PepBiel
 */
public class LT {

    public char[] llegirLiniaA() {
        String res = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
            res = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toCharArray();
    }

    public Integer llegirSencer() {
        Integer res = 0;
        try {
            char[] pal = llegirLiniaA();
            res = Integer.parseInt(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public Double llegirReal() {
        Double res = 0.0;
        try {
            char[] pal = llegirLiniaA();
            res = Double.parseDouble(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public String llegirLinia() {
        String res;
            char[] pal = llegirLiniaA();
            res = new String(pal);
        return res;
    }

    public Character llegirCaracter() {
        Character res;
            char[] pal = llegirLiniaA();
            String s = new String(pal);
            if (s.length()==0) {
                res = null;
            } else {
                res = new Character(s.charAt(0));
            }
        return res;
    }

    

    
}

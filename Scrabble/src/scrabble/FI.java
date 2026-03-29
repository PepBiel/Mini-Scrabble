//Autors: Josep Gabriel Fornés Reynés i Antoni Cruz Carrió
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PepBiel
 */
public class FI {
    
    private Paraula nom;
    private FileReader fr;
    private BufferedReader br;

    public FI(Paraula n) {     //Contructor que li passen el nom de l'arxiu
        nom = n;
    }

    public void obrir() {   //Obri l'arxiu
        try {
            fr = new FileReader(nom.toString());
            br = new BufferedReader(fr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public char[] llegirLinia() {  //Llegeix les linies de l'arxiu i la retorna
        char[] res = null;
        try {
            String s = br.readLine();
            if (s != null) {
                res = s.toCharArray();
            }
        } catch (IOException ex) {
            Logger.getLogger(FI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public char llegirCaracter() throws IOException{     //Llegeix caracter a caracter l'arxiu. Aquest ens servirà per llegir l'arxiu de les fitxes
        char res;
        res = (char)br.read();
        return res;
    }

    public void tancar() {           //Tanca l'arxiu
        try {
            br.close();
            fr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
}

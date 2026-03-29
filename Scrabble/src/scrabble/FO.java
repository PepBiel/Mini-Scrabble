//Autors: Josep Gabriel Fornés Reynés i Antoni Cruz Carrió
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author PepBiel
 */
public class FO {

    private Paraula nom;
    private FileWriter fw = null;
    private BufferedWriter bw = null;

    public FO(Paraula s) {         //constructor que reb el nom de l'arxiu
        nom = s;
    }

    public void obrir() {
        try {
            fw = new FileWriter(nom.toString(), true);  //Introdueix el nom de l'arxiu, i el true és perque no esborri el que ja hi havia escrit anteriorment
            bw = new BufferedWriter(fw);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void gravar(Paraula n, int t, int pu) throws IOException {  //reb el nom, els torns i els punts i ho escriu al fitxer de sortida
        Date fecha = new Date();
        bw.write("Nom: ");
        bw.write(n.getPar(n));
        bw.write("  ");
        bw.write("torns: " + t + "  ");
        bw.write("puntuació:  " + pu + "  ");
        bw.write("data: " + fecha + "  ");
        bw.newLine();
    }

    public void tancar() {       //Tanca el fitxer
        try {
            bw.close();
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}

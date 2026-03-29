//Autors: Josep Gabriel Fornés Reynés i Antoni Cruz Carrió
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

/**
 *
 * @author PepBiel
 */
public class Paraula {

    private final int llarginicial = 20;
    private char[] pal;
    private int ind;

    public Paraula() {
        pal = new char[llarginicial];
        ind = 0;
    }

    public Paraula(char[] p) {
        pal = new char[llarginicial];
        ind = 0;
        for (int i = 0; i < p.length; i++) {
            posar(p[i]);
        }
    }

    public void posar(char c) {
        if (ind == pal.length) {
            char[] aux = new char[pal.length + 5];
            for (int i = 0; i < pal.length; i++) {
                aux[i] = pal[i];
            }
            pal = aux;
        }
        pal[ind++] = c;
    }

    public boolean buida() {
        return (ind == 0);
    }

    public boolean compararLletres(Paraula l, Paraula p) {             //Aquest mètode reb els paràmetres: l = fitxes i p = paraula introduida
        boolean comp = true;                                           //Aquest mira si les lletres i la paraula coincideixen. Si coincideixen retorna true
        for (int i = 0; (i < p.ind) && (comp); i++) {
            comp = false;
            for (int j = 0; (j < l.ind) && (!comp); j++) {
                if (p.pal[i] == l.pal[j]) {
                    l.pal[j] = ' ';
                    comp = true;
                }else{
                    comp = false;
                }
            }
        }
        return comp;
    }
    
    public boolean parIgual(Paraula par) {     //Aquest mètode reb la paraula introduida i mira si la paraula del fitxer és igual, si és igual retorna true
        boolean p = true;
        if (ind != par.ind) {
            p = false;
        }
        for (int i = 0; (i < ind) && p; i++) {
            if (pal[i] != par.pal[i]) {
                p = false;
            }
        }
        return p;
    }
    
    public char[] getPar(Paraula par){    //Retorna la paraula introduida
        return par.pal;
    }
    
    public char[] getParMaquina(){        //Retorna la paraula del diccionari
        return pal;
    }
    
    public boolean parIgualMaquina(Paraula par){              //Fa el mateix que el mètode parIgual, però aqui en vers de mirar
        boolean p = true;                                     //les fitxes i la paraula introduida, mira les fitxes i la paraula del fitxer
        for (int i = 0; (i < ind) && p; i++){
            p = false;
            for (int j = 0; (j < par.ind) && (!p); j++){
                if (pal[i] == par.pal[j]){
                    par.pal[j] = ' ';
                    p = true;
                }
            }
        }
        return p;
    }
    
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; i < ind; i++) {
            res = res + pal[i];
        }
        return res;
    }
    
}

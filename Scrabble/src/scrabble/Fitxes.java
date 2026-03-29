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
public class Fitxes {
    
    private char lletra;
    private int puntuacio;
    private int cantidad;
    
    public Fitxes() {        //Constructor
       
    }
    
    public void rebreLletra(char n){       //Per introduir la lletra llegida del fitxer
        lletra = n;
    }
    
    public void rebrePunts(int n){       //Per introduir els punts de la lletra llegida del fitxer
        puntuacio = n;
    }
    
    public void rebreCantitat(int n){       //Per introduir la cantitat de lletres llegida del fitxer
        cantidad = n;
    }
    
    public char enviaLletra(){       //Retorna la lletra
        return lletra;
    }
    
    public int enviaCantitat(){       //Retorna la cantitat
        return cantidad;
    }
    
    public int enviaPunts(){       //Retorna els punts
        return puntuacio;
    }
    
    @Override
    public String toString(){
        return "Lletra: " + lletra + " | Punts: " + puntuacio + " | Cantidad: " + cantidad;
    }
    
}

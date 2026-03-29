//Autors: Josep Gabriel Fornés Reynés i Antoni Cruz Carrió
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrabble;

import java.io.IOException;
import java.util.Random;

/**
 *
 * @author PepBiel
 */
public class Scrabble {

    LT tec = new LT();            //Ens permet accedir a la Classe LT on ens dona la possibilitat de llegir sencers, caracters...
    private int num;
    private Fitxes[] munt = new Fitxes[26];
    private Character car;
    private Integer torn;
    private char[] lin;
    private int pos;
    private int puntsMaquina;
    private final int nombreLletres = 104;

    public static void main(String[] args) throws IOException {
        (new Scrabble()).inicio();
    }

    /**
     * *********************************************************************************************
     ****************** PRESENTACIÓ *************************************
     * **********************************************************************************************
     */
    private void inicio() throws IOException {    //En aquesta part podem veure que segons l'opció elegida ens durà a un
        presentacio();                            // o a un alttre.
        eleccio();
        if (num == 1) {
            joc();
        }
        if (num == 2) {
            historial();
        }
        if (num == 3) {
            superMaquina();
        }
        if (num == 4) {
            acabarJoc();
        }
        if (num == 5) {
            enseñarFitxes();
        }

    }

    private void presentacio() {               //Aquest mètode imprimeix per pantalla les distintes opcions del joc
        System.out.println("*********************************************");
        System.out.println("BENVINGUTS AL MINI-SCRABBLE");
        System.out.println("Opcions:\n"
                + "1. Jugar una partida.\n"
                + "2. Historial de partides.\n"
                + "3. Usuari vs supermàquina.\n"
                + "4. Sortir del joc.\n"
                + "5. Enseñar fitxes");
        System.out.println("*********************************************");
    }

    private void eleccio() {                           //Aquí ens demana la opció que volem elegir  
        System.out.print("Elegeix opció: ");
        num = agafarNatural(tec);
    }

    private int agafarNatural(LT te) {               //Si el nombre que introduim no és un nombre entre 1 i 5,
        Integer n = te.llegirSencer();               //ens ho torna a demanr fins que ho introduiguem bé.
        while ((n == null) || ((n != 1) && (n != 2) && (n != 3) && (n != 4) && (n != 5))) {
            System.out.print("ERROR!!! OPCIÓ?? ");
            n = te.llegirSencer();
        }
        return n;
    }

    /**
     * *********************************************************************************************
     * ************************************ OPCIÓ JOC
     * ***********************************************
     * *********************************************************************************************
     */
    public void joc() throws IOException {    //Aquest mètode és l'opció 1
        System.out.println("*********************************************");
        System.out.println("*              COMENÇA EL JOC               *");
        System.out.println("*********************************************");
        int punts = 0;
        Paraula nom = new Paraula();
        System.out.print("Introdueix el teu nom: ");
        nom = validacioNom();                             //Ens demana el nom i valida si el nom és buit o no
        System.out.print("Quants torns vols fer? ");
        torn = agafarTorn();                              //Ens demana els torns que volem fer i valida si el torn és un nombre natural
        for (int i = 0; i < torn; i++) {                                       //En aquest for el que ens permetrà és repetir l'acció
            char[] arrayFitxes = new char[11];                                 //segons els torns que hagui introduit l'usuari
            generarFitxes();
            System.out.println("Les lletres són: ");
            arrayFitxes = sorteig();
            System.out.println(" ");
            System.out.println("Et donam 30 segons per la teva contesta: ");
            cronometre();
            punts = punts + cercarAlfabet(arrayFitxes);
            System.out.println("Total: " + punts + " punts");
        }
        escriureHistorial(nom, torn, punts);                          //Aquí li passam els paràmetres de nom, torn i punts a un mètode
        acabarTorns();                                                //perquè ho escrigui al fitxer de l'historial
    }

    private Paraula validacioNom() {                             //Mira si el nom introduit és buit o no
        Paraula n = new Paraula(tec.llegirLiniaA());
        while (n.buida()) {
            System.out.print("El nom és incorrecte. Introdueix nom: ");
            n = new Paraula(tec.llegirLiniaA());
        }
        return n;
    }

    private int agafarTorn() {                                   //Mira si el torn introduit és buit o menor que 0
        Integer t;
        t = tec.llegirSencer();
        while ((t == null) || (t <= 0)) {
            System.out.print("ERROR!! Introduix torn: ");
            t = tec.llegirSencer();
        }
        return t;
    }

/*******************************************************************************
****************************** CRONOMETRE **************************************
*******************************************************************************/                                                                    /**/
    private void cronometre() {                                             /**/
        int interval = 5;                                                   /**/
        for (int i = 0; i <= 30; i = i + interval) {                        /**/
            System.out.print(" " + i + " ");                                /**/
            if (i < 30) {                                                   /**/
                esperaSegons(interval);                                     /**/
            }                                                               /**/
        }                                                                   /**/
    }                                                                       /**/
                                                                            /**/
    public void esperaSegons(int s) {                                       /**/
        long temps = System.currentTimeMillis();                            /**/
        while (System.currentTimeMillis() < (temps + (s * 1000))) {         /**/
        }                                                                   /**/
    }                                                                       /**/
                                                                            /**/
/*******************************************************************************
********************************************************************************
*******************************************************************************/
    
    
    private int cercarAlfabet(char[] c) {                                 //En aquest mètode es produeix l'introducció de
        Paraula paraulaIntroduida;                                        //la paraula de l'usuari i la compara amb l'arxiu del diccionari
        Paraula l;
        int p = 0;
        System.out.println(" ");
        System.out.print("Introdueix una paraula formada per les lletres obtingudes: ");
        paraulaIntroduida = new Paraula(tec.llegirLiniaA());
        boolean paraula = false;
        Paraula pa = new Paraula("cat.dic".toCharArray());
        FI fi = new FI(pa);
        Paraula aux = new Paraula();
        char[] par;
        fi.obrir();
        do {
            lin = fi.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    aux = llegirParaula();
                    l = new Paraula(c);
                    if (aux.compararLletres(l, paraulaIntroduida)) {   //Mira si les lletres sortejades i la paraula introduida coincideixen
                        if (aux.parIgual(paraulaIntroduida)) {         //Mira si la paraula introduida coincideis amb la del diccionari
                            paraula = true;
                            System.out.println("Paraula correcte");
                            par = aux.getPar(paraulaIntroduida);
                            p = contarPunts(par);
                        }
                    }
                } while (!(aux.buida()));
            }
        } while ((lin != null) && (paraula == false));
        if ((lin == null) && (paraula == false)) {               //Si no troba ninguna paraula del diccionari igual a la introduida, els puns son -10
            System.out.println("Paraula incorrecte");
            p = p - 10;
        }
        fi.tancar();
        System.out.println("Punts del torn: " + p);
        return p;
    }

    public Paraula llegirParaula() {
        Paraula aux = new Paraula();
        botarBlancs();
        while ((pos < lin.length) && (lin[pos] != ' ')) {
            aux.posar(lin[pos++]);
        }
        return aux;
    }

    public void botarBlancs() {
        while ((pos < lin.length) && (lin[pos] == ' ')) {
            pos++;
        }
    }

    /**
     * **************************************************************
     */
    private void generarFitxes() throws IOException {                    //Genera les fitxes i les introduix a la classe Fitxes
        Paraula pa = new Paraula("alfabet.txt".toCharArray());           //Es llegeix caracter a caracter l'arxiu de les fitxes
        FI fi = new FI(pa);                                              //i després s'introdueixen  la classe Fitxa
        fi.obrir();
        car = fi.llegirCaracter();
        for (int i = 0; (car != null) && (i < munt.length); i++) {
            int num = 0;
            int num2 = 0;
            while (!((car >= 'a') && (car <= 'z'))) {
                car = fi.llegirCaracter();
            }
            munt[i] = new Fitxes();
            munt[i].rebreLletra(car);

            while (!((car >= '0') && (car <= '9'))) {
                car = fi.llegirCaracter();
            }
            while ((car >= '0') && (car <= '9')) {
                num = num * 10 + (int) (car - '0');
                car = fi.llegirCaracter();
            }
            munt[i].rebreCantitat(num);

            while (!((car >= '0') && (car <= '9'))) {
                car = fi.llegirCaracter();
            }
            while ((car >= '0') && (car <= '9')) {
                num2 = num2 * 10 + (int) (car - '0');
                car = fi.llegirCaracter();
            }
            munt[i].rebrePunts(num2);
        }
        fi.tancar();
    }

    private int contarPunts(char[] p) {              //Li passam la paraula introduida, que ja sabem que és correcte
        int punt = 0;                                //i segons la lletra que hi hagui miram el valor que te i li sumam
        for (int i = 0; i < munt.length; i++) {
            for (int j = 0; j < p.length; j++) {
                if (munt[i].enviaLletra() == p[j]) {
                    punt = punt + munt[i].enviaPunts();
                }
            }
        }
        return punt;
    }

    private char[] sorteig() {                                                  //En aquest mètode el que feim és sortejar un número entre el 0 i 103
        int c;
        boolean b;                                                              //i a través dels dos fors anam sumant 1 a una variable fins arribar
        char[] af = new char[11];                                               //al nombre sortejat. Després a la cantidat de la lletra tocada li restam 1.
        for (int i = 0; i < 11; i++) {
            c = new Random().nextInt(nombreLletres);
            int v = 0;
            b = false;
            for (int j = 0; (j < munt.length) && (!b); j++) {
                for (int k = 0; (k < munt[j].enviaCantitat()) && (!b); k++) {
                    v++;
                    if ((v >= c) && (munt[j].enviaCantitat() != 0)) {
                        munt[j].rebreCantitat(munt[j].enviaCantitat() - 1);
                        System.out.print(" " + munt[j].enviaLletra() + " ");
                        af[i] = munt[j].enviaLletra();
                        b = true;
                    }
                }
            }
        }
        return af;
    }

    /**
     * **************************************************************
     */
    private void escriureHistorial(Paraula n, int t, int pu) throws IOException {     //Li passam els parametres del nom, els torns i els punts
        Paraula s = new Paraula("historial.txt".toCharArray());                       //per aixi passar-ho a la classe FO i que ho escrigui a l'historial
        FO fo = new FO(s);
        fo.obrir();
        Paraula aux = new Paraula();
        fo.gravar(n, t, pu);
        fo.tancar();
    }

    private void acabarTorns() throws IOException {                             //Aquest mètode és perque en acabar cada opció, tenir l'oportunitat
        Integer num2 = 0;                                                       //de retornar al menú principal o d'acabar el joc.
        System.out.println(" ");
        System.out.println("*********************************************");
        System.out.println("LA TEVA ELECCIÓ HA ACABAT");
        System.out.println("Elegeix entre aquestes opcions:\n"
                + "1. Anar al menú principal.\n"
                + "2. Sortir del joc. ");
        System.out.print("Elegeix opció: ");
        num2 = tec.llegirSencer();
        while ((num2 == null) || ((num2 != 1) && (num2 != 2))) {
            System.out.print("ERROR!!! OPCIÓ?? ");
            num2 = tec.llegirSencer();
        }
        if (num2 == 1) {
            inicio();
        }
        if (num2 == 2) {
            acabarJoc();
        }
    }

    /**
     * **************************************************************
     */
    /**
     * ***************************************************************************************
     * ****************************** OPCIÓ HISTORIAL
     * ****************************************
     * ******************************************************************************************
     */
    private void historial() throws IOException {                          //Aquest mètode imprimeix per pantalla el contingut de l'historial
        System.out.println("*********************************************************************");
        System.out.println("*                      HISTORIAL DE PARTIDES                        *");
        System.out.println("*********************************************************************");
        Paraula pa = new Paraula("historial.txt".toCharArray());
        FI fic = new FI(pa);
        fic.obrir();
        lin = fic.llegirLinia();
        do {
            System.out.println(lin);
            lin = fic.llegirLinia();
        } while (lin != null);
        fic.tancar();
        acabarTorns();
    }

    /**
     * ***************************************************************************************
     * ****************************** OPCIÓ SUPERMAQUINA
     * ****************************************
     * ******************************************************************************************
     */
    private void superMaquina() throws IOException {                            //Aquí hi ha el mètode de la supermàquina on el principi és el mateix que el joc normal
        int punts = 0;
        puntsMaquina = 0;
        Paraula nom = new Paraula();
        System.out.print("Introdueix el teu nom: ");
        nom = validacioNom();
        System.out.print("Quants torns vols fer? ");
        torn = agafarTorn();
        for (int i = 0; i < torn; i++) {
            char[] arrayFitxes;
            generarFitxes();
            arrayFitxes = sorteig();
            System.out.println(" ");
            System.out.println("Et donam 30 segons per la teva contesta: ");
            cronometre();
            punts = punts + cercarAlfabet(arrayFitxes);
            System.out.println("En total: " + punts + " punts");
            tornMaquina(arrayFitxes);                                           //Aquesta part és la única que canvia
        }
        escriureHistorial(nom, torn, punts);
        acabarTorns();
    }

    private void tornMaquina(char[] arrayFitxesMaquina) throws IOException {       //Li passam l'array de ses fitxes
        System.out.println("TORN DE LA SUPERMÀQUINA");
        puntsMaquina = puntsMaquina + cercarAlfabetMaquina(arrayFitxesMaquina);    //Crida al mètode cercarAlfabetMaquina
        System.out.println("En total supermàquina: " + puntsMaquina + " punts");
    }

    private int cercarAlfabetMaquina(char[] c) {                    //Li passam s'array de fitxes
        int p = 0;
        int p2;
        Paraula l;
        Paraula pa = new Paraula("cat.dic".toCharArray());         //Obrim l'arxiu del diccionari
        FI fi = new FI(pa);
        Paraula aux = new Paraula();
        char[] par = null;                                           //Cream dos chars per aixi comparar l'anterior paraula i la seguent i guardar domes la paraula amb més puntuació
        char[] par2;
        fi.obrir();
        do {
            lin = fi.llegirLinia();
            if (lin != null) {
                pos = 0;
                do {
                    aux = llegirParaula();
                    l = new Paraula(c);
                    if (aux.parIgualMaquina(l)) {
                        par2 = aux.getParMaquina();
                        p2 = contarPuntsMaquina(par2);
                        if (p2 > p) {                                 //Observam quina paraula té més puntuació i la guardam a la variable par
                            par = new char[par2.length];
                            for (int i = 0; i < par2.length; i++) {
                                par[i] = par2[i];
                                p = p2;
                            }
                        } else {

                        }
                    }
                } while (!(aux.buida()));
            }
        } while ((lin != null));
        if (par != null) {
            System.out.print("La paraula amb més puntuació és '");
            for (int i = 0; i < par.length; i++) {
                System.out.print(par[i]);
            }
            System.out.println("' amb una puntuació de " + p + " punts");
        }
        fi.tancar();
        return p;
    }

    private int contarPuntsMaquina(char[] p) {         //Compta els punts de la paraula que hem passat per paràmetre
        int punt = 0;
        for (int i = 0; i < munt.length; i++) {
            for (int j = 0; j < p.length; j++) {
                if (munt[i].enviaLletra() == p[j]) {
                    punt = punt + munt[i].enviaPunts();
                }
            }
        }
        return punt;
    }

    /**
     * ***************************************************************************************
     * ****************************** OPCIÓ ACABAR JOC
     * ****************************************
     * ******************************************************************************************
     */
    private void acabarJoc() {                                                  //Mètode d'acabar joc
        System.out.println("*********************************************");
        System.out.println("*             EL JOC HA ACABAT              *");
        System.out.println("*********************************************");
        System.exit(0);
    }
    
    /**
     * ***************************************************************************************
     * ****************************** OPCIÓ ENSEÑAR FITXES
     * ****************************************
     * ******************************************************************************************
     */
    private void enseñarFitxes() throws IOException {                                                  //Mètode enseñar les fitxes
        System.out.println("*********************************************");
        System.out.println("*             TOTES LES FITXES              *");
        System.out.println("*********************************************");
        generarFitxes();
        imprimirFitxes();
        acabarTorns();
    }
    
    private void imprimirFitxes() {
        for (int i = 0; i < munt.length; i++) {
            System.out.println(munt[i].toString());
        }
    }

}

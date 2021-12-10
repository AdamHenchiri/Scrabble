
// dmaj : fin octobre 2020

import java.util.*;
import java.lang.*;

public class Ut {

    public static void afficher (String ch) {
	System.out.print(ch);
    }
    public static void afficherSL (String ch) {
	afficher(ch); passerLigne();
    }

    public static void afficher (int nb) {
	System.out.print(nb + "");
    }
    public static void afficherSL (int nb) {
	afficher(nb); passerLigne();
    }

    /** Retour a la ligne : */
    
    public static void passerLigne () {
	System.out.println("");
    }

    public static void passerALaLigne () {
	passerLigne();
    }

    public static void sauterALaLigne () {
	passerLigne();
    }

    public static void sautLigne () {
	passerLigne();
    }
    
    public static void afficher (double nb) {
	System.out.print(nb + "");
    }
    public static void afficherSL (double nb) {
	afficher(nb); passerLigne();
    }

    public static void afficher (float nb) {
	System.out.print(nb + "");
    }
    public static void afficherSL (float nb) {
	afficher(nb); passerLigne();
    }

    public static void afficher (char c) {
	System.out.print(c + "");
    }
    public static void afficherSL (char c) {
	afficher(c); passerLigne();
    }

   public static void afficher (boolean b) {
	System.out.print(b + "");
    }
    public static void afficherSL (boolean b) {
	afficher(b); passerLigne();
    }

    public static void afficher (int [][] mat) {
	for (int i = 0 ; i < mat.length ; i++) {
	    for (int j = 0 ; j < mat[i].length ; j++) {
		System.out.print(mat[i][j] + "\t");
	    }
	    sautLigne();
	}
    }
    public static void afficherSL (int [][] mat) {
	afficher(mat); passerLigne();
    }
    
    public static int saisirEntier () {
	
	Scanner clavier=new Scanner(System.in);
	String s = clavier.nextLine(); //int lu = clavier.nextInt();
	int lu=456;
	try{
	    lu = Integer.parseInt(s);
	}
	catch(NumberFormatException ex){
	    System.out.println("Ce n'est pas un entier valide");
	}
	return lu;
    }

    public static double saisirDouble () {
	return saisirReel();
    }

    public static double saisirReel () {
	
	Scanner clavier=new Scanner(System.in);
	String s = clavier.nextLine();
	double lu = -123.987;
	try{
	    lu = Double.parseDouble(s);
	}
	catch(NumberFormatException ex){
	    System.out.println("Ce n'est pas un reel valide");
	}
        return lu;
    }

    public static float saisirFlottant () {
	
	Scanner clavier=new Scanner(System.in);
	String s = clavier.nextLine();
	float lu = -123.987F;
	try{
	    lu = Float.parseFloat(s);
	}
	catch(NumberFormatException ex){
	    System.out.println("Ce n'est pas un reel valide");
	}
        return lu;
    }

    public static char saisirCaractere () {
	
	Scanner clavier=new Scanner(System.in);
	char lu = clavier.next().charAt(0);
        return lu;
    }

    public static boolean saisirBooleen () {
	
	Scanner clavier=new Scanner(System.in);
	boolean lu = clavier.nextBoolean();
        return lu;
    }

    public static String saisirChaine () {
	
	Scanner clavier=new Scanner(System.in);
	String s = clavier.nextLine();
	return s;
    }

    public static char [] saisirCharArray () {
	
	Scanner clavier=new Scanner(System.in);
	String s = clavier.nextLine();
	char [] tab = s.toCharArray();
	return tab;
    }
    

    public static int alphaToIndex (char c) {
	// Prerequis : c est un caractere entre 'a' et 'z'
	// Resultat : la valeur 0 pour 'a', 12 pour 'm', 25 pour 'z'...
	return (int)c - 97;
    }

    public static char indexToAlpha (int i) {
	// Prerequis : i est un entier entre 0 et 25 
	//             (par exemple, indice d'un tableau)
	// Resultat : la valeur 'a' pour 0, 'm' pour 12, 'z' pour 25...b
	return (char)(i + 97);
    }

    /**
     * @param a entier
     * @param b entier
     * Pre-requis : aucun
     * @return le PGCD de a et b en utilisant l'algorithme d'Euclide
     */
    public static int pgcd(int a, int b) {

	a=Math.abs(a);
	b=Math.abs(b);
	int temp;
	while (b != 0) {
	    temp = b;
	    b = a%b;
	    a = temp;
	}
	return a;
    } 

    public static int randomMinMax(int min, int max) {
	// Resultat : un entier entre min et max choisi aleatoirement
	Random rand = new Random();
	int res = rand.nextInt(max - min + 1) + min;
	// System.out.println(res + " in [" + min + "," + max + "]");
	// assert min <= res && res <= max : "tirage aleatoire hors des bornes";
	return res;
    }

    public static void clearConsole () {
	// Action : efface la console (le terminal)
	System.out.print("\033[H\033[2J");
    }

    public static void pause (int timeMilli) {
	// Action : suspend le processus courant pendant timeMilli millisecondes
	try {
	    Thread.sleep(timeMilli); 
	} catch(InterruptedException ex) {
	    ex.printStackTrace();
	}
    }

    public static boolean estEntier(double n) {
	// Resultat : vrai ssi n est un reel qui est aussi un entier (exemple: 5.0)
	return (int)n == n;
    }

    public static int modulo2 (int a, int b) {
	// Pré-requis : b != 0 mais b peut être négatif
	// Résultat : a modulo b, un nombre entre 0 et b-1 si b est positif, entre b+1 et 0 sinon.
	//            Suit la définition : a mod b := a - b * E(a/b)
	// Remarque : l'opérateur modulo de Java ne suit pas cette définition et
	//            peut rendre un nombre de signe opposé à b.
	return a  -  b * (int)Math.floor((double)a/b) ;
    }

    public static boolean estBissextile (int an) {
	// Resultat : vrai ssi an est bissextile
	return an % 400 == 0 || an % 4 == 0 && an % 100 != 0;
    }
    
} // end class




	

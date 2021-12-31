import java.util.Random;

public class MEE {
    // PR => Pré-requis
    // A => Action
    private int[] tabFreq;
    private int nbTotEx;

    /**
     * PR: max >= 0
     * A: Crée un multi-ensemble vide dont les éléments seront inférieurs à max
     * 
     * @param max
     */
    public MEE(int max) {

        // On initialise le tableau 'tabFreq' et 'nbToEx' à 0
        this.tabFreq = new int[max];
        this.nbTotEx = 0;
        // On met toutes les cases du tableau a 0
        for (int i = 0; i < this.tabFreq.length; i++) {
            this.tabFreq[i] = 0;
        }
    }

    /**
     * PR: Les éléments de tab sont positifs ou nuls.
     * A: Crée un multi-ensemble dont le tableau de fréquences est une copie de tab.
     * 
     * @param tab
     */
    public MEE(int[] tab) {
        // On initializa le tableau et le nbr
        this.tabFreq = new int[tab.length];
        this.nbTotEx = 0;
        // On fait une boucle pour copier tous les elements de 'tab' dans 'tabFreq'
        for (int i = 0; i < tab.length; i++) {
            // On copie les element de tab dans tabFreq et incremente nbTotEx
            this.tabFreq[i] = tab[i];
            this.nbTotEx += tabFreq[i];
        }

    }

    /***
     * PR: Nothing
     * A: Copie d'une autre MEE
     * 
     * @param e
     */
    public MEE(MEE e) {
        // On copie les éléments de e dans this
        this.tabFreq = e.tabFreq;
        this.nbTotEx = e.nbTotEx;
    }

    /**
     * PR: Nothing
     * A: Getter of the this.nbToEx
     * 
     * @return
     */
    public int getNbTotEx() {
        return this.nbTotEx;
    }


    public int[] getTabFreq() {
        return this.tabFreq;
    }

    /**
     * PR: Nothing
     * A: Le résultat est vrai si est seulement si l'ensemble est vide
     * 
     * @return
     */
    public boolean estVide() {
        // On initialise la variable 'estVide', sur vrai
        boolean estVide = true;

        // On fait une boucle pour connaitre si l'element est vide
        for (int i = 0; i < this.tabFreq.length; i++) {
            // On regarde si le nombre dans le tableaux est supérieur à 0
            if (this.tabFreq[i] > 0) {
                // Alors on passe la variable 'estVide' à false;
                estVide = false;
            }
        }
        return estVide;
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: Ajoute un exemplaire de i à this
     * 
     * @param i
     */
    public void ajoute(int i) {
        // Si le chiffre est bon alors on ajoute dans 'tabFreq' 1 exemplaire
        this.tabFreq[i] = this.tabFreq[i] + 1;
        this.nbTotEx++;
    }

    /**
     * PR: i > 0 et i < tabFreq.length
     * A: retire un exemplaire de i dans this s'il en existe et retourne vrai si est
     * seulement si cette action a pu être effectuée
     * 
     * @param i
     * @return
     */
    public boolean retire(int i) {
        // On initialise la variable 'retireFonctionne' pour savoir si nous avons réussi
        // à retirer l'exemplaire
        boolean retireFonctionne = false;
        // Si il existe un exemplaire dans this alors
        if (this.tabFreq[i] > 0) {
            this.tabFreq[i] = this.tabFreq[i] - 1;
            this.nbTotEx--;
            retireFonctionne = true;
        }

        return retireFonctionne;
    }

    /**
     * PR: this est non vide.
     * A: Retire de this un exemplaire choisi aléatoirement et le retourne.
     * 
     * @return
     */
    public int retireAleat() {
        // Choisi un nombre aléatoire entre 0 et la longueur du tableau -1
        Random rand = new Random();
        int indice = rand.nextInt(0 - this.tabFreq.length - 1);

        // Si le nombre placé à l'indice du tableau et > à 0 alors:
        if (this.tabFreq[indice] > 0) {
            // On retire 1 de cette élément
            this.tabFreq[indice]--;
            this.nbTotEx--;
            // Sinon
        } else {
            // On met indice à -1p
            indice = -1;
            // On réapelle la fonction
            retireAleat();
        }
        // On retourne l'indice
        return indice;
    }

    /**
     * PR: 0 <= i && i < tabFreq.length
     * A:Tranfère un exemplaire de i de this vers e s'il en existe, et retourne vrai
     * ssi cette action a pu être effectué.
     * 
     * @param e
     * @param i
     * @return
     */
    public boolean transfere(MEE e, int i) {
        // On initialise la variable 'resultat', qui est un boolean.
        boolean resultat = false;

        // On retire 1 de this a l'indice donnée.
        this.retire(i);
        // On ajoute 1 à l'ensemble e donnée en paramètre et a l'indice donnée.
        e.ajoute(i);
        // On passe la variable résultat à true.
        resultat = true;

        // On retourne la variable boolean.
        return resultat;
    }

    /**
     * PR: k >= 0
     * A: transfère k exemplaires choisis aléatoirement de this vers e dans la
     * limite du contenu de this.
     * 
     * @param e
     * @param k
     */
    public void tranfereAleat(MEE e, int k) {
        // Choisi un nombre aléatoire entre 0 et la longueur du tableau -1
        Random rand = new Random();
        int indice = rand.nextInt(0 - this.tabFreq.length - 1);

        // Si l'élément contenue à l'indicie 'indice' dans le tableau est suppérieur à
        // nombre d'élément que l'on veut enlever alors:
        if (this.tabFreq[indice] > k) {
            // On fait une boucle pour x fois de i=0 à i<k
            for (int i = 0; i < k; i++) {
                // On retire alors une fois l'élément à l'indice indiquer
                this.retire(indice);
            }
            // Sinon
        } else {
            // On affiche une erreur:
            System.out.println("ERROR: Trop peut d'exemplaire dans l'indice choisis");
        }
    }

    /**
     * PR: tabFreq.length <= v.length
     * A: Retourne la somme des valeurs des exemplaires des éléments de this, la
     * valeur d'un exemplaire d'un élément i de this étant égale a v[i]
     * 
     * @param v
     * @return
     */
    public int sommeValeurs(int[] v) {
        // On initialize le résulat
        int resultat = 0;

        // On fait une boucle pour parcourir tous le trableau this
        for (int i = 0; i < this.tabFreq.length; i++) {
            // On ajoute au résultat le nombre d'exemplaire * valeur des points
            resultat += this.tabFreq[i] * v[i];
        }

        // Retoune le résultat
        return resultat;
    }
}

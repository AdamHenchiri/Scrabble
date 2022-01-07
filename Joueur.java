public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {

        this.nom = unNom;
        this.chevalet = new MEE(26);
        this.score = 0;

    }

    public MEE getChevalet() {

        return this.chevalet;
    }

    public int getScore() {

        return this.score;
    }

    public String getNom() {
        return this.nom;
    }

    public void ajouteScore(int nb) {

        this.score = this.score + nb;
    }

    public void setScore(int scr) {
        this.score = scr;
    }
    /*
     * pré-requis : nbPointsJet indique le nombre de points rapportés par chaque
     * jeton/lettre
     */

    public int nbPointsChevalet(int[] nbPointsJet) {

        int nbPointsChevalet = 0;

        if (!(this.chevalet.estVide())) {

            nbPointsChevalet = this.chevalet.sommeValeurs(nbPointsJet);

        }
        return nbPointsChevalet;
    }

    /* pré-requis : les éléments de s sont inférieurs à 26 */

    public void prendJetons(MEE s, int nbJetons) {

        int nbExampTransfer = 0;
        if ((s.getNbTotEx() > nbJetons)) {
            while ((nbJetons != 0)) {
                
                
                nbExampTransfer = s.transfereAleat(this.chevalet, nbJetons);
                //System.out.println(nbJetons+"nbJetons");
                //System.out.println(nbExampTransfer+"nbExampTransfer");
                
                nbJetons = nbJetons - nbExampTransfer;
                //System.out.println(nbJetons);
            }
            
        }
        /*
         * le cas qu'il reste est si le nombre de jetons restant dans le sac est
         * inférieur au nombre de jetons que le joueur doit piocher,
         * on transfère donc l'intégralité du sac dans le chevalet du joueur
         */

        else if (s.getNbTotEx() <= nbJetons && s.getNbTotEx() != 0) {
            for (int i = 0; i < s.getTabFreq().length; i++) {
                if (s.getEltTabFreq(i) != 0) {
                    s.transfere(this.chevalet, i);
                }
            }
        } else {
            System.out.println("Erreur: sac vide !!");
        }
    }

    public int joue(Plateau p, MEE s, int[] nbPointsJet) {

        int resultat = 0;
        char reponse = '\0';

        do {
            System.out.println("veillez choisir votre reponse ");
            System.out.println("E pour ECHANGER un ou plusieurs jeton/s..");
            System.out.println("P pour PASSER votre tour..");
            System.out.println("J pour JOUER un mot..");
            reponse = Ut.saisirCaractere();

        } while (!(reponse != 'E' || reponse != 'P' || reponse != 'J'));

        if (reponse == 'E') {
            this.echangeJetons(s);
            resultat = 0;
        } else if (reponse == 'P') {

            resultat = -1;
        }

        else if (reponse == 'J') {
            resultat = 1;
            while (!(joueMot(p, s, nbPointsJet))) {
                joueMot(p, s, nbPointsJet);
            }
        }

        return resultat;
    }

    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {

        String motjoue = "";
        int numCol = 0;
        int numLig = 0;
        char sens = '\0';
        boolean test = false;

        System.out.println("saisir le mot que vous souhaitez jouer !");
        motjoue = Ut.saisirChaine();
        System.out.println("saisir le numéro de la colonne de votre première lettre !");
        numCol = Ut.saisirEntier();
        System.out.println("saisir le numéro de la ligne de votre première lettre !");
        numLig = Ut.saisirEntier();
        System.out.println("donner le sens de votre mot !");
        sens = Ut.saisirCaractere();
        test = (p.placementValide(motjoue, numLig, numCol, sens, s));
        if (test) {
            joueMotAux(p, s, nbPointsJet, motjoue, numLig, numCol, sens);
        }
        return test;
    }

    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLig, int numCol, char sens) {
        p.place(mot, numLig, numCol, sens, s);
    }

    public boolean estCorrectPourEchange(String mot) {

        boolean res = false;

        int i = 0;

        MEE e1 = new MEE(this.chevalet);

        for (i = 0; i < mot.length(); i++) {

            if (Ut.estUneMaj(mot.charAt(i))) {

                while (res == false) {

                    res = !(e1.getEltTabFreq(Ut.majToIndex(mot.charAt(i))) > 0);
                    e1.retire(Ut.majToIndex(mot.charAt(i)));
                }
            }
        }
        return res;
    }

    public void echangeJetonsAux(MEE sac, String ensJetons) {

        int nbJetEchange = 0;

        do {
            System.out.println("combien de lettre voulez vous echanger ?");

            nbJetEchange = Ut.saisirEntier();

        } while (nbJetEchange < 1 || nbJetEchange > 7);

        this.prendJetons(sac, nbJetEchange);

        for (int i = 0; i < ensJetons.length(); i++) {

            this.chevalet.transfere(sac, Ut.majToIndex(ensJetons.charAt(i)));
        }
    }

    public void echangeJetons(MEE sac) {

        String ensJetons = "";

        System.out.println("quel mot voulez vous echanger ?");

        ensJetons = Ut.saisirChaine();

        if (estCorrectPourEchange(ensJetons)) {

            echangeJetonsAux(sac, ensJetons);

        }
    }
    /*
     * public static void main(String[] args) {
     * int[] sacdebase = { 9, 2, 2, 3, 15, 2, 2, 2, 8, 1, 1, 5, 3, 6, 6, 2, 1, 6, 6,
     * 6, 6, 2, 1, 1, 1, 1 };
     * MEE sac=new MEE(sacdebase);
     * int[] tab = { 7, 6, 4, 4, 16, 8, 3, 1, 3, 4, 1, 2, 2, 1, 4, 3, 8, 4, 1, 3, 2,
     * 5, 0, 0, 1, 1 };
     * MEE meeP1 = new MEE(tab);
     * meeP1.prendJetons(sac,7);
     * }
     */
}

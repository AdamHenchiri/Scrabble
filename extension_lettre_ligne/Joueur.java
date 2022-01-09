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
    /**
     * 
     * @param nbPointsJet indique le nombre de points rapportés par chaque
     * jeton/lettre
     * @return le nbr de points du chevalet du joueur this
     */
    public int nbPointsChevalet(int[] nbPointsJet) {

        int nbPointsChevalet = 0;

        if (!(this.chevalet.estVide())) {

            nbPointsChevalet = this.chevalet.sommeValeurs(nbPointsJet);

        }
        return nbPointsChevalet;
    }

    /**
     * prend le nbr nbJetons du sac s vers le chevalet this 
     * @param s
     * @param nbJetons
     */
    public void prendJetons(MEE s, int nbJetons) {

        int nbExampTransfer = 0;
        if ((s.getNbTotEx() > nbJetons)) {
            while ((nbJetons != 0)) {
                
                
                nbExampTransfer = s.transfereAleat(this.chevalet, nbJetons);
                nbJetons = nbJetons - nbExampTransfer;
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
            System.out.println("sac vide !!");
        }
    }
    /**
     * 
     * @param p le plateau
     * @param s le sac 
     * @param nbPointsJet le tableau avec les points de chaque Jetons
     * @return 1 si le joueur this décide de jouer un mot , -1 si il passe son tour et 0 si il echange des Jetons
     */
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
                System.out.println("placement NON valide");
            }
        }

        return resultat;
    }
    /**
     * 
     * @param p
     * @param s
     * @param nbPointsJet
     * @return true ssi le placement de ce mot sur le plateau est valide
     */
    public boolean joueMot(Plateau p, MEE s, int[] nbPointsJet) {

        String motjoue = "";
        int numCol = 0;
        int numLig = 0;
        char sens = '\0';
        boolean test = false;

        System.out.println("saisir le mot que vous souhaitez jouer !");
        motjoue = Ut.saisirChaine();
        do{
        System.out.println("saisir la lettre de la ligne de votre premiere lettre !");
        numLig = Ut.majToIndex(Ut.saisirCaractere())+1;
        }while(numLig<1 || numLig>15);
        do {
        System.out.println("saisir le numero de la colonne de votre premiere lettre !");
        numCol = Ut.saisirEntier();
        }while (numCol<1 || numCol>15);
        do{
        System.out.println("donner le sens de votre mot v/h...");
        sens = Ut.saisirCaractere();
        }while (sens!='h' && sens!='v');
        test = (p.placementValide(motjoue, numLig-1, numCol-1, sens, chevalet));
        if (test) {
            joueMotAux(p, chevalet, nbPointsJet, motjoue, numLig-1, numCol-1, sens);
        }
        return test;
    }
    /**
     * place le mot sur le plateau et ajoute les points au score du joueur this
     * @param p
     * @param s
     * @param nbPointsJet
     * @param mot
     * @param numLig
     * @param numCol
     * @param sens
     */
    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot, int numLig, int numCol, char sens) {
        p.place(mot, numLig, numCol, sens, s);
        int nbp=p.nbPointsPlacement(mot, numLig, numCol, sens, nbPointsJet);
        this.score+=nbp;
    }
    /**
     * 
     * @param mot
     * @return true si mot à changer et valide 
     */
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
    /**
     * echange les jetons demandé
     * @param sac
     * @param ensJetons
     */
    public void echangeJetonsAux(MEE sac, String ensJetons) {

        int nbJetEchange = 0;
        nbJetEchange=ensJetons.length();

        this.prendJetons(sac, nbJetEchange);

        for (int i = 0; i < ensJetons.length(); i++) {

            this.chevalet.transfere(sac, Ut.majToIndex(ensJetons.charAt(i)));
        }
    }
    /**
     * demande le mot a echanger et valide cette transaction ou pas
     * @param sac
     */
    public void echangeJetons(MEE sac) {

        String ensJetons = "";

        System.out.println("quel mot voulez vous echanger ?");

        ensJetons = Ut.saisirChaine();

        if (estCorrectPourEchange(ensJetons)) {

            echangeJetonsAux(sac, ensJetons);

        }
    }
}

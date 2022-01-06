public class Joueur {

    private String nom;
    private MEE chevalet;
    private int score;

    public Joueur(String unNom) {

        this.nom = unNom;
        this.chevalet = new MEE(7);
        this.score = 0;

    }

    public MEE getChevalet() {

        return this.chevalet;
    }

    public int getScore() {

        return this.score;
    }

    @Override
    public String toString() {

        return "{" +
        " nom='" + getNom() + "'" +
        ", chevalet='" + getChevalet() + "'" +
        ", score='" + getScore() + "'" +
        "}";
    }

    public void ajouteScore(int nb) {

        this.score = this.score + nb;
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

        int nbExampTransfer=0;
        if ((s.getNbTotEx()> nbJetons) ){
            while (  (nbJetons!=0) ){

                s.transfereAleat(this.chevalet,nbJetons);

                nbExampTransfer=s.transfereAleat(this.chevalet,nbJetons);

                nbJetons=nbJetons-nbExampTransfer;

            }
        }
         /* le cas qu'il reste est si le nombre de jetons restant dans le sac est inférieur au nombre de jetons que le joueur doit piocher,
         on transfère donc l'intégralité du sac dans le chevalet du joueur  */

         else if (s.getNbTotEx()<=nbJetons && s.getNbTotEx()!=0){
            for (int i = 0 ; i < s.getTabFreq().length ; i++) {
                if (s.getEltTabFreq(i)!=0){
                    s.transfere(this.chevalet,i);
                }
            }
        }else{
            System.out.println("Erreur: sac vide !!");
        }
    }

    public int joue(Plateau p, MEE s, int[] nbPointsJet) {

        int resultat = 0;
        char reponse='\0';

        do{
            System.out.println("veillez choisir votre reponse ");
            System.out.println("E pour ECHANGER un ou plusieurs jeton/s..");
            System.out.println("P pour PASSER votre tour..");
            System.out.println("J pour JOUER un mot..");
            reponse=Ut.saisirCaractere();

        }while(!(reponse!='E'||reponse!='P'||reponse!='J'));

        if (reponse=='E'){

            resultat = 0;     
        }
        else if (reponse=='P') { 

            resultat = -1
        }          

        else if (reponse=='J') {

            resultat = 1;
        }
        

        return resultat;
    }

    public int joueMot(Plateau p, MEE s, int[] nbPointsJet) {

        String motjoue = "";
        int numCol = 0;
        int numLig = 0;
        char sens = 'sens';

        do {
            System.out.println("saisir le mot que vous souhaitez jouer !");
            motjoue=Ut.saisirChaine();
            System.out.println("saisir le numéro de la colonne de votre première lettre !");
            numCol = Ut.saisirEntier();
            System.out.println("saisir le numéro de la ligne de votre première lettre !");
            numLig = Ut.saisirEntier();
            System.out.println("donner le sens de votre mot !");
            sens = Ut.saisirCaractere();

        }while(!(p.placementValide(motjoue,numLig,numCol,sens,s)));

        joueMotAux(p,s,nbPointsJet,motjoue,numLig,numCol,sens);

    }

    public void joueMotAux(Plateau p, MEE s, int[] nbPointsJet, String mot,int numLig, int numCol, char sens) {

        p.place(mot,numLig,numCol,sens,s);
    }       



    public boolean estCorrectPourEchange (String mot) {

        boolean res = false;

        int i = 0;

        MEE e1 = new MEE(this.chevalet);

        for (i = 0 ; i < mot.length() ; i++ ) {

            if (estUneMaj(mot.charAt(i))) {

                while (res == false) {

                    res = !(e1.getEltTabFreq(Ut.majToIndex(mot.charAt(i))) > 0);
                    e1.retire(Ut.majToIndex(mot.charAt(i)));
                }
            }
        }
        return res;    
    }

    public void echangeJetonsAux(MEE sac, String ensJetons) {

        int nbJetEchange=0;

        do {
            System.out.println("combien de lettre voulez vous echanger ?");

            nbJetEchange = Ut.saisirEntier();

        }while(nbJetEchange <1 && nbJetEchange > 7);

        this.prendJetons(sac,nbJetEchange);
    
        for (int i=0;i<ensJetons.length();i++){
           
            this.chevalet.transfere(sac,Ut.majToIndex(ensJetons.charAt(i)));
        }
    }

    public void echangeJetons(MEE sac) {

        String ensJetons = "";

        System.out.println("quel mot voulez vous echanger ?");

        ensJetons = Ut.saisirChaine();

        if (estCorrectPourEchange(ensJetons)) {

            echangeJetonsAux(sac,ensJetons);

        }
    }    

}










































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
        char reponse='\0';
        int resultat=2;
        int nbJetEchange=0;
        char lettreChange='\0';
        do{
            System.out.println("veillez choisir votre reponse ");
            System.out.println("E pour ECHANGER un ou plusieurs jeton/s..");
            System.out.println("P pour PASSER votre tour..");
            System.out.println("J pour JOUER un mot..");
            reponse=Ut.saisirCaractere();
        }while(reponse!='E'||reponse!='P'||reponse!='J');
        if (reponse=='E'){
                do{
                System.out.println("combien de lettre voulez vous echanger ?");
                 nbJetEchange=Ut.saisirEntier();
                }while(nbJetEchange>0 && nbJetEchange <= 7);
                for (int i=0;i<nbJetEchange;i++){
                System.out.println("quelle lettre voulez vouz echanger ?");
                lettreChange=Ut.saisirCaractere();
                this.chevalet.transfere(s,Ut.majToIndex(lettreChange));
                }
                this.prendJetons(s,nbJetEchange);
                resultat=0;
        }
        else if (reponse=='J'){
            
        }
    }
}
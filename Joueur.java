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

    /* pré-requis : nbPointsJet indique le nombre de points rapportés par chaque jeton/lettre */

    public int nbPointsChevalet(int[] nbPointsJet) {

        int nbPointsChevalet=0;

        if (!(this.chevalet.estVide())) {

            nbPointsChevalet = this.chevalet.sommeValeurs(nbPointsJet);

        } 
        return nbPointsChevalet ;
    }

    /* pré-requis : les éléments de s sont inférieurs à 26 */ 

    public void prendJetons(MEE s, int nbJetons) {

        int nbExampTransfer=0;

        while (s.getNbTotEx()> nbJetons) {

            while (nbJetons!=0) {

            s.transfereAleat(this.chevalet,nbJetons);

            nbExampTransfer=s.transfereAleat(this.chevalet,nbJetons);

            nbJetons=nbJetons-nbExampTransfer;

            }
        }
/* le cas qu'il reste est si le nombre de jetons restant dans le sac est inférieur au nombre de jetons que le joueur doit piocher,
   on transfère donc l'intégralité du sac dans le chevalet du joueur  */

        for (int i = 0 ; i < s.getTabFreq().length ; i++) {

            if (s.getEltTabFreq(i) != 0) {

                s.retire(getEltTabFreq(i));
      
                this.chevalet.ajoute(getEltTabFreq(i));
            }
        }

}